package de.drentech.innihald.documentrepository.service;

import de.drentech.innihald.documentrepository.domain.model.PhysicalFile;
import de.drentech.innihald.documentrepository.domain.repository.PhysicalFileRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@ApplicationScoped
public class PhysicalFileService {

    @Inject
    PhysicalFileRepository physicalFileRepository;

    //TODO: move to config value
    String fileRepoPath = "/home/marian/.temp/innihald";

    @Transactional
    public PhysicalFile createFile(File file, String filename) throws IOException {

        // move file to folder
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        filename = now.format(formatter) + "_" + UUID.randomUUID() + "_" + filename;
        Path newFile = Paths.get(fileRepoPath + "/" + filename);

        Files.copy(Paths.get(file.getPath()), newFile, StandardCopyOption.REPLACE_EXISTING);

        // create object
        PhysicalFile physicalFile = new PhysicalFile();
        physicalFile.filename = filename;
        physicalFile.path = newFile.toAbsolutePath().toString();

        // save object
        this.physicalFileRepository.persistAndFlush(physicalFile);

        return physicalFile;
    }

    public PhysicalFile getFileById(Long id) {

        return this.physicalFileRepository.findById(id);
    }
}
