package com.wb.myhhdb.myhhdbimporter.importer;

import com.wb.myhhdb.myhhdbimporter.entity.BookingCSVEntry;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;

@Component
public class ImportFileReader extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        onException(ConstraintViolationException.class).
            maximumRedeliveries(0).handled(true).process(new AlreadyImportedProcessor());

        from("file:intbox")
                .process(new FileProcessor())
                .split(body())
                .process(new BookingEntryCreator())
                .to("jpa:com.wb.myhhdb.myhhdbimporter.entity.BookingCSVEntry");
    }


    public class AlreadyImportedProcessor implements Processor {

        @Override
        public void process(Exchange exchange) throws Exception {
            System.out.println("=> Already Imported: " + exchange.getIn().getBody(BookingCSVEntry.class));
        }
    }


}
