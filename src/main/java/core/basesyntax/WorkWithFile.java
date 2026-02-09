package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(fromFileName));
                     BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(toFileName))) {

            int supply = 0;
            int buy = 0;
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] splitLine = line.split(",");
                if (splitLine[0].equals("supply")) {
                    supply += Integer.parseInt(splitLine[1]);
                } else {
                    buy += Integer.parseInt(splitLine[1]);
                }
            }
            bufferedWriter.write("supply," + supply + "\n");
            bufferedWriter.write("buy," + buy + "\n");
            bufferedWriter.write("result," + (supply - buy) + "\n");
        } catch (IOException exception) {
            throw new RuntimeException("IO EXCEPTION", exception);
        }
    }
}

