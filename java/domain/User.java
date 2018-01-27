package domain;

import java.io.Serializable;

/**
 * Класс описывает пользователя, который может совершать операции в системе (бронировать комнаты)
 */
public class User implements Serializable {
    private final long id;
    private final String userLogin;
    private String userName;
    private String userLastName;

    public User(String userLogin, String userName, String userLastName) {
        this.id = IdGenerator.getId(this);
        this.userLogin = userLogin;
        this.userName = userName;
        this.userLastName = userLastName;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userLogin.equals(user.userLogin);
    }

    @Override
    public int hashCode() {
        return userLogin.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userLogin='" + userLogin + '\'' +
                ", userName='" + userName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                '}';
    }
}
