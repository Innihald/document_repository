package de.drentech.innihald.documentrepository.ocr;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OcrRepository implements PanacheRepository<OcrData> {
}
