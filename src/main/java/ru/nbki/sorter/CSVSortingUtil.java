package src.main.java.ru.nbki.sorter;

import java.io.*;

public class CSVSortingUtil {

    private static final String CSV = "sourcefile.csv";
    private static final String CSV_NEW = "targetfile.csv";

    public static void main(String[] args) throws IOException {

        CsvConverter converter = new CsvConverter(CSV, CSV_NEW, true);
        converter.doSort();
    }
}
