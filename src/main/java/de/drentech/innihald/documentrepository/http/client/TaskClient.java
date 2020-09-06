package de.drentech.innihald.documentrepository.http.client;

import de.drentech.innihald.documentrepository.http.client.model.Task;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Consumes("application/json")
@Path("/task")
@RegisterRestClient
public interface TaskClient {
    @POST
    @Produces("application/json")
    public Task createTask(Task task);
}
