package tools;

import controller.BookingSystemController;
import controller.Controller;
import controller.UserController;

import java.io.IOException;

/**
 * Created by User on 11.05.2017.
 */
public class PrintDataBase {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println(new UserController().getAllUsers());
        System.out.println(new BookingSystemController().getAllHotels());

    }
}
