package de.drentech.innihald.documentrepository.document;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ui/document")
@Produces(MediaType.TEXT_HTML)
public class DocumentController {

    @Inject
    DocumentService documentService;

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance list();
    }

    @GET
    public TemplateInstance listAction() {
        return Templates.list().data("documents", this.documentService.getAllDocuments());
    }
}
