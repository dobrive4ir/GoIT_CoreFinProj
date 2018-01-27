package dao;

import domain.Hotel;
import domain.Room;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс содержит в себе методы по работе с базой данных отелей и комнат.
 */

public interface BookingSystemDAO {

    /**
     * Добавляет отель в базу данных.
     *
     * @param hotel отель, который нужно добавить в базу данных
     * @return <tt>true</tt> если отель добавлен, <tt>false</tt> - если отель не добавлен
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    boolean addHotel(Hotel hotel) throws IOException;

    /**
     * Изменяет свойства отеля в базе данных.
     *
     * @param hotel - отель, который содержит новые свойства
     * @return <tt>true</tt>  если свойства изменены, <tt>false</tt> - если свойства не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    boolean changeHotelData(Hotel hotel) throws IOException;

    /**
     * Добавляет комнату в отель.
     *
     * @param hotel отель, в который нужно добавить комнату
     * @param room комната, которую нужно добавить
     * @return <tt>true</tt> если комната добавлена, <tt>false</tt> - если комната не добавлена
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    boolean addRoomToHotel(Hotel hotel, Room room) throws IOException;

    /**
     * Изменяет свойства комнаты в отеле.
     *
     * @param room  комната, которая содержит новые свойства
     * @param hotel отель, в котором нужно изменить данные комнаты
     * @return <tt>true</tt>  если свойства изменены, <tt>false</tt> - если свойства не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    boolean changeRoomData(Room room, Hotel hotel) throws IOException;

    /**
     * Удаляет комнату из отеля.
     *
     * @param room  - комната, которую нужно удалить
     * @param hotel - отель, из которого нужно удалить комнату
     * @return <tt>true</tt> если комната успешно удалена, <tt>false</tt> - если комнату не удалось удалить
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    boolean deleteRoomFromHotel(Room room, Hotel hotel) throws IOException;

    /**
     * Удаляет отель из базы данных.
     *
     * @param hotel - отель, который нужно удалить
     * @return <tt>true</tt> если отель успешно удален, <tt>false</tt> - если отель не удалось удалить
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    boolean deleteHotel(Hotel hotel) throws IOException;

    /**
     * Получение всех отелей.
     *
     * @return List<Hotel> список со всеми отелями в базе данных
     */
    List<Hotel> getAllHotels();

    /**
     * Получение всех комнат в заданном отеле.
     *
     * @param hotel отель, список комнат которого мы хотим получить
     * @return List<Room> список со всеми комнатами заданного отеля
     */
    List<Room> getAllRooms(Hotel hotel);

    /**
     * Производит запись данных в файл 'hotels'.
     *
     * @throws IOException когда произошли ошибки/прервались I/O operations.
     */
    void saveToFile() throws IOException;

    /**
     * Производит чтение данных из файла 'hotels'.
     *
     * @throws IOException            когда произошли ошибки/прервались I/O operations.
     * @throws ClassNotFoundException если база данных не найдена
     */
    void readDBFromFile() throws IOException, ClassNotFoundException;

}
