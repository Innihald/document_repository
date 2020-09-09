package de.drentech.innihald.documentrepository.ocr;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.enterprise.context.ApplicationScoped;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@ApplicationScoped
public class OcrUtil {
    public String getOcrTextFromPdf(PDDocument pdf, String languageCode) throws IOException {
        PDFRenderer renderer = new PDFRenderer(pdf);
        StringBuilder ocrText = new StringBuilder();

        for (int pageNumber = 0; pageNumber < pdf.getNumberOfPages(); pageNumber++) {
            BufferedImage pageImage = renderer.renderImageWithDPI(pageNumber, 300, ImageType.RGB);  //TODO: use this to generate thumbnail

            File tempFile = File.createTempFile("tempfile_" + pageNumber, ".png");
            ImageIO.write(pageImage, "png", tempFile);

            String ocrCommand = String.format("tesseract -l %s --oem 1 %s -", languageCode, tempFile.getAbsolutePath());

            System.out.println("Command run: " + ocrCommand);

            try {
                ProcessBuilder processBuilder = new ProcessBuilder();
                processBuilder.command("bash", "-c", ocrCommand);

                Process process = processBuilder.start();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream())
                );

                String line;
                while ((line = reader.readLine()) != null) {
                    ocrText.append(line).append("\n\r");
                }

                int exitVal = process.waitFor();

            } catch (Exception e) {
                ocrText.append("ERROR_NO_OCR");
                e.printStackTrace();
            } finally {
                boolean delete = tempFile.delete();
            }
        }
        return ocrText.toString();
    }
}
