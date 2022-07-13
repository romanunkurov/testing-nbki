package src.main.java.ru.nbki.sorter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CsvConverter {

    private final String sourcePath;
    private final String targetPath;
    private final boolean append;

    public CsvConverter(String sourcePath, String targetPath, boolean append) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        this.append = append;
    }

    public void doSort() throws IOException {
        List<String> strings = readAllLinesFromFile(sourcePath);

        List<CSVRepresentation> csvRepresentations = convertAndSortCSV(strings);

        writeAllLinesToFile(targetPath, csvRepresentations, append);
    }


    private List<String> readAllLinesFromFile(String path) throws IOException {
        return Files.lines(Path.of(path))
                .collect(Collectors.toList());
    }

    private List<CSVRepresentation> convertAndSortCSV(List<String> csvStrings) {
        List<CSVRepresentation> csvRepresentations = new ArrayList<>();
        csvStrings.remove(0);
        for(String csvString : csvStrings) {
            String[] parts = csvString.split(";");
            int fid = Integer.parseInt(parts[0]);
            String serialNum = parts[1];
            String memberCode = parts[2];
            String accType = parts[3];
            String openedDt = parts[4];
            String accRteCde = parts[5];
            String reportingDt = parts[6];
            String creditLimit = parts[7];
            CSVRepresentation representationNode = new CSVRepresentation();
            representationNode.setFid(fid);
            representationNode.setSerialNum(serialNum);
            representationNode.setMemberCode(memberCode);
            representationNode.setAccType(accType);
            representationNode.setOpenedDt(openedDt);
            representationNode.setAccRteCde(accRteCde);
            representationNode.setReportingDt(reportingDt);
            representationNode.setCreditLimit(creditLimit);
            csvRepresentations.add(representationNode);
        }

        csvRepresentations.sort(Comparator.comparing(CSVRepresentation::getFid));
        return csvRepresentations;
    }

    private void writeAllLinesToFile(String path, List<CSVRepresentation> list, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(path, append);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        list.stream().map(CSVRepresentation::toString).forEach( line -> printWriter.printf("%s" + "%n", line));
        printWriter.close();
    }
}
