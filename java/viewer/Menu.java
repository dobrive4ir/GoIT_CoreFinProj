package viewer;

import controller.Controller;
import domain.BookingInfo;
import domain.Hotel;
import domain.Room;
import domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Класс-меню для работы с программой для системы бронирования отелей через консоль.
 *
 */
public class Menu {
    private Controller controller;

    public Menu(Controller controller) {
        this.controller = controller;
    }

    public void run(){
        generalMenu();
    }



    private void generalMenu(){
        while (true) {
            System.out.println();
            System.out.println ("*** Booking system ***");
            System.out.println();
            System.out.println("1 Booking menu");
            System.out.println("2 Administrator menu");
            System.out.println("3 User menu");
            System.out.println("0 Exit");

            String choise = readData();

            switch (choise) {
                case "1":
                    bookingMenu();
                    break;

                case "2":
                    adminMenu();
                    break;

                case "3":
                    userMenu();
                    break;

                case "0":
                    return;

                default:
                    System.out.println("INFO: Wrong choice");
            }
        }
    }

    //**************** User menu block
    private void userMenu() {
        while (true) {
            System.out.println();
            System.out.println ("*** User menu ***");
            System.out.println();
            System.out.println("Users list:");
            printAllUsers ();
            System.out.println();
            System.out.println("1 Add User");
            System.out.println("2 Edit User");
            System.out.println("3 Delete User");
            System.out.println("0 Exit");

            String choise = readData();

            switch (choise) {
                case "1":
                    addUser();
                    break;

                case "2":
                    editUser();
                    break;

                case "3":
                    deleteUser();
                    break;

                case "0":
                    return;

                default:
                    System.out.println("INFO: Wrong choice");
            }
        }

    }

    private void deleteUser() {
        String userLogin;


        System.out.println();
        System.out.println("INFO: Enter exit for exit");

        System.out.print("Enter user login: ");
        userLogin = readData();
        if (userLogin.equalsIgnoreCase("exit")) return;


        if (userLogin.equals("")) {
            System.out.println("INFO: Wrong user data");
            return;
        }

        User user = new User(userLogin, "", "");
        try {
            if (controller.deleteUser(user)) {
                System.out.println("INFO: User is deleted");
            } else {
                System.out.println("INFO: User " + userLogin + " is not registered");
            }
        } catch (IOException e) {
            System.out.println("ERROR: Data Base IO problem");
        }
    }

    private void editUser() {
        String userLogin;
        String userName;
        String userLastName;

        System.out.println();
        System.out.println("INFO: Enter exit for exit");

        System.out.print("Enter user login: ");
        userLogin = readData();
        if (userLogin.equalsIgnoreCase("exit")) return;
        System.out.print("Enter user name: ");
        userName = readData();
        if (userName.equalsIgnoreCase("exit")) return;
        System.out.print("Enter user last name: ");
        userLastName = readData();
        if (userLastName.equalsIgnoreCase("exit")) return;

        if (userLogin.equals("") || userName.equals("") || userLastName.equals("")) {
            System.out.println("INFO: Wrong user data");
            return;
        }

        User user = new User(userLogin, userName, userLastName);
        try {
            if (controller.updateUser(user)) {
                System.out.println("INFO: User is updated");
            } else {
                System.out.println("INFO: User " + userLogin + " is not registered");
            }
        } catch (IOException e) {
            System.out.println("ERROR: Data Base IO problem");
        }
    }

    private void addUser() {
        String userLogin;
        String userName;
        String userLastName;

        System.out.println();
        System.out.println("INFO: Enter exit for exit");

        System.out.print("Enter user login: ");
        userLogin = readData();
        if (userLogin.equalsIgnoreCase("exit")) return;
        System.out.print("Enter user name: ");
        userName = readData();
        if (userName.equalsIgnoreCase("exit")) return;
        System.out.print("Enter user last name: ");
        userLastName = readData();
        if (userLastName.equalsIgnoreCase("exit")) return;

        if (userLogin.equals("") || userName.equals("") || userLastName.equals("")) {
            System.out.println("INFO: Wrong user data");
            return;
        }

        User user = new User(userLogin, userName, userLastName);
        try {
            if (controller.addUser(user)) {
                System.out.println("INFO: User is added");
            } else {
                System.out.println("INFO: User " + userLogin + " is already exist");
            }
        } catch (IOException e) {
            System.out.println("ERROR: Data Base IO problem");
        }

    }

    private void printAllUsers () {
        controller.getUsers().forEach(user -> {
            System.out.println("User login: " + user.getUserLogin()
            + ", User name: " + user.getUserName()
            + ", User last name: " + user.getUserLastName());
        });
    }

    //************** Administrator menu block *****************
    private void adminMenu() {

        while (true) {
            System.out.println();
            System.out.println ("*** Administrator menu ***");
            System.out.println();
            System.out.println("Hotels list:");
            printAllHotels();
            System.out.println();
            System.out.println("1 Add Hotel");
            System.out.println("2 Edit Hotel");
            System.out.println("3 Delete Hotel");
            System.out.println("0 Exit");

            String choice = readData();

            switch (choice) {
                case "1":
                    addHotel ();
                    break;

                case "2":
                    editHotel ();
                    break;

                case "3":
                    deleteHotel ();
                    break;

                case "0":
                    return;

                default:
                    System.out.println("INFO: Wrong choice");
            }
        }
    }

    private void editHotel () {
        String cityRegister;


        System.out.println();
        System.out.println("INFO: Enter exit for exit");

        System.out.print("Enter hotel city register: ");
        cityRegister = readData();
        if (cityRegister.equalsIgnoreCase("exit")) return;


        if (cityRegister.equals("")) {
            System.out.println("INFO: Wrong user data");
            return;
        }



        Hotel hotel = new Hotel(cityRegister, "", "");

        editHotelSubMenu (hotel);

    }

    private void editHotelSubMenu (Hotel hotel) {
        while (true) {
            System.out.println();
            System.out.println ("*** Edit hotel menu ***");
            System.out.println();
            System.out.println("1 Edit hotel data");
            System.out.println("2 Edit rooms");
            System.out.println("3 Add room to hotel");
            System.out.println("4 Delete room from hotel");


            System.out.println("0 Exit");

            String choice = readData();

            switch (choice) {
                case "1":
                    String newHotelName;

                    System.out.println();
                    System.out.println("INFO: Enter exit for exit");

                    System.out.print("Enter new hotel name: ");
                    newHotelName = readData();
                    if (newHotelName.equalsIgnoreCase("exit")) return;


                    if (newHotelName.equals("")) {
                        System.out.println("INFO: Wrong user data");
                        return;
                    }

                    Hotel currentHotel = controller.getHotelByCityRegister(hotel.getCityRegister());
                    currentHotel.setName(newHotelName);
                    try {
                        if (!controller.changeHotelData(currentHotel)) {
                            System.out.println("WARNING: This hotel is not registered");
                        } else {
                            System.out.println("INFO: Hotel is edited");
                        }
                    } catch (IOException e) {
                        System.out.println("ERROR: Data Base IO problem");
                    }
                    break;

                case "2":
                    System.out.println();
                    System.out.println("Choose the room for edit:");
                    currentHotel = controller.getHotelByCityRegister(hotel.getCityRegister());
                    List<Room> roomList = currentHotel.getRoomList();
                    for (int i = 0; i < roomList.size(); i++) {
                        System.out.print((i+1) + " Room number-" + roomList.get(i).getRoomNumber() + " ");
                        System.out.print(" persons-" + roomList.get(i).getPersons() + " ");
                        System.out.println(" price-" + roomList.get(i).getPrice());
                    }
                    System.out.println("0 Exit");
                    choice = readData();
                    if ("0".equalsIgnoreCase(choice)) return;

                    try {
                        Room room = roomList.get(Integer.parseInt(choice) - 1);
                        editRoomMenu (hotel, room);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("INFO: Wrong choice");
                    }
                    break;

                case "3":
                    currentHotel = controller.getHotelByCityRegister(hotel.getCityRegister());
                    addRoomMenu (currentHotel);
                    break;

                case "4":
                    currentHotel = controller.getHotelByCityRegister(hotel.getCityRegister());
                    deleteRoomFromHotel (currentHotel);

                case "0":
                    return;

                default:
                    System.out.println("INFO: Wrong choice");
            }
        }
    }

    private void deleteRoomFromHotel (Hotel hotel) {
        String roomNumber;
        System.out.println();
        System.out.println("INFO: Enter exit for exit");

        System.out.print("Enter the number of new room: ");
        roomNumber = readData();

        try {
            Room room = new Room(Integer.parseInt(roomNumber), 0, 0);
            if (controller.deleteRoomFromHotel(room, hotel)) {
                System.out.println("INFO: Room is deleted");
            } else {
                System.out.println("Room with the number is not exist");
            }
        } catch (IOException e) {
            System.out.println("ERROR: Data Base IO problem");
        } catch (NumberFormatException e) {
            System.out.println("Entered data is wrong");
        }
    }

    private void addRoomMenu (Hotel hotel) {
        String roomNumber;
        String persons;
        String price;
        System.out.println();
        System.out.println("INFO: Enter exit for exit");

        System.out.print("Enter the number of new room: ");
        roomNumber = readData();
        System.out.print("Enter the persons amount of new room: ");
        persons = readData();
        System.out.print("Enter the price of new room: ");
        price = readData();

        try {
            Room room = new Room(Integer.parseInt(roomNumber), Integer.parseInt(persons), Double.parseDouble(price));
            if (controller.addRoomToHotel(hotel, room)) {
                System.out.println("INFO: New room added to hotel");
            } else {
                System.out.println("WARNING: Room is already exist");
            }
        } catch (IOException e) {
            System.out.println("ERROR: Data Base IO problem");
        } catch (NumberFormatException e) {
            System.out.println("Entered data format is wrong");
        }
    }

    private void editRoomMenu (Hotel hotel, Room room) {
        String personAmount;
        String price;

        System.out.println();
        System.out.println("Edit room");
        System.out.println();
        System.out.print("Enter new persons amount: ");
        personAmount = readData();
        System.out.print("Enter new price: ");
        price = readData();

        try {
            room.setPersons(Integer.parseInt(personAmount));
            room.setPrice(Double.parseDouble(price));
            controller.changeRoomData(room, hotel);
        } catch (IOException e) {
            System.out.println("ERROR: Data Base IO problem");
        } catch (NumberFormatException e) {
            System.out.println("You entered wrong data");
        }

    }

    private void deleteHotel(){
        String cityRegister;


        System.out.println();
        System.out.println("INFO: Enter exit for exit");

        System.out.print("Enter hotel city register: ");
        cityRegister = readData();
        if (cityRegister.equalsIgnoreCase("exit")) return;


        if (cityRegister.equals("")) {
            System.out.println("INFO: Wrong user data");
            return;
        }

        Hotel hotel = new Hotel(cityRegister, "", "");

        try {
            if (controller.deleteHotel(hotel)) {
                System.out.println("INFO: Hotel is deleted");
            } else {
                System.out.println("INFO: Hotel " + cityRegister + " is not registered");
            }
        } catch (IOException e) {
            System.out.println("ERROR: Data Base IO problem");
        }
    }

    private void addHotel () {
        String hotelName;
        String hotelCity;
        String cityRegister;

        System.out.println();
        System.out.println("INFO: Enter exit for exit");

        System.out.print("Enter hotel name: ");
        hotelName = readData();
        if (hotelName.equalsIgnoreCase("exit")) return;
        System.out.print("Enter hotel city: ");
        hotelCity = readData();
        if (hotelCity.equalsIgnoreCase("exit")) return;
        System.out.print("Enter cityRegister: ");
        cityRegister = readData();
        if (cityRegister.equalsIgnoreCase("exit")) return;

        if (hotelName.equals("") || hotelCity.equals("") || cityRegister.equals("")) {
            System.out.println("INFO: Wrong hotel data");
            return;
        }

        Hotel hotel = new Hotel(cityRegister, hotelName, hotelCity);

        try {
            if (controller.addHotel(hotel)) {
                System.out.println("INFO: Hotel is added");
            } else {
                System.out.println("INFO: Hotel " + hotelName + " is already exist");
            }
        } catch (IOException e) {
            System.out.println("ERROR: Data Base IO problem");
        }
    }

    //*************** Booking menu block *********************
    private void bookingMenu() {
        while (true) {
            System.out.println();
            System.out.println ("*** Booking menu ***");
            System.out.println();
            System.out.println("Hotels list:");
            printAllHotels();
            System.out.println();
            System.out.println("1 Search hotel by name");
            System.out.println("2 Search hotel by city");
            System.out.println("0 Exit");

            String choise = readData();

            switch (choise) {
                case "1":
                    searchHotelByName();
                    break;

                case "2":
                    searchHotelByCity();
                    break;

                case "0":
                    return;

                default:
                    System.out.println("INFO: Wrong choice");
            }
        }
    }

    private void printAllHotels() {
        controller.getHotels().forEach(hotel -> {
            System.out.println ("Hotel name-" + hotel.getName()
                    + ", City reg: " + hotel.getCityRegister()
                    + ", City-" + hotel.getCity()
                    + ", Rooms amount-" + hotel.getRoomList().size());
        });
    }

    private void searchHotelByCity() {
        String cityName;
        System.out.println();
        System.out.println();
        System.out.println("INFO: Enter exit for exit");

        System.out.print("Enter city name: ");
        cityName = readData();
        if (cityName.equalsIgnoreCase("exit")) return;

        List<Hotel> hotelList = controller.searchHotelByCity(cityName);

        if (hotelList.size() == 0) {
            System.out.println("INFO: No hotels founded");
            return;
        }

        chooseHotelMenu(hotelList);

        for (int i = 0; i < hotelList.size(); i++) {
            System.out.print(i + " Hotel-" + hotelList.get(i).getName() + " ");
            System.out.print(" City-" + hotelList.get(i).getCity() + " ");
            System.out.println(" room number-" + hotelList.get(i).getRoomList().size());
        }
    }

    private void searchHotelByName() {
        String hotelName;

        System.out.println();
        System.out.println("INFO: Enter exit for exit");

        System.out.print("Enter hotel name: ");
        hotelName = readData();
        if (hotelName.equalsIgnoreCase("exit")) return;

        List<Hotel> hotelList = controller.searchHotelByName(hotelName);

        if (hotelList.size() == 0) {
            System.out.println("INFO: No hotels founded");
            return;
        }

        chooseHotelMenu(hotelList);
        for (int i = 0; i < hotelList.size(); i++) {
            System.out.print(i + " Hotel-" + hotelList.get(i).getName() + " ");
            System.out.print(" City-" + hotelList.get(i).getCity() + " ");
            System.out.println(" room number-" + hotelList.get(i).getRoomList().size());
        }
    }

    private void chooseHotelMenu(List<Hotel> hotelList) {
        while (true) {
            for (int i = 0; i < hotelList.size(); i++) {
                System.out.print((i+1) + " Hotel-" + hotelList.get(i).getName() + " ");
                System.out.print(" City-" + hotelList.get(i).getCity() + " ");
                System.out.println(" room number-" + hotelList.get(i).getRoomList().size());
            }

            System.out.println("0 Exit");

            String choise = readData();
            if ("0".equalsIgnoreCase(choise)) return;

            try {
                Hotel hotel = hotelList.get(Integer.parseInt(choise) - 1);
                chooseRoomMenu(hotel);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("INFO: Wrong choice");
            }


        }
    }

    private void chooseRoomMenu(Hotel hotel) {

        while (true) {
            List<Room> roomList = hotel.getRoomList();
            for (int i = 0; i < roomList.size(); i++) {
                System.out.print((i+1) + " Room number-" + roomList.get(i).getRoomNumber() + " ");
                System.out.print(" persons-" + roomList.get(i).getPersons() + " ");
                System.out.println(" price-" + roomList.get(i).getPrice());
            }
            System.out.println("0 Exit");
            String choise = readData();
            if ("0".equalsIgnoreCase(choise)) return;

            try {
                Room room = roomList.get(Integer.parseInt(choise) - 1);
                orderRoomMenu(hotel, room);
            } catch (Exception e) {
                System.out.println("INFO: Wrong choice");
            }
        }
    }

    private void orderRoomMenu(Hotel hotel, Room room) {
        while (true) {
            System.out.println();
            System.out.println ("*** Booking of room menu ***");
            System.out.println();
            System.out.println("1 Book room");
            System.out.println("2 Cancel room booking");
            System.out.println("0 Exit");

            String choise = readData();

            switch (choise) {
                case "1":
                    System.out.print("Enter user login :");
                    String userLogin = readData();

                    if (!controller.containsUser(new User(userLogin, "", ""))) {
                        System.out.println("WARNING: The User is not registered");
                        break;
                    }

                    System.out.print("Enter date of begin (DD.MM.YYYY):");
                    String fromDate = readData();
                    Date dateFrom = null;
                    try {
                        dateFrom = new SimpleDateFormat("dd.MM.yyyy").parse(fromDate);
                    } catch (ParseException e) {
                        System.out.println("WARNING: Wrong date format");
                    }

                    System.out.print("Enter date of end (DD.MM.YYYY):");
                    String toDate = readData();
                    Date dateTo = null;
                    try {
                        dateTo = new SimpleDateFormat("dd.MM.yyyy").parse(toDate);
                    } catch (ParseException e) {
                        System.out.println("WARNING: Wrong date format");
                    }
                    try {
                        if (controller.bookRoomByUser(hotel, room, new BookingInfo(userLogin, dateFrom, dateTo))) {
                            System.out.println("INFO: Room is booked");
                        } else {
                            System.out.println("INFO: Room is busy on this dates");
                        }
                    } catch (Exception e) {
                        System.out.println("WARNING: Begin date cannot be after finish date");
                    }
                    break;

                case "2":
                    System.out.print("Enter user login :");
                    userLogin = readData();

                    if (!controller.containsUser(new User(userLogin, "", ""))) {
                        System.out.println("WARNING: The User is not registered");
                        break;
                    }

                    System.out.print("Enter date of begin (DD.MM.YYYY):");
                    fromDate = readData();
                    dateFrom = null;
                    try {
                        dateFrom = new SimpleDateFormat("dd.MM.yyyy").parse(fromDate);
                        System.out.println(dateFrom);
                    } catch (ParseException e) {
                        System.out.println("WARNING: Wrong date format");
                    }

                    System.out.print("Enter date of end (DD.MM.YYYY):");
                    toDate = readData();
                    dateTo = null;
                    try {
                        dateTo = new SimpleDateFormat("dd.MM.yyyy").parse(toDate);
                        System.out.println(dateTo);
                    } catch (ParseException e) {
                        System.out.println("WARNING: Wrong date format");
                    }
                    try {
                        if (controller.bookingCancalation(hotel, room, new BookingInfo(userLogin, dateFrom, dateTo))) {
                            System.out.println("INFO: Booking is canceled");
                        } else {
                            System.out.println("INFO: This order is not founded on this room");
                        }
                    } catch (Exception e) {
                        System.out.println("WARNING: Begin date cannot be after finish date");
                    }
                    break;

                case "0":

                    return;

                default:
                    System.out.println("INFO: Wrong choice");
            }
        }
    }

    private String readData(){
        String answer = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            answer = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return answer;
    }
}
