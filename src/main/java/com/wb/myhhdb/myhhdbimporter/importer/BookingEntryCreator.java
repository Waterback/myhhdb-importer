package com.wb.myhhdb.myhhdbimporter.importer;

import com.wb.myhhdb.myhhdbimporter.entity.BookingCSVEntry;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class BookingEntryCreator implements Processor {

    private DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
    private NumberFormat numberformat = NumberFormat.getInstance(Locale.GERMAN);

    @Override
    public void process(Exchange exchange) throws Exception {
        String body = exchange.getIn().getBody(String.class);
        body = body.replaceAll("s/\\x00//g;","");
        String[] elements = body.split(";");
        BookingCSVEntry entry = new BookingCSVEntry(
                dateFormat.parse(elements[0]),
                dateFormat.parse(elements[1]),
                elements[2],
                elements[3],
                elements[4],
                numberformat.parse(elements[5]).doubleValue(),
                Currency.getInstance(elements[6]),
                numberformat.parse(elements[7]).doubleValue(),
                Currency.getInstance(elements[8]),
                exchange.getIn().getHeader("IBAN", String.class)
        );

        exchange.getIn().setBody(entry);

    }

}
