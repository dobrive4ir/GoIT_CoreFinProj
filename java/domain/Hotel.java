package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс инкапсулирует абстракцию отеля;
 * id отеля генерируется автоматически при создании экземпляра класса.
 */
public class Hotel implements Serializable {
    private final long id;
    private String cityRegister;
    private String name;
    private String city;
    private List<Room> roomList = new ArrayList<>();

    public Hotel(String cityRegister, String name, String city) {
        this.id = IdGenerator.getId(this);
        this.cityRegister = cityRegister;
        this.name = name;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public String getCityRegister() {
        return cityRegister;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        return cityRegister != null ? cityRegister.equals(hotel.cityRegister) : hotel.cityRegister == null;
    }

    @Override
    public int hashCode() {
        return cityRegister != null ? cityRegister.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", cityRegister='" + cityRegister + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", roomList=" + roomList +
                '}';
    }
}
