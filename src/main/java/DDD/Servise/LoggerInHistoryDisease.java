package DDD.Servise;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerInHistoryDisease {
    private int counter = 0;
    private static LoggerInHistoryDisease logger = null;

    public LoggerInHistoryDisease() {
    }

    public void log(String msg, File file) {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yy ' 'hh:mm:ss");
        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            String msgFinal = "[ " + ++counter + " " + ft.format(date) + "] " + msg + "\n";
            byte[] bytes = msgFinal.getBytes();//перевод строки лога в массив байтов
            fos.write(bytes, 0, bytes.length);
        }//запись байтов в файл
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static LoggerInHistoryDisease getInstance() {
        if (logger == null) logger = new LoggerInHistoryDisease();
        return logger;
    }

}
