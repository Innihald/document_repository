package de.drentech.innihald.documentrepository.external.task;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;

@Consumes("application/json")
@Path("/task")
@RegisterRestClient
public interface TaskClient {
    @POST
    @Produces("application/json")
    public Task createTask(Task task);

    @GET
    @Path("/next/{task}/{topic}")
    @Produces("application/json")
    public Task getNextTask(@PathParam String task, @PathParam String topic);
}
