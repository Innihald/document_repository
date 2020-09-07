package de.drentech.innihald.documentrepository.physicalfile;

import de.drentech.innihald.documentrepository.physicalfile.PhysicalFile;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PhysicalFileRepository implements PanacheRepository<PhysicalFile> {
}
