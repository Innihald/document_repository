package de.drentech.innihald.documentrepository.document;

import de.drentech.innihald.documentrepository.document.Document;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentRepository implements PanacheRepository<Document> {

}
