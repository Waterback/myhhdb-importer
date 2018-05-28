package com.wb.myhhdb.myhhdbimporter.importer;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Just prints the body of the In-Body - using the toString() method of the
 * exchanged Object.
 *
 * @author martinh
 *
 */
public class FileProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        // just get the body as a string
        String body = exchange.getIn().getBody(String.class);
        List<String> newlines = new ArrayList<>();
        System.out.println("-------------- process Body ------------------");
        String lines[] = body.split("\\r?\\n");
        for (String line: lines) {
            if (line.contains("IBAN")) {
                String elements[] = line.split(";");
                System.out.println("->" + line);
                exchange.getIn().setHeader("IBAN", elements[1]);
            }
            if (StringUtils.countOccurrencesOf(line, ".") >3 && (!line.startsWith("Buchung")
                    && (!line.startsWith("Zeitraum")))) {
                newlines.add(line);
            }
        }
        exchange.getIn().setBody(newlines);
    }
}