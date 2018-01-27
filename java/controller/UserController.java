package controller;

import dao.UserDAO;
import dao.UserDAOImpl;
import domain.User;

import java.io.IOException;
import java.util.List;

/**
 * Класс, который содержит методы:
 * для работы с данными отелей через вызов методов UserDAOImpl;
 * проверки наличия пользователя в базе данных.
 */

public final class UserController {

    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAOImpl();
    }

    /**
     * Вызывает метод добавления пользователя в базу данных.
     *
     * @param user пользователь, которого нужно добавить в базу данных
     * @return <tt>true</tt> если пользователь добавлен, <tt>false</tt> - если не добавлен
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean addUser(User user) throws IOException {
        return userDAO.addUser(user);
    }

    /**
     * Вызывает метод изменения свойств пользователя в базе данных.
     *
     * @param user пользователь, который содержит новые свойства
     * @return <tt>true</tt> если свойства изменены, <tt>false</tt> - если не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean updateUser(User user) throws IOException {
        return userDAO.updateUser(user);
    }

    /**
     * Удаляет пользователя из базы данных.
     *
     * @param user пользователь, которого нужно удалить из базы данных
     * @return <tt>true</tt> если пользователь успешно удален, <tt>false</tt> - если не удалось удалить пользователя
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public boolean deleteUser(User user) throws IOException {
        return userDAO.deleteUser(user);
    }

    /**
     * Проверяет наличие пользователя в базе данных
     *
     * @param user пользователь, наличие которого в базе данных нужно проверить
     * @return <tt>true</tt> если пользователь найден, <tt>false</tt> - если не удалось найти пользователя
     */
    public boolean containsUser(User user) {
        return getAllUsers().stream().anyMatch(user::equals);
    }

    /**
     * Получение списка всех пользователей
     *
     * @return List<User> список со всеми пользователями в базе данных
     */
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    /**
     * Вызывает метод для записи данных в файл 'users'
     *
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    public void flush() throws IOException {
        this.userDAO.saveToFile();
    }

}
