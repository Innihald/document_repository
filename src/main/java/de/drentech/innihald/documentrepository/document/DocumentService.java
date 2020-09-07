package de.drentech.innihald.documentrepository.document;

import de.drentech.innihald.documentrepository._external.task.Task;
import de.drentech.innihald.documentrepository._external.task.TaskClient;
import de.drentech.innihald.documentrepository.physicalfile.PhysicalFile;
import de.drentech.innihald.documentrepository.physicalfile.PhysicalFileService;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class DocumentService {

    @Inject
    DocumentRepository documentRepository;

    @Inject
    PhysicalFileService physicalFileService;

    @Inject @Channel("document-create")
    Emitter<Long> documentEmitter;

    @Inject
    @RestClient
    TaskClient taskClient;

    public List<Document> getAllDocuments() {
        return this.documentRepository.findAll().list();
    }

    public Document getDocumentById(Long id) {
        return this.documentRepository.findById(id);
    }

    @Transactional
    public Document persistDocumentWithFile(Document document, File file, String filename) throws IOException {
        PhysicalFile newFile = this.physicalFileService.createFile(file, filename);
        document.file = newFile;

        return this.persistDocument(document);
    }

    @Transactional
    public Document persistDocument(Document document) {
        this.documentRepository.persistAndFlush(document);

        this.documentEmitter.send(document.id);

        return document;
    }

    @Incoming("document-create")
    protected void createOcrOnDocumentCreation(Long documentId) throws InterruptedException {
        System.out.println("New document " + documentId);

        Task documentOcrTask = new Task();
        documentOcrTask.topic = "document";
        documentOcrTask.task = "create-ocr";
        documentOcrTask.payload.add(new Task.Payload("documentId", documentId.toString()));

        this.taskClient.createTask(documentOcrTask);

        //TODO: create new task in Task_Runner -> call rest endpoint e.g. POST /task with data type: document, task: create-ocr, payload: {documentId: 124}

        //TODO: document_task_runner gets new tasks from task_runner via rest endpoint e.g. GET "/task/document/create-ocr/next" -> /task/$type/$task
        // Scales better because the number of document_task_runners limits the number of concurrent tasks

    }
}
