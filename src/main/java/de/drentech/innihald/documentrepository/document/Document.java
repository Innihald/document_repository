package de.drentech.innihald.documentrepository.document;

import de.drentech.innihald.documentrepository.ocr.OcrData;
import de.drentech.innihald.documentrepository.physicalfile.PhysicalFile;

import javax.persistence.*;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;

    public String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    public PhysicalFile file;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ocr_id", referencedColumnName = "id")
    public OcrData ocr;

    public Document(String title, String description) {
        this(title, description, null, null);
    }

    public Document(String title, String description, PhysicalFile file, OcrData ocr) {
        this.title = title;
        this.description = description;
        this.file = file;
        this.ocr = ocr;
    }

    public Document() {

    }
}
