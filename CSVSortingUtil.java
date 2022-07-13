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
        readAllLinesFromFile(CSV);
        for(String aStudentString: listStrings){
            System.out.println(aStudentString +"\n");
        }

        convertToStudents(listStrings);

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

    static ArrayList<CSVRepresentation> convertToStudents(List<String> studentsStrings) {
        ArrayList<CSVRepresentation> csvRepresentations = new ArrayList<>();
        studentsStrings.remove(0);
        for(String studentString : studentsStrings) {
            String[] parts = studentString.split(";");
            int FID = Integer.parseInt(parts[0]);
            String SERIAL_NUM = parts[1];
            String MEMBER_CODE = parts[2];
            String ACCT_TYPE = parts[3];
            String OPENED_DT = parts[4];
            String ACCT_RTE_CDE = parts[5];
            String REPORTING_DT = parts[6];
            String CREDIT_LIMIT = parts[7];
            CSVRepresentation representationNode = new CSVRepresentation();
            representationNode.setFID(String.valueOf(FID));
            representationNode.setSERIAL_NUM(SERIAL_NUM);
            representationNode.setMEMBER_CODE(MEMBER_CODE);
            representationNode.setACCT_TYPE(ACCT_TYPE);
            representationNode.setOPENED_DT(OPENED_DT);
            representationNode.setACCT_RTE_CDE(ACCT_RTE_CDE);
            representationNode.setREPORTING_DT(REPORTING_DT);
            representationNode.setCREDIT_LIMIT(CREDIT_LIMIT);
            csvRepresentations.add(representationNode);
        }

        csvRepresentations.sort(Comparator.comparing(CSVRepresentation::getFID));
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
    private static class CSVRepresentation {
        private String FID;
        private String SERIAL_NUM;
        private String MEMBER_CODE;
        private String ACCT_TYPE;
        private String OPENED_DT;
        private String ACCT_RTE_CDE;
        private String REPORTING_DT;
        private String CREDIT_LIMIT;

    }
}
