package dao;

import java.io.*;

/**
 * Этот класс содержит в себе методы для чтения и записи данных в базу данных(файлы).
 */
public class DataBaseIO {

    private DataBaseIO() {
    }

    /**
     * Метод считывает данные из базы данных.
     *
     * @param filePath переменная указывающая на путь к файлу в файловой системе
     * @return Object обьект из базы данных
     * @throws IOException            когда произошли ошибки/прервались I/O operations
     * @throws ClassNotFoundException если файл не был найден
     */
    public static Object readDB(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fisU = new FileInputStream(filePath);
        ObjectInputStream oinU = new ObjectInputStream(fisU);
        Object answer = oinU.readObject();
        fisU.close();
        oinU.close();
        return answer;
    }

    /**
     * Метод который записывает данные в базу данных.
     *
     * @param object   обьект который будет записан в базу данных
     * @param filePath переменная указывающая на путь к файлу в файловой системе
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public static void saveDB(Object object, String filePath) throws IOException {
        FileOutputStream fosH = new FileOutputStream(filePath);
        ObjectOutputStream oosH = new ObjectOutputStream(fosH);
        oosH.writeObject(object);
        oosH.flush();
        oosH.close();
    }
}
