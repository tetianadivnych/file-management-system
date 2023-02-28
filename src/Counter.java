import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Counter {

    public static Map<String, Integer> countEmptyLinesPerFile(List<File> files) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (File file : files) {
            try {
                Scanner scanner = new Scanner(file);
                int count = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.trim().equals("")) {
                        count++;
                    }
                }
                scanner.close();
                counts.put(file.getName(), count);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return counts;
    }

    public static int countEmptyLinesPerDirectory(List<File> files) {
        int count = 0;
        for (File file : files) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.trim().equals("")) {
                        count++;
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
}
