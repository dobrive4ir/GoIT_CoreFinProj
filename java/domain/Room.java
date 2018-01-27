package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс инкапсулирует абстракцию комнаты;
 * id комнаты генерируется автоматически при создании экземпляра класса.
 */
public class Room implements Serializable {
    private final long id;
    private final int roomNumber;
    private List<BookingInfo> bookingInfoList;
    private int persons;
    private double price;

    public Room(int roomNumber, int persons, double price) {
        this.bookingInfoList = new ArrayList<>();
        this.id = IdGenerator.getId(this);
        this.roomNumber = roomNumber;
        this.persons = persons;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<BookingInfo> getBookingInfoList() {
        return bookingInfoList;
    }

    public void setBookingInfoList(List<BookingInfo> bookingInfoList) {
        this.bookingInfoList = bookingInfoList;
    }

    /**
     * Данный метод проверяет забронирована ли комната на указанные в bookingInfo даты
     *
     * @param bookingInfo - объект класса BookingInfo с id пользователя и датам брони
     * @return - true если комната на указанные даты свободна, false - если занята
     */
    public boolean isFree(BookingInfo bookingInfo) {
        boolean answer = true;

        if (this.bookingInfoList.size() != 0) {
            for (BookingInfo bookItem : this.bookingInfoList) {
                if (!bookingInfo.getFromDate().equals(bookItem.getFromDate())) {
                    if (bookingInfo.getFromDate().after(bookItem.getFromDate())) {
                        if (bookingInfo.getFromDate().before(bookItem.getToDate())) {
                            answer = false;
                        }
                    }

                    if (bookingInfo.getFromDate().before(bookItem.getFromDate())) {
                        if (bookingInfo.getToDate().after(bookItem.getFromDate())) {
                            answer = false;
                        }
                    }
                } else {
                    answer = false;
                }
            }
        }

        return answer;
    }

    /**
     * Метод добавляет бронирование комнтаы определенным юзером на указанные даты
     *
     * @param bookingInfo - id пользователя, дата заезда, дата выезда
     * @return - true если комната забронирована, false - если комната на указанные даты занята
     */
    public boolean bookTheRoom(BookingInfo bookingInfo) {
        boolean answer = isFree(bookingInfo);
        if (answer) {
            this.bookingInfoList.add(bookingInfo);
        }
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", bookingInfoList=" + bookingInfoList +
                ", persons=" + persons +
                ", price=" + price +
                '}';
    }
}
