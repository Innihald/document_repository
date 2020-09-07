package de.drentech.innihald.documentrepository.schedular;

import io.quarkus.scheduler.Scheduled;
import javax.enterprise.context.ApplicationScoped;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class TaskScheduler {

    //TODO: put value in config
    int numberOfThreads = 2;

    ExecutorService executorService;

    public TaskScheduler() {
        this.executorService = Executors.newFixedThreadPool(this.numberOfThreads);
    }

    @Scheduled(every = "{cron.sequence.fast}")
    public void addTaskToPool() {

        this.executorService.submit(() -> this.createOcrForDocument(12L));
    }

    //TODO: move to service
    private void createOcrForDocument(Long documentId) {
        System.out.println(documentId);
    }
}