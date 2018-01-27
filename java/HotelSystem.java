import controller.Controller;
import viewer.Menu;

/**
 * Класс для запуска программы.
 */

public class HotelSystem {

    public static void main(String[] args) {
      Controller controller = new Controller ();
      Menu menu = new Menu(controller);
      menu.run ();

    }

}