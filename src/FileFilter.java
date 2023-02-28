import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileFilter {

    public static List<File> getTxtFilesByFilter(File[] files, Predicate<File> action) {
        List<File> result = new ArrayList<>();
        if (files != null) {
            result = Arrays.stream(files)
                    .filter(file -> file.getName().endsWith(".txt"))
                    .filter(file -> action.test(file))
                    .sorted()
                    .collect(Collectors.toList());
        }
        return result;
    }
}
