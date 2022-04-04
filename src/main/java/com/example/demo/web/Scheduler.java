package com.example.demo.web;

import com.example.demo.model.Product;
import com.example.demo.persistence.UserRepository;
import com.example.demo.service.CsvExportService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.io.Writer;

import static org.slf4j.LoggerFactory.getLogger;


@Component
public class Scheduler {
    private final UserRepository userRepository;

    public Scheduler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private static final Logger log = getLogger(Scheduler.class);


    @Scheduled(cron = "0 * 9 * * ?")
    public void cronJobSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Java cron job expression:: " + strDate);
    }
    @Scheduled(fixedRate = 10000)
    public void fixedRateSch() throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Fixed Rate scheduler:: " + strDate);
        List<Product> employees = (List<Product>) userRepository.findAll();
        String header[]={"id", "product date time"};
        String name="abc_"+System.currentTimeMillis()+".csv";
        Writer writer=new FileWriter("C:\\Users\\kanav\\Documents\\"+name);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header))) {
            for (Product product : employees) {
                csvPrinter.printRecord(product.getId(), product.getProductTime());
            }
            csvPrinter.flush();
            csvPrinter.close();
            System.out.println("Wrote");
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }
}