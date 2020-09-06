package de.drentech.innihald.documentrepository.message.consumer;

import de.drentech.innihald.documentrepository.domain.model.Document;
import de.drentech.innihald.documentrepository.domain.model.PhysicalFile;
import de.drentech.innihald.documentrepository.http.client.TaskClient;
import de.drentech.innihald.documentrepository.http.client.model.Task;
import de.drentech.innihald.documentrepository.service.DocumentService;
import de.drentech.innihald.documentrepository.service.PhysicalFileService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.util.HashMap;

@ApplicationScoped
public class OcrCreation {

    @Inject
    DocumentService documentService;

    @Inject
    PhysicalFileService physicalFileService;

    @Inject
    @RestClient
    TaskClient taskClient;

    @Incoming("document-create")
    public void createOcrOnDocumentCreation(Long documentId) throws InterruptedException {
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
