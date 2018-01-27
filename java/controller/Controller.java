package controller;

import domain.BookingInfo;
import domain.Hotel;
import domain.Room;
import domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс, который содержит:
 * методы для работы с данными отелей, поиска отеля по городу, поиска отеля по имени,
 * бронирования комнаты пользователем, отмены бронирования пользователем через вызов методов BookingSystemController;
 * методы для работы с базой данных пользователей через вызов методов UserController.
 */

public final class Controller {

    private final BookingSystemController bookingSystemController;
    private final UserController userController;

    public Controller() {
        this.bookingSystemController = new BookingSystemController();
        this.userController = new UserController();
    }

    /**
     * Вызывает метод добавления пользователя в базу данных.
     *
     * @param user пользователь, которого нужно добавить в базу данных
     * @return <tt>true</tt> если пользователь добавлен, <tt>false</tt> - если не добавлен
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean addUser(User user) throws IOException {
        return userController.addUser(user);
    }

    /**
     * Метод возвращает лист всех пользователей записанных в БД
     * @return - List<User>
     */
    public List<User> getUsers() {
        return userController.getAllUsers();
    }

    /**
     * Вызывает метод изменения свойств пользователя в базе данных.
     *
     * @param user пользователь, который содержит новые свойства
     * @return <tt>true</tt> если свойства изменены, <tt>false</tt> - если не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean updateUser(User user) throws IOException {
        return userController.updateUser(user);
    }

    /**
     * Удаляет пользователя из базы данных.
     *
     * @param user пользователь, которого нужно удалить из базы данных
     * @return <tt>true</tt> если пользователь успешно удален, <tt>false</tt> - если не удалось удалить пользователя
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean deleteUser(User user) throws IOException {
        return userController.deleteUser(user);
    }

    /**
     * Проверяет наличие пользователя в базе данных
     *
     * @param user пользователь, наличие которого в базе данных нужно проверить
     * @return <tt>true</tt> если пользователь найден, <tt>false</tt> - если не удалось найти пользователя
     */
    public boolean containsUser(User user) {
        return userController.containsUser(user);
    }

    /**
     * Вызывает метод добавления отеля.
     *
     * @param hotel отель, который нужно добавить в базу данных
     * @return <tt>true</tt> если отель добавлен, <tt>false</tt> - если отель не добавлен
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean addHotel(Hotel hotel) throws IOException {
        return bookingSystemController.addHotel(hotel);
    }

    /**
     * Вызывает метод изменения свойства отеля.
     *
     * @param hotel - отель, который содержит новые свойства
     * @return <tt>true</tt>  если свойства изменены, <tt>false</tt> - если свойства не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean changeHotelData(Hotel hotel) throws IOException {
        return bookingSystemController.changeHotelData(hotel);
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
        return bookingSystemController.addRoomToHotel(hotel, room);
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
        return bookingSystemController.changeRoomData(room, hotel);
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
        return bookingSystemController.deleteRoomFromHotel(room, hotel);
    }

    /**
     * Вызывает метод удаления отеля из базы данных.
     *
     * @param hotel - отель, который нужно удалить
     * @return <tt>true</tt> если отель успешно удален, <tt>false</tt> - если отель не удалось удалить
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean deleteHotel(Hotel hotel) throws IOException {
        return bookingSystemController.deleteHotel(hotel);
    }

    /**
     * Метод возвращает все зарегестрированные в базе данных отели
     * @return лист зарегестрированных в базе данных отелей
     */
    public List<Hotel> getHotels () {
        return bookingSystemController.getAllHotels();
    }

    /**
     * Вызывает метод поиска отеля по имени.
     *
     * @param hotelName имя отеля, который нужно найти
     * @return List<Hotel> список отелей с заданым в параметере именем
     */
    public List<Hotel> searchHotelByName(String hotelName) {
        return bookingSystemController.searchHotelByName(hotelName);
    }

    /**
     * Вызывает метод поиска отеля в городе, указанном в параметрах.
     *
     * @param cityName город, в котором нужно найти отели
     * @return List<Hotel> список отелей в заданом городе
     */
    public List<Hotel> searchHotelByCity(String cityName) {
        return bookingSystemController.searchHotelByCity(cityName);
    }

    /**
     * Вызывает метод поиска отеля по номеру в регистре города.
     *
     * @param cityRegister номер здания в городском регистре, по которому нужно найти отель
     * @return Hotel искомый отель, если он найден в базе данных, null - если отель в базе данных не найден
     */
    public Hotel getHotelByCityRegister (String cityRegister) {
        return bookingSystemController.getHotelByCityRegister(cityRegister);
    }

    /**
     * Вызывает метод бронирования комнаты на определенную дату.
     *
     * @param room комната, которую нужно забронировать
     * @param bookingInfo информация для бронирования
     * @return <tt>true</tt>  если комната успешно забронирована, <tt>false</tt> - если комнату не удалось забронировать
     */
    public boolean bookRoomByUser(Hotel hotel, Room room, BookingInfo bookingInfo) throws IOException {
        return bookingSystemController.bookRoomByUser(hotel, room, bookingInfo);
    }

    /**
     * Отменяет регестрацию комнаты в системе.
     *
     * @param room комната, с которой нужно снять бронь
     * @param bookingInfo информация про бронь, которую необходимо снять
     * @return <tt>true</tt>  если бронь успешно снята, <tt>false</tt> - если бронь не удалось снять
     */
    public boolean bookingCancalation(Hotel hotel, Room room, BookingInfo bookingInfo) throws IOException {
        return bookingSystemController.bookingCancellation(hotel, room, bookingInfo);
    }

    /**
     * Метод обновляет БД до первоначальных, тестовых, значений.
     * 5 юзеров, 3 города, по 2 отебя в городе, по 10 комнта в отеле
     */
    public void fillAllDataBases() throws IOException {

        List<User> userList = new ArrayList<>();
        User user1 = new User("andreid", "Andrei", "Demidov");
        userList.add(user1);
        User user2 = new User("sbosh", "Sergei", "Boshuk");
        userList.add(user2);
        User user3 = new User("troy", "Vitalii", "Proskura");
        userList.add(user3);
        User user4 = new User("olechka82", "Olga", "Demidenko");
        userList.add(user4);
        User user5 = new User("arajan", "Ashot", "Azuryan");
        userList.add(user5);

        List<Hotel> hotelList = new ArrayList<>();
        Hotel hotel1 = new Hotel("K-111", "Berloga", "Kiev");
            List<Room> roomList1 = new ArrayList<>();
            Room room11 = new Room(1, 2, 150.0);
            roomList1.add(room11);
            Room room12 = new Room(2, 2, 150.0);
            roomList1.add(room12);
            Room room13 = new Room(3, 2, 150.0);
            roomList1.add(room13);
            Room room14 = new Room(4, 3, 200.0);
            roomList1.add(room14);
            Room room15 = new Room(5, 3, 200.0);
            roomList1.add(room15);
            Room room16 = new Room(6, 3, 200.0);
            roomList1.add(room16);
            Room room17 = new Room(7, 1, 100.0);
            roomList1.add(room17);
            Room room18 = new Room(8, 1, 100.0);
            roomList1.add(room18);
            Room room19 = new Room(9, 1, 100.0);
            roomList1.add(room19);
            Room room10 = new Room(10, 2, 100.0);
            roomList1.add(room10);
            hotel1.setRoomList(roomList1);
        hotelList.add(hotel1);

        Hotel hotel2 = new Hotel("K-222", "Hayat", "Kiev");
            List<Room> roomList2 = new ArrayList<>();
            Room room21 = new Room(1, 2, 150.0);
            roomList2.add(room21);
            Room room22 = new Room(2, 2, 150.0);
            roomList2.add(room22);
            Room room23 = new Room(3, 2, 150.0);
            roomList2.add(room23);
            Room room24 = new Room(4, 3, 200.0);
            roomList2.add(room24);
            Room room25 = new Room(5, 3, 200.0);
            roomList2.add(room25);
            Room room26 = new Room(6, 3, 200.0);
            roomList2.add(room26);
            Room room27 = new Room(7, 1, 100.0);
            roomList2.add(room27);
            Room room28 = new Room(8, 1, 100.0);
            roomList2.add(room28);
            Room room29 = new Room(9, 1, 100.0);
            roomList2.add(room29);
            Room room20 = new Room(10, 2, 100.0);
            roomList2.add(room20);
            hotel2.setRoomList(roomList2);
        hotelList.add(hotel2);

        Hotel hotel3 = new Hotel("O-111", "Privoz", "Odessa");
            List<Room> roomList3 = new ArrayList<>();
            Room room31 = new Room(1, 2, 150.0);
            roomList3.add(room31);
            Room room32 = new Room(2, 2, 150.0);
            roomList3.add(room32);
            Room room33 = new Room(3, 2, 150.0);
            roomList3.add(room33);
            Room room34 = new Room(4, 3, 200.0);
            roomList3.add(room34);
            Room room35 = new Room(5, 3, 200.0);
            roomList3.add(room35);
            Room room36 = new Room(6, 3, 200.0);
            roomList3.add(room36);
            Room room37 = new Room(7, 1, 100.0);
            roomList3.add(room37);
            Room room38 = new Room(8, 1, 100.0);
            roomList3.add(room38);
            Room room39 = new Room(9, 1, 100.0);
            roomList3.add(room39);
            Room room30 = new Room(10, 2, 100.0);
            roomList3.add(room30);
            hotel3.setRoomList(roomList3);
        hotelList.add(hotel3);

        Hotel hotel4 = new Hotel("O-222", "Uvoz", "Odessa");
            List<Room> roomList4 = new ArrayList<>();
            Room room41 = new Room(1, 2, 150.0);
            roomList4.add(room41);
            Room room42 = new Room(2, 2, 150.0);
            roomList4.add(room42);
            Room room43 = new Room(3, 2, 150.0);
            roomList4.add(room43);
            Room room44 = new Room(4, 3, 200.0);
            roomList4.add(room44);
            Room room45 = new Room(5, 3, 200.0);
            roomList4.add(room45);
            Room room46 = new Room(6, 3, 200.0);
            roomList4.add(room46);
            Room room47 = new Room(7, 1, 100.0);
            roomList4.add(room47);
            Room room48 = new Room(8, 1, 100.0);
            roomList4.add(room48);
            Room room49 = new Room(9, 1, 100.0);
            roomList4.add(room49);
            Room room40 = new Room(10, 2, 100.0);
            roomList4.add(room40);
            hotel4.setRoomList(roomList4);
        hotelList.add(hotel4);


        Hotel hotel5 = new Hotel("N-111", "South Buk", "Nikolaev");
            List<Room> roomList5 = new ArrayList<>();
            Room room51 = new Room(1, 2, 150.0);
            roomList5.add(room51);
            Room room52 = new Room(2, 2, 150.0);
            roomList5.add(room52);
            Room room53 = new Room(3, 2, 150.0);
            roomList5.add(room53);
            Room room54 = new Room(4, 3, 200.0);
            roomList5.add(room54);
            Room room55 = new Room(5, 3, 200.0);
            roomList5.add(room55);
            Room room56 = new Room(6, 3, 200.0);
            roomList5.add(room56);
            Room room57 = new Room(7, 1, 100.0);
            roomList5.add(room57);
            Room room58 = new Room(8, 1, 100.0);
            roomList5.add(room58);
            Room room59 = new Room(9, 1, 100.0);
            roomList5.add(room59);
            Room room50 = new Room(10, 2, 100.0);
            roomList5.add(room50);
            hotel5.setRoomList(roomList5);
        hotelList.add(hotel5);

        Hotel hotel6 = new Hotel("N-222", "Sail", "Nikolaev");
            List<Room> roomList6 = new ArrayList<>();
            Room room61 = new Room(1, 2, 150.0);
            roomList6.add(room61);
            Room room62 = new Room(2, 2, 150.0);
            roomList6.add(room62);
            Room room63 = new Room(3, 2, 150.0);
            roomList6.add(room63);
            Room room64 = new Room(4, 3, 200.0);
            roomList6.add(room64);
            Room room65 = new Room(5, 3, 200.0);
            roomList6.add(room65);
            Room room66 = new Room(6, 3, 200.0);
            roomList6.add(room66);
            Room room67 = new Room(7, 1, 100.0);
            roomList6.add(room67);
            Room room68 = new Room(8, 1, 100.0);
            roomList6.add(room68);
            Room room69 = new Room(9, 1, 100.0);
            roomList6.add(room69);
            Room room60 = new Room(10, 2, 100.0);
            roomList6.add(room60);
            hotel6.setRoomList(roomList6);
        hotelList.add(hotel6);

        this.userController.getAllUsers().clear();
        this.userController.flush();

        this.bookingSystemController.getAllHotels().clear();
        this.bookingSystemController.flush();
        for (User user : userList) {
            this.userController.addUser(user);
        }

        for (Hotel hotel : hotelList) {
            this.bookingSystemController.addHotel(hotel);
        }

    }

}