package de.drentech.innihald.documentrepository.domain.model;

import javax.persistence.*;
import java.nio.file.Path;

@Entity
@Table(name = "physical_file")
public class PhysicalFile {
    @Id @GeneratedValue
    public Long id;

    // public Path path;

    public String filename;

    @OneToOne(mappedBy = "file")
    public Document document;
}
