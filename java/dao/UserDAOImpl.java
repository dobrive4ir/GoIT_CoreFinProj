package dao;

import domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс имплементирующий интерфейс UserDAO.
 * Реализует методы для работы с базой данных пользователей:
 * добавление, изменине, удаление.
 * Содержит методы записи и чтения из файла 'users'.
 */

public class UserDAOImpl implements UserDAO {

    private static final String USERFILE = "./booking_project/src/resources/users";
    private List<User> userList = new ArrayList<>();

    public UserDAOImpl() {

        try {
            readDBFromFile();
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    /**
     * Добавляет пользователя в базу данных.
     *
     * @param user пользователь, которого нужно добавить в базу данных
     * @return <tt>true</tt> если пользователь добавлен, <tt>false</tt> - если не добавлен
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    @Override
    public boolean addUser(User user) throws IOException {

        boolean answer = userList.stream()
                .noneMatch(user::equals)
                && userList.add(user);
        saveToFile();

        return answer;
    }

    /**
     * Изменяет свойства пользователя в базе данных.
     *
     * @param user пользователь, который содержит новые свойства
     * @return <tt>true</tt> если свойства изменены, <tt>false</tt> - если не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    @Override
    public boolean updateUser(User user) throws IOException {

        boolean answer = userList.stream()
                .anyMatch(user::equals);

        if (answer) {
            User foundedUser = userList.stream()
                    .filter(user::equals)
                    .findFirst()
                    .get();
            foundedUser.setUserName(user.getUserName());
            foundedUser.setUserLastName(user.getUserLastName());
            saveToFile();
        }

        return answer;
    }

    /**
     * Удаляет пользователя из базы данных.
     *
     * @param user пользователь, которого нужно удалить из базы данных
     * @return <tt>true</tt> если пользователь успешно удален, <tt>false</tt> - если не удалось удалить пользователя
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    @Override
    public boolean deleteUser(User user) throws IOException {

        boolean answer = userList.stream().anyMatch(user::equals)
                && userList.remove(user);

        saveToFile();

        return answer;
    }

    /**
     * Получение списка всех пользователей
     *
     * @return List<User> список со всеми пользователями в базе данных
     */
    @Override
    public List<User> getAll() {
        return userList;
    }

    /**
     * Производит запись данных в файл 'users'
     *
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    @Override
    public void saveToFile() throws IOException {
        DataBaseIO.saveDB(userList, USERFILE);
    }

    /**
     * Производит чтение данных из файла 'users'
     *
     * @throws IOException            когда произошли ошибки/прервались I/O operations
     * @throws ClassNotFoundException когда не удалось загрузить класс
     */
    @Override
    public void readDBFromFile() throws IOException, ClassNotFoundException {
        userList = (List<User>) DataBaseIO.readDB(USERFILE);

    }
}
