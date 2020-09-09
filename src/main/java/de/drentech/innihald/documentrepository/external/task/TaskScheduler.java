package de.drentech.innihald.documentrepository.external.task;

import de.drentech.innihald.documentrepository.document.DocumentService;
import io.quarkus.scheduler.Scheduled;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class TaskScheduler {

    //TODO: put value in config
    int numberOfThreads = 2;

    ExecutorService executorService;

    @Inject
    @RestClient
    TaskClient taskClient;

    @Inject
    DocumentService documentService;

    public TaskScheduler() {
        this.executorService = Executors.newFixedThreadPool(this.numberOfThreads);
    }

    @Scheduled(every = "{cron.sequence.fast}")
    public void addTaskToPool() {
        Task task = this.taskClient.getNextTask("document", "create-ocr");

        Long documentId = task.payload.stream()
                .filter(payload -> payload.key.equals("documentId"))
                .map(payload -> Long.valueOf(payload.value))
                .reduce(0L, Long::sum);

        if(documentId != 0) {
            this.executorService.submit(() -> this.documentService.createOcrForDocument(documentId));
        }
    }
}