package de.drentech.innihald.documentrepository.http.resource;

import de.drentech.innihald.documentrepository.domain.model.Document;
import de.drentech.innihald.documentrepository.domain.model.PhysicalFile;
import de.drentech.innihald.documentrepository.http.request.FileUploadRequest;
import de.drentech.innihald.documentrepository.service.DocumentService;
import de.drentech.innihald.documentrepository.service.PhysicalFileService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/file")
public class PhysicalFileResource {

    @Inject
    PhysicalFileService physicalFileService;

    @Inject
    DocumentService documentService;

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public PhysicalFile uploadFile(@MultipartForm FileUploadRequest upload) throws IOException {

        PhysicalFile file = this.physicalFileService.createFile(upload.file, upload.filename);

        Document document = this.documentService.getDocumentById(upload.documentId);
        document.file = file;
        this.documentService.persistDocument(document);

        return file;
    }
}
