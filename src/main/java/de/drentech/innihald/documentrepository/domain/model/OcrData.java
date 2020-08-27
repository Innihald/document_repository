package de.drentech.innihald.documentrepository.domain.model;


import javax.persistence.*;

@Entity
@Table(name = "ocr_data")
public class OcrData {
    @Id @GeneratedValue
    public Long id;

    public String text;

    @OneToOne(mappedBy = "ocr")
    public Document document;
}
