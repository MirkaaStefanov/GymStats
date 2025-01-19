package com.example.GymStats.services.impl;

import com.example.GymStats.models.entity.PDF;
import com.example.GymStats.repositories.PDFRepository;
import com.example.GymStats.services.WebScrapperService;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WebScrapperServiceImpl implements WebScrapperService {

    private static final String URL = "https://www.gymnastics.sport/site/events/searchresults.php#filter";
    private static final String DOWNLOAD_DIR = "src/main/resources/downloads"; // Directory to save PDFs
    private final ChromeDriver driver;
    private final PDFRepository pdfRepository;


    @Override
    public void scrape() {
        try {
            //the path to chromedriver
            System.setProperty("webdriver.chrome.driver", "http://www.google.com/");
            // Open the URL
            driver.get(URL);

            // Wait for the page to load (if necessary)
            Thread.sleep(5000); // Add WebDriverWait if more precise waiting is needed

            // Find the table body <tbody>
            WebElement tbody = driver.findElement(By.cssSelector("table > tbody"));

            // Get all <tr> elements within the <tbody>
            List<WebElement> rows = tbody.findElements(By.tagName("tr"));

            // Iterate over the rows and process them
            for (WebElement row : rows) {
                String rowText = row.getText();

                if (rowText.contains("MAG")) {
                    System.out.print("Row: " + row.getText() + " ");

                    // Check for the PDF link
                    List<WebElement> pdfLinks = row.findElements(By.cssSelector("a[href*='asset.php']"));
                    if (!pdfLinks.isEmpty()) {
                        // If a PDF link is found, print the first one (or handle as needed)
                        WebElement pdfLink = pdfLinks.get(0);
                        String pdfUrl = pdfLink.getAttribute("href");
                        System.out.println("PDF Link: " + pdfUrl);
                        Optional<PDF> optionalPDF = pdfRepository.findByFileUrl(pdfUrl);
                        if (!optionalPDF.isPresent()) {
                            downloadFile(pdfUrl);
                            downloadAndSavePdf(pdfUrl);
                        }
                    } else {
                        System.out.println("No PDF Link found for this row.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }


    // Resolve relative URL to an absolute URL
    private void downloadFile(String fileURL) throws IOException {
        // Create the downloads directory if it doesn't exist
        java.nio.file.Path downloadsDir = java.nio.file.Paths.get(DOWNLOAD_DIR);
        if (!java.nio.file.Files.exists(downloadsDir)) {
            java.nio.file.Files.createDirectories(downloadsDir);
        }

        // Extract the file name from the URL and add a .pdf extension if necessary
        String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
        if (!fileName.endsWith(".pdf")) {
            fileName += ".pdf";
        }

        // URL contains illegal characters for a file system on Windows (so the next method resolve didn't work for me)
        fileName = fileName.replaceAll("[\\\\/:*?\"<>|]", "_");

        // Set the target path for the downloaded file
        java.nio.file.Path targetPath = downloadsDir.resolve(fileName);

        // Download the file from the URL
        java.net.URL url = new java.net.URL(fileURL);
        try (java.io.InputStream in = url.openStream()) {
            java.nio.file.Files.copy(in, targetPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

        // Print confirmation of the download
        System.out.println("Downloaded: " + targetPath);
    }

    private void downloadAndSavePdf(String fileURL) throws IOException {
        // Extract the file name from the URL
        String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
        if (!fileName.endsWith(".pdf")) {
            fileName += ".pdf";
        }

//        java.net.URL url = new URL(fileURL);
//        byte[] pdfContent;
//        try (var inputStream = url.openStream()) {
//            pdfContent = inputStream.readAllBytes();
//        }

        // Save the PDF to the database
        PDF pdf = new PDF();
        pdf.setFileUrl(fileURL);
        pdf.setFileName(fileName);
        pdf.setFilePath("src/main/resources/downloads/" + fileName);
        pdf.setCreatedAt(LocalDateTime.now());
        pdfRepository.save(pdf);

        System.out.println("Saved PDF to database: " + fileName);
    }
}
