package de.drentech.innihald.documentrepository.service;

import de.drentech.innihald.documentrepository.domain.model.Document;
import de.drentech.innihald.documentrepository.domain.model.PhysicalFile;
import de.drentech.innihald.documentrepository.domain.repository.DocumentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class DocumentService {

    @Inject
    DocumentRepository documentRepository;

    @Inject
    PhysicalFileService physicalFileService;

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
        this.documentRepository.persist(document);

        //TODO: send message to queue that new document was created

        return document;
    }
}
