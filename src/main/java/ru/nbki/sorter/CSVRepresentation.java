package src.main.java.ru.nbki.sorter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CSVRepresentation implements Comparable<CSVRepresentation> {
    private int fid;
    private String serialNum;
    private String memberCode;
    private String accType;
    private String openedDt;
    private String accRteCde;
    private String reportingDt;
    private String creditLimit;

    @Override
    public String toString() {
        return  fid + ";" + serialNum + ";" + memberCode + ";" + accType + ";" + openedDt + ";" +accRteCde + ";" + reportingDt + ";" + creditLimit;
    }

    @Override
    public int compareTo(CSVRepresentation o) {
        return this.getFid() - o.getFid();
    }
}
