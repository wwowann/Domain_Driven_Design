package DDD.Servise;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HistoryDisease {
    private final File filename;

    public HistoryDisease(File filename) {
        this.filename = filename;
    }

    public void toPrintHistoryDisease() {
        try {
            FileReader fileReader = new FileReader(filename);
            int i;
            while ((i = fileReader.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public File getFilename() {
        return filename;
    }
}
