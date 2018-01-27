package dao;


import domain.User;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс содержит в себе методы по работе с базой данных пользователей.
 */

public interface UserDAO {

    /**
     * Добавляет пользователя в базу данных.
     *
     * @param user пользователь, которого нужно добавить в базу данных
     * @return <tt>true</tt> если пользователь добавлен, <tt>false</tt> - если не добавлен
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    boolean addUser(User user) throws IOException;

    /**
     * Изменяет свойства пользователя в базе данных.
     *
     * @param user пользователь, который содержит новые свойства
     * @return <tt>true</tt> если свойства изменены, <tt>false</tt> - если не изменены
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    boolean updateUser(User user) throws IOException;

    /**
     * Удаляет пользователя из базы данных.
     *
     * @param user пользователь, которого нужно удалить из базы данных
     * @return <tt>true</tt> если пользователь успешно удален, <tt>false</tt> - если не удалось удалить пользователя
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    boolean deleteUser(User user) throws IOException;

    /**
     * Получение списка всех пользователей
     *
     * @return List<User> список со всеми пользователями в базе данных
     */
    List<User> getAll();

    /**
     * Производит запись данных в файл 'users'
     *
     * @throws IOException когда произошли ошибки/прервались I/O operations
     */
    void saveToFile() throws IOException;

    /**
     * Производит чтение данных из файла 'users'
     *
     * @throws IOException            когда произошли ошибки/прервались I/O operations
     * @throws ClassNotFoundException когда не удалось загрузить класс
     */
    void readDBFromFile() throws IOException, ClassNotFoundException;

}
