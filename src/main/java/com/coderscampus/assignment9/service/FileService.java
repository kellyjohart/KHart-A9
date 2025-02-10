package com.coderscampus.assignment9.service;
import com.coderscampus.assignment9.domain.Recipe;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.QuoteMode;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {
    private final ResourceLoader resourceLoader;

    public FileService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<CSVRecord> readFile(String filePath) {
        Resource resource = resourceLoader.getResource(filePath);
        List<CSVRecord> records = new ArrayList<>();

        try (Reader in = new InputStreamReader(resource.getInputStream())) {

                records = CSVFormat
                    .DEFAULT
                    .builder()
                    .setHeader()
                    .setIgnoreSurroundingSpaces(true)
                    .setEscape('\\')
                    .setQuote('"')
                    .setQuoteMode(QuoteMode.ALL)
                    .setDelimiter(',')
                    .setTrailingDelimiter(true)
                    .build()
                    .parse(in)
                    .getRecords();

            } catch(IOException e){
                System.out.println("There was an issue reading csv file: " + e.getMessage());

            }
            return records;
        }
    }

