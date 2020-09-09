package de.drentech.innihald.documentrepository.ocr;


import de.drentech.innihald.documentrepository.document.Document;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "ocr_data")
public class OcrData {
    @Id @GeneratedValue
    public Long id;

    @Type(type = "text")
    public String text;

    @OneToOne(mappedBy = "ocr")
    public Document document;
}
