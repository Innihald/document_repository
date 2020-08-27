package de.drentech.innihald.documentrepository.resource;

import de.drentech.innihald.documentrepository.domain.model.Document;
import de.drentech.innihald.documentrepository.service.DocumentService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/document")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DocumentResource {

    @Inject
    DocumentService documentService;

    @GET
    public List<Document> getAllDocuments() {
        return this.documentService.getAllDocuments();
    }

    @POST
    @Transactional
    public Document createDocument(Document document) {
        return this.documentService.createDocument(document);
    }
}
