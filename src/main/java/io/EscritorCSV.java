package io;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;

public class EscritorCSV {

    public <T> void escreve(List<T> csvBeans, Path path) {

        try (Writer writer = new FileWriter(path.toString())) {
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(';')
                    .build();


            sbc.write(csvBeans);
        } catch (IOException | CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {

            throw new RuntimeException("Houve um problema ao ecrever o CSV", e);
        }
    }
}
