import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager {

    public static void main(String[] args) {
        output();
    }


    public static void output() {
        String pathname = "/Users/tania/Desktop/folders/softserve/java-fundamentals-project";
        File directory = new File(pathname);
        if (!directory.isDirectory()) {
            System.out.println(pathname + " is not a directory");
            return;
        }
        File[] files = directory.listFiles();

        List<File> emptyTxtFiles = findEmptyTxtFiles(files);
        String emptyFiles = convertFiles(emptyTxtFiles);
        System.out.println("\nEmpty file(s): " + "\n" + emptyFiles);

        List<File> nonEmptyTxtFiles = findNonEmptyTxtFiles(files);
        String nonEmptyTextFileList = convertFiles(nonEmptyTxtFiles);
        System.out.println("\nNon-Empty file(s): " + "\n" + nonEmptyTextFileList);

        System.out.println("\nTotal count of empty lines in directory: "
                + countEmptyLinesPerDirectory(nonEmptyTxtFiles));

        Map<String, Integer> map = countEmptyLinesPerFile(nonEmptyTxtFiles);
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        System.out.println("\nCount of empty lines per file: ");
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry);
        }
    }

    private static String convertFiles(List<File> files) {
        return files.stream()
                .map(file -> file.getName())
                .sorted()
                .collect(Collectors.joining("\n"));
    }

    private static List<File> findEmptyTxtFiles(File[] files) {
        List<File> result = new ArrayList<>();
        if (files != null) {
            result = Arrays.stream(files)
                    .filter(file -> file.getName().endsWith(".txt"))
                    .filter(file -> file.length() == 0)
                    .sorted()
                    .collect(Collectors.toList());
        }
        return result;
    }

    public static List<File> findNonEmptyTxtFiles(File[] files) {
        List<File> result = new ArrayList<>();
        if (files != null) {
            result = Arrays.stream(files)
                    .filter(file -> file.getName().endsWith(".txt"))
                    .filter(file -> file.length() != 0)
                    .collect(Collectors.toList());
        }
        return result;
    }


    public static int countEmptyLinesPerDirectory(List<File> files) {
        int counter = 0;
        for (File file : files) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.trim().equals("")) {
                        counter++;
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return counter;
    }

    public static Map<String, Integer> countEmptyLinesPerFile(List<File> files) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (File file : files) {
            try {
                Scanner scanner = new Scanner(file);
                int counter = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.trim().equals("")) {
                        counter++;
                    }
                }
                scanner.close();
                counts.put(file.getName(), counter);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return counts;
    }
}

