package Sql.Dao;

import Sql.Entity.User;

import java.util.List;

/**
 * @author zhw
 * Date:2022/10/26
 */
public interface UserMapper {
    List<User> findAll();
}
