package dao;

import domain.Hotel;
import domain.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс имплементирующий интерфейс BookingSystemDAO.
 * Реализует для работы с базой данных отелей и комнат:
 * добавление, изменение, удаление.
 * Содержит методы записи и чтения из файла 'hotels'.
 */

public class BookingSystemDAOImpl implements BookingSystemDAO {

    private static final String HOTELSFILE = "./booking_project/src/resources/hotels";
    private List<Hotel> hotelList;

    public BookingSystemDAOImpl() {

        this.hotelList = new ArrayList<>();

        try {
            readDBFromFile();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    /**
     * Добавляет отель в базу данных.
     *
     * @param hotel отель, который нужно добавить в базу данных
     * @return <tt>true</tt> если отель добавлен, <tt>false</tt> - если отель не добавлен
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    @Override
    public boolean addHotel(Hotel hotel) throws IOException {

        boolean answer = hotelList.stream()
                .noneMatch(hotel::equals)
                && hotelList.add(hotel);

        saveToFile();

        return answer;
    }

    /**
     * Изменяет свойства отеля в базе данных.
     *
     * @param hotel - отель, который содержит новые свойства
     * @return <tt>true</tt>  если свойства изменены, <tt>false</tt> - если свойства не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    @Override
    public boolean changeHotelData(Hotel hotel) throws IOException {

        boolean answer = hotelList.stream()
                .anyMatch(hotel::equals);

        if (answer) {
            Hotel foundedHotel = hotelList.stream()
                    .filter(hotel::equals)
                    .findFirst()
                    .get();
            foundedHotel.setName(hotel.getName());
        }

        saveToFile();

        return answer;
    }

    /**
     * Добавляет комнату в отель.
     *
     * @param hotel отель, в который нужно добавить комнату
     * @param room комната, которую нужно добавить
     * @return <tt>true</tt> если комната добавлена, <tt>false</tt> - если комната не добавлена
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    @Override
    public boolean addRoomToHotel(Hotel hotel, Room room) throws IOException {

        boolean answer = hotel.getRoomList().stream()
                .noneMatch(room::equals)
                && hotel.getRoomList().add(room);

        saveToFile();

        return answer;
    }

    /**
     * Изменяет свойства комнаты в отеле.
     *
     * @param room  комната, которая содержит новые свойства
     * @param hotel отель, в котором нужно изменить данные комнаты
     * @return <tt>true</tt>  если свойства изменены, <tt>false</tt> - если свойства не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    @Override
    public boolean changeRoomData(Room room, Hotel hotel) throws IOException {

        boolean answer = hotel.getRoomList().stream()
                .anyMatch(hotel::equals);

        if (answer) {
            Room foundedRoom = hotel.getRoomList().stream()
                    .filter(room::equals)
                    .findFirst()
                    .get();
            foundedRoom.setPersons(room.getPersons());
            foundedRoom.setPrice(room.getPrice());
        }

        saveToFile();

        return answer;
    }

    /**
     * Удаляет комнату из отеля.
     *
     * @param room  - комната, которую нужно удалить
     * @param hotel - отель, из которого нужно удалить комнату
     * @return <tt>true</tt> если комната успешно удалена, <tt>false</tt> - если комнату не удалось удалить
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    @Override
    public boolean deleteRoomFromHotel(Room room, Hotel hotel) throws IOException {

        boolean answer = hotel.getRoomList().stream().anyMatch(room::equals)
                && hotel.getRoomList().remove(room);

        saveToFile();

        return answer;
    }

    /**
     * Удаляет отель из базы данных.
     *
     * @param hotel - отель, который нужно удалить
     * @return <tt>true</tt> если отель успешно удален, <tt>false</tt> - если отель не удалось удалить
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    @Override
    public boolean deleteHotel(Hotel hotel) throws IOException {

        boolean answer = hotelList.stream().anyMatch(hotel::equals) && hotelList.remove(hotel);

        saveToFile();

        return answer;

    }

    /**
     * Получение всех отелей.
     *
     * @return List<Hotel> список со всеми отелями в базе данных
     */
    @Override
    public List<Hotel> getAllHotels() {
        return hotelList;
    }

    /**
     * Получение всех комнат в заданном отеле.
     *
     * @param hotel отель, список комнат которого мы хотим получить
     * @return List<Room> список со всеми комнатами заданного отеля
     */
    @Override
    public List<Room> getAllRooms(Hotel hotel) {
        return hotel.getRoomList();
    }

    /**
     * Производит запись данных в файл 'hotels'.
     *
     * @throws IOException когда произошли ошибки/прервались I/O operations.
     */
    @Override
    public void saveToFile() throws IOException {
        DataBaseIO.saveDB(hotelList, HOTELSFILE);

    }

    /**
     * Производит чтение данных из файла 'hotels'.
     *
     * @throws IOException            когда произошли ошибки/прервались I/O operations.
     * @throws ClassNotFoundException если база данных не найдена
     */
    @Override
    public void readDBFromFile() throws IOException, ClassNotFoundException {
        hotelList = (List<Hotel>) DataBaseIO.readDB(HOTELSFILE);
    }
}
