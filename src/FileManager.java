import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileManager {

    public static void output() {
        String pathname = "/Users/path/path";
        File directory = new File(pathname);

        if (!directory.isDirectory()) {
            System.out.println(pathname + " is not a directory");
            return;
        }

        File[] files = directory.listFiles();

        List<File> emptyTxtFiles = FileFilter.getTxtFilesByFilter(files, file -> file.length() == 0);
        String emptyFiles = Converter.convertFiles(emptyTxtFiles);
        System.out.println("\nEmpty file(s): " + "\n" + emptyFiles);

        List<File> nonEmptyTxtFiles = FileFilter.getTxtFilesByFilter(files, file -> file.length() != 0);
        String nonEmptyTextFileList = Converter.convertFiles(nonEmptyTxtFiles);
        System.out.println("\nNon-Empty file(s): " + "\n" + nonEmptyTextFileList);

        System.out.println("\nTotal count of empty lines in directory: "
                + Counter.countEmptyLinesPerDirectory(nonEmptyTxtFiles));

        Map<String, Integer> map = Counter.countEmptyLinesPerFile(nonEmptyTxtFiles);
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        System.out.println("\nCount of empty lines per file: ");
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.println(entry);
        }
    }
}

