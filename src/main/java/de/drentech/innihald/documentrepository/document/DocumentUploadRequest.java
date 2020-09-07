package de.drentech.innihald.documentrepository.document;

import javax.ws.rs.FormParam;
import java.io.File;

public class DocumentUploadRequest {
    @FormParam("title")
    public String title;

    @FormParam("description")
    public String description;

    @FormParam("file")
    public File file;

    @FormParam("filename")
    public String filename;
}
