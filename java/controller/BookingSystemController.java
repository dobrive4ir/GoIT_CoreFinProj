package controller;

import dao.BookingSystemDAO;
import dao.BookingSystemDAOImpl;
import domain.BookingInfo;
import domain.Hotel;
import domain.Room;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс, который содержит методы:
 * для работы с данными отелей через вызов методов BookingSystemDAOImpl;
 * поиска отеля по городу, поиска отеля по имени, поиск отеля по номеру в регистре города,
 * бронирования комнаты пользователем, отмены бронирования пользователем.
 */

public final class BookingSystemController {

    private final BookingSystemDAO bookSysDAO;

    public BookingSystemController() {
        this.bookSysDAO = new BookingSystemDAOImpl();
    }

    /**
     * Вызывает метод добавления отеля.
     *
     * @param hotel отель, который нужно добавить в базу данных
     * @return <tt>true</tt> если отель добавлен, <tt>false</tt> - если отель не добавлен
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean addHotel(Hotel hotel) throws IOException {
        return bookSysDAO.addHotel(hotel);
    }

    /**
     * Вызывает метод изменения свойства отеля.
     *
     * @param hotel - отель, который содержит новые свойства
     * @return <tt>true</tt>  если свойства изменены, <tt>false</tt> - если свойства не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean changeHotelData(Hotel hotel) throws IOException{
        return bookSysDAO.changeHotelData(hotel);
    }

    /**
     * Вызывает метод добавления комнаты в отель.
     *
     * @param hotel отель, в который нужно добавить комнату
     * @param room комната, которую нужно добавить
     * @return <tt>true</tt> если комната добавлена, <tt>false</tt> - если комната не добавлена
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean addRoomToHotel(Hotel hotel, Room room) throws IOException {
        return bookSysDAO.addRoomToHotel(hotel, room);
    }

    /**
     * Вызывает метод изменения свойств комнаты в отеле.
     *
     * @param room  комната, которая содержит новые свойства
     * @param hotel отель, в котором нужно изменить данные комнаты
     * @return <tt>true</tt>  если свойства изменены, <tt>false</tt> - если свойства не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean changeRoomData(Room room, Hotel hotel) throws IOException {
        return bookSysDAO.changeRoomData(room, hotel);
    }

    /**
     * Вызывает метод удаления комнаты из отеля.
     *
     * @param room  - комната, которую нужно удалить
     * @param hotel - отель, из которого нужно удалить комнату
     * @return <tt>true</tt> если комната успешно удалена, <tt>false</tt> - если комнату не удалось удалить
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean deleteRoomFromHotel(Room room, Hotel hotel) throws IOException {
        return bookSysDAO.deleteRoomFromHotel(room, hotel);
    }

    /**
     * Вызывает метод удаления отеля из базы данных.
     *
     * @param hotel - отель, который нужно удалить
     * @return <tt>true</tt> если отель успешно удален, <tt>false</tt> - если отель не удалось удалить
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean deleteHotel(Hotel hotel) throws IOException {
        return bookSysDAO.deleteHotel(hotel);
    }

    /**
     * Ищет отели по имени.
     *
     * @param hotelName имя отеля, который нужно найти
     * @return List<Hotel> список отелей с заданым в параметере именем
     */
    public List<Hotel> searchHotelByName(String hotelName) {
        return getAllHotels().stream()
                .filter(hotel -> hotel.getName().equalsIgnoreCase(hotelName))
                .collect(Collectors.toList());
    }

    /**
     * Ищет отели в городе, указанном в параметрах.
     *
     * @param cityName город, в котором нужно найти отели
     * @return List<Hotel> список отелей в заданом городе
     */
    public List<Hotel> searchHotelByCity(String cityName) {
        return getAllHotels().stream()
                .filter(hotel -> hotel.getCity().equalsIgnoreCase(cityName))
                .collect(Collectors.toList());
    }

    /**
     * Ищет отель с указанным номером в регистре города.
     *
     * @param cityRegister номер здания в городском регистре, по которому нужно найти отель
     * @return Hotel искомый отель, если он найден в базе данных, null - если отель в базе данных не найден
     */
    public Hotel getHotelByCityRegister (String cityRegister) {
        Hotel answer = null;
        if (getAllHotels().stream().anyMatch(hotelFromDB -> hotelFromDB.getCityRegister().equals(cityRegister))) {
            answer = getAllHotels().stream()
                    .filter(hotelFromDB -> hotelFromDB.getCityRegister().equals(cityRegister))
                    .findFirst()
                    .get();
        }
        return answer;
    }

    /**
     * Бронирует комнату на определенную дату.
     *
     * @param room комната, которую нужно забронировать
     * @param bookingInfo информация про бронь
     * @return <tt>true</tt>  если комната успешно забронирована, <tt>false</tt> - если комнату не удалось забронировать
     */
    public boolean bookRoomByUser(Hotel hotel, Room room, BookingInfo bookingInfo) throws IOException {
        boolean answer = room.bookTheRoom(bookingInfo);
        changeRoomData(room, hotel);
        flush();
        return answer;
    }

    /**
     * Отменяет регестрацию комнаты в системе.
     *
     * @param room комната, с которой нужно снять бронь
     * @param bookingInfo информация про бронь, которую необходимо снять
     * @return <tt>true</tt>  если бронь успешно снята, <tt>false</tt> - если бронь не удалось снять
     */
    public boolean bookingCancellation(Hotel hotel, Room room, BookingInfo bookingInfo) throws IOException {

        boolean answer = false;


        List<Room> roomList = bookSysDAO.getAllRooms(hotel);
        int index = roomList.indexOf(room);

        if (!roomList.get(index).isFree(bookingInfo)) {
            answer = room.getBookingInfoList().remove(bookingInfo);
            changeRoomData(room, hotel);
            flush();
        }

        return answer;
    }

    /**
     * Получение всех отелей.
     *
     * @return List<Hotel> список со всеми отелями в базе данных
     */
    public List<Hotel> getAllHotels() {
        return bookSysDAO.getAllHotels();
    }

    /**
     * Получение всех комнат в заданном отеле.
     *
     * @param hotel отель, список комнат которого мы хотим получить
     * @return List<Room> список со всеми комнатами заданного отеля
     */
    public List<Room> getAllRooms(Hotel hotel) {
        return bookSysDAO.getAllRooms(hotel);
    }

    /**
     * Вызывает метод для записи запись данных в файл 'hotels'.
     *
     * @throws IOException когда произошли ошибки/прервались I/O operations.
     */
    public void flush () throws IOException {
        this.bookSysDAO.saveToFile();
    }

}
