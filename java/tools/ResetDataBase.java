package tools;

import controller.Controller;

import java.io.IOException;

/**
 * Created by User on 11.05.2017.
 */
public class ResetDataBase {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Controller().fillAllDataBases();
    }
}
