package de.drentech.innihald.documentrepository.domain.model;

public class Document {
    private String title;

    private String description;

    private PhysicalFile file;

    private OcrData ocr;

    public Document(String title, String description) {
        this(title, description, null, null);
    }

    public Document(String title, String description, PhysicalFile file, OcrData ocr) {
        this.title = title;
        this.description = description;
        this.file = file;
        this.ocr = ocr;
    }
}
