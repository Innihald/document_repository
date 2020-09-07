package de.drentech.innihald.documentrepository.physicalfile;

import javax.ws.rs.FormParam;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

public class FileUploadRequest {
    @FormParam("document")
    public Long documentId;

    @FormParam("file")
    public File file;

    @FormParam("filename")
    public String filename;
}
