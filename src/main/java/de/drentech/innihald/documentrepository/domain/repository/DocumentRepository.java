package de.drentech.innihald.documentrepository.domain.repository;

import de.drentech.innihald.documentrepository.domain.model.Document;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class DocumentRepository implements PanacheRepository<Document> {

}
