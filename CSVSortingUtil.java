import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CSVSortingUtil {

    public static final String CSV = "testfile.csv";
    public static final String CSV_NEW = "newtest.csv";
    public static boolean append = true;
    public static List<String> listStrings = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        ArrayList<CSVRepresentation> csvRepresentations = convertAndSortCSV(readAllLinesFromFile(CSV));

        writeAllLinesToFile(CSV_NEW, listStrings);

    }

    static List<String> readAllLinesFromFile(String path) throws IOException {

        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while( (line = bufferedReader.readLine()) != null){
            listStrings.add(line);
        }
        bufferedReader.close();

        return listStrings;

    }

    static ArrayList<CSVRepresentation> convertAndSortCSV(List<String> csvStrings) {
        ArrayList<CSVRepresentation> csvRepresentations = new ArrayList<>();
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

    static void writeAllLinesToFile(String path, List<String> list) throws IOException {
        FileWriter fileWriter = new FileWriter(path, append);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (String line : list){
            printWriter.printf("%s" + "%n", line);
        }
        printWriter.close();
    }


    @Data
    private static class CSVRepresentation implements Comparable<CSVRepresentation> {
        private int fid;
        private String serialNum;
        private String memberCode;
        private String accType;
        private String openedDt;
        private String accRteCde;
        private String reportingDt;
        private String creditLimit;

        @Override
        public int compareTo(CSVRepresentation o) {
            return this.fid - o.getFid();
        }
    }
}
