package de.drentech.innihald.documentrepository.external.task;

import java.util.ArrayList;
import java.util.List;

public class Task {
    public String topic;

    public String task;

    public List<Payload> payload;

    public Task() {
        this.payload = new ArrayList<>();
    }

    public static class Payload {
        public String key;

        public String value;

        public Payload() {
            this("", "");
        }

        public Payload(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
