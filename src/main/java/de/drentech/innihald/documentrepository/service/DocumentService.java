package de.drentech.innihald.documentrepository.service;

import de.drentech.innihald.documentrepository.domain.model.Document;
import de.drentech.innihald.documentrepository.domain.repository.DocumentRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.util.List;

@ApplicationScoped
public class DocumentService {

    @Inject
    DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return this.documentRepository.findAll().list();
    }

    public Document createDocumentWithFile(Document document, File file) {
        return document;
    }
}
