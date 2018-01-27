package domain;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Класс служит для генерации уникальных ID номеров для таких сущностей,
 * как Отель, Команата, Юзер.
 */
public final class IdGenerator {
    //D:\JavaCore\GoIT_Group9\booking_project\src\resources\idCounters.properties
    private static String filename = "./booking_project/src/resources/idCounters.properties";
    private static Properties properties = new Properties();
    private static long hotelsCounter;
    private static long ordersCounter;
    private static long roomsCounter;
    private static long usersCounter;

    static {
        readFieldsFromFile();
    }


    private IdGenerator() {
    }

    public static void setFilePath(String filePath) {
        filename = filePath;
    }

    /**
     * Медот для присваивания ID
     *
     * @param object обьект которому будет назначен ID
     * @return уникальный ID
     */
    public static long getId(Object object) {
        long answer = 0;
        String className = object.getClass().getSimpleName();

        switch (className) {
            case "Hotel":
                answer = hotelsCounter++;
                writeFieldsToFile();
                break;
            case "Room":
                answer = roomsCounter++;
                writeFieldsToFile();
                break;
            case "User":
                answer = usersCounter++;
                writeFieldsToFile();
                break;
        }

        return answer;
    }

    /**
     * метод, загружающий значение ID счетчиков из файла
     */
    private static void readFieldsFromFile() {
        try (FileInputStream fis = new FileInputStream(filename)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        hotelsCounter = Long.parseLong((String) properties.get("hotelsCounter"));
        ordersCounter = Long.parseLong((String) properties.get("ordersCounter"));
        roomsCounter = Long.parseLong((String) properties.get("roomsCounter"));
        usersCounter = Long.parseLong((String) properties.get("usersCounter"));
    }

    /**
     * метод, загружающий значение ID счетчиков в файла
     */
    private static void writeFieldsToFile() {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            properties.setProperty("hotelsCounter", String.valueOf(hotelsCounter));
            properties.setProperty("ordersCounter", String.valueOf(ordersCounter));
            properties.setProperty("roomsCounter", String.valueOf(roomsCounter));
            properties.setProperty("usersCounter", String.valueOf(usersCounter));

            properties.store(fos, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
