package de.drentech.innihald.documentrepository.ocr;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

@ApplicationScoped
public class OcrService {

    @Inject
    OcrRepository ocrRepository;

    @Inject
    OcrUtil ocrUtil;

    @Transactional
    public OcrData createOcrForFile(Path filepath) throws IOException {
        System.out.println(filepath.toString());
        OcrData ocr = new OcrData();

        PDDocument pdf = PDDocument.load(filepath.toAbsolutePath().toFile());

        PDFTextStripper stripper = new PDFTextStripper();
        ocr.text = stripper.getText(pdf);

        if(ocr.text.isBlank()) {
            System.out.println("OCR Text is empty, start OCR...");

            ocr.text = this.ocrUtil.getOcrTextFromPdf(pdf, "deu");
        }

        pdf.close();
        this.ocrRepository.persistAndFlush(ocr);

        return ocr;
    }
}
