package domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Класс инкапсулирует абстракцию заказа комнаты.
 */
public class BookingInfo implements Serializable {
    private String userLogin;
    private Date fromDate;
    private Date toDate;

    public BookingInfo(String userLogin, Date fromDate, Date toDate) throws Exception {
        this.userLogin = userLogin;
        if (fromDate.equals(toDate) || fromDate.after(toDate)) {
            throw new Exception("Wrong dates");
        }
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingInfo that = (BookingInfo) o;

        if (!userLogin.equals(that.userLogin)) return false;
        if (!fromDate.equals(that.fromDate)) return false;
        return toDate.equals(that.toDate);
    }


    @Override
    public int hashCode() {
        int result = userLogin.hashCode();
        result = 31 * result + fromDate.hashCode();
        result = 31 * result + toDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BookingInfo{" +
                "userLogin=" + userLogin +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
