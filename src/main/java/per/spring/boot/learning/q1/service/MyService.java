package per.spring.boot.learning.q1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.spring.boot.learning.q1.dao.User;
import per.spring.boot.learning.q1.dao.mapper.UserMapper;

/**
 * @Author:TangFenQi
 * @Date:2021/11/4 23:57
 **/
@Service
public class MyService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void saveToDB(){
        System.out.println("===============");
        System.out.println("saveToDB");
        User user = new User();
        user.setId(1L);
        user.setAge(100);
        userMapper.updateByPrimaryKeySelective(user);
        System.out.println("===============");
    }

}
