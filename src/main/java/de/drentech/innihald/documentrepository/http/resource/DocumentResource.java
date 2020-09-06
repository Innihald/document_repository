package de.drentech.innihald.documentrepository.http.resource;

import de.drentech.innihald.documentrepository.domain.model.Document;
import de.drentech.innihald.documentrepository.http.request.DocumentUploadRequest;
import de.drentech.innihald.documentrepository.service.DocumentService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/document")
@Produces(MediaType.APPLICATION_JSON)
public class DocumentResource {

    @Inject
    DocumentService documentService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Document> getAllDocuments() {
        return this.documentService.getAllDocuments();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Document createDocument(Document document) throws SystemException {
        return this.documentService.persistDocument(document);
    }

    @POST
    @Path("/upload")
    public Document createDocumentAndUploadFile(@MultipartForm DocumentUploadRequest upload) throws IOException, SystemException {
        Document document = new Document();

        document.title = upload.title;
        document.description = upload.description;

        return this.documentService.persistDocumentWithFile(document, upload.file, upload.filename);
    }
}
