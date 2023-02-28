import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static String convertFiles(List<File> files) {
        return files.stream()
                .map(file -> file.getName())
                .sorted()
                .collect(Collectors.joining("\n"));
    }
}
