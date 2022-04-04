package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.persistence.UserRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class CsvExportService {

    private static final Logger log = getLogger(CsvExportService.class);

    private final UserRepository userRepository;

    public CsvExportService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void writeEmployeesToCsv(Writer writer) {
        List<Product> employees = (List<Product>) userRepository.findAll();
        String header[]={"id", "product date time"};
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header))) {
            for (Product product : employees) {
                csvPrinter.printRecord(product.getId(), product.getProductTime());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }
}
