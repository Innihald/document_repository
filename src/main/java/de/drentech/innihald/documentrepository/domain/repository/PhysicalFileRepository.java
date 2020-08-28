package de.drentech.innihald.documentrepository.domain.repository;

import de.drentech.innihald.documentrepository.domain.model.PhysicalFile;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PhysicalFileRepository implements PanacheRepository<PhysicalFile> {
}
