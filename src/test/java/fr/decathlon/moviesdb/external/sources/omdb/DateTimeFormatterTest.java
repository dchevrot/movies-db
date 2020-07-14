package fr.decathlon.moviesdb.external.sources.omdb;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeFormatterTest {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);


    @Test
    void OMDBMovieDateFormatter() {
        System.out.println(LocalDate.parse("05 May 2017", DATE_FORMAT));
    }
}
