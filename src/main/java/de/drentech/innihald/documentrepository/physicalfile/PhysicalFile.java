package de.drentech.innihald.documentrepository.physicalfile;

import de.drentech.innihald.documentrepository.document.Document;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "physical_file")
public class PhysicalFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String path;

    public String filename;

    @OneToOne(mappedBy = "file")
    @XmlTransient
    @JsonbTransient
    public Document document;

    public PhysicalFile() {
    }
}
