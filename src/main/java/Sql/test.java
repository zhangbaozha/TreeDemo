package Sql;

import Sql.Dao.UserMapper;
import Sql.Entity.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author zhw
 * Date:2022/10/26
 */
public class test {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtils.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.findAll();
        if(users.isEmpty()){
            System.out.println("====");;
        }
        System.out.println(users.get(0).getAccount());
    }
}
