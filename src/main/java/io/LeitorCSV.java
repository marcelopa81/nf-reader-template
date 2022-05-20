package io;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class LeitorCSV<T> {

    public List<T> leia(File file, Class tipo) {

        try (var fileReader = new FileReader(file, StandardCharsets.ISO_8859_1)) {

            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(fileReader)
                    .withSeparator(';')
                    .withType(tipo)
                    .withThrowExceptions(false)
                    .withIgnoreQuotations(true)
                    .build();

            return csvToBean.parse();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
