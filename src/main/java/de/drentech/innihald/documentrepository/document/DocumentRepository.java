package de.drentech.innihald.documentrepository.document;

import de.drentech.innihald.documentrepository.document.Document;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
class DocumentRepository implements PanacheRepository<Document> {

}
