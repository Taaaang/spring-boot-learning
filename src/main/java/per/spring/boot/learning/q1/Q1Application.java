package per.spring.boot.learning.q1;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import per.spring.boot.learning.q1.service.MyService;

/**
 * 验证当切面方法报错，添加{@link Transactional}事务是否会回滚
 *
 * 本质，spring的代理类与切面方法的执行顺序
 *
 * @Author:TangFenQi
 * @Date:2021/11/4 23:55
 **/
@SpringBootApplication
@MapperScan({"per.spring.boot.learning.q1.dao.mapper"})
public class Q1Application implements SmartInitializingSingleton {

    @Autowired
    private MyService service;

    public static void main(String[] args) {
        SpringApplication.run(Q1Application.class);
    }

    public void afterSingletonsInstantiated() {
        service.saveToDB();
    }
}
