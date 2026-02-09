package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INITIAL_VALUE = 0;
    private static final String DATA_SPLITERATOR = ",";
    private static final String LINE_SEPARATOR = "\n";
    private static final int SUPPLY_POSITION = 0;
    private static final int BUY_POSITION = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readDataFromFile(fromFileName);
        writeDataToFile(data, toFileName);
    }

    private String readDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            int supply = INITIAL_VALUE;
            int buy = INITIAL_VALUE;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split(DATA_SPLITERATOR);
                if (splitLine[0].equals(SUPPLY)) {
                    supply += Integer.parseInt(splitLine[1]);
                } else {
                    buy += Integer.parseInt(splitLine[1]);
                }
            }
            return supply + DATA_SPLITERATOR + buy;
        } catch (IOException exception) {
            throw new RuntimeException("IO EXCEPTION");
        }
    }

    private void writeDataToFile(String data, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String[] dataSplit = data.split(DATA_SPLITERATOR);
            bufferedWriter.write(SUPPLY + DATA_SPLITERATOR
                    + dataSplit[SUPPLY_POSITION] + LINE_SEPARATOR);
            bufferedWriter.write(BUY + DATA_SPLITERATOR
                    + dataSplit[BUY_POSITION] + LINE_SEPARATOR);
            bufferedWriter.write(RESULT + DATA_SPLITERATOR
                    + (Integer.parseInt(dataSplit[SUPPLY_POSITION])
                    - Integer.parseInt(dataSplit[BUY_POSITION])));
        } catch (IOException e) {
            throw new RuntimeException("IO EXCEPTION");
        }
    }
}

