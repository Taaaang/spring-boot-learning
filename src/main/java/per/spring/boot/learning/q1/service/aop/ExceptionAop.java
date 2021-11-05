package per.spring.boot.learning.q1.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author:TangFenQi
 * @Date:2021/11/4 23:59
 **/
@Aspect
@Component
@Order(1)
public class ExceptionAop {

    /**
     * 定义切入点：对要拦截的方法进行定义与限制，如包、类
     *
     * 1、execution(public * *(..)) 任意的公共方法
     * 2、execution（* set*（..）） 以set开头的所有的方法
     * 3、execution（* per.spring.boot.learning.q1.service.MyService.*（..）），MyService这个类里的所有的方法
     * 4、execution（* per.spring.boot.learning.q1.service.*.*（..）），service包下的所有的类的所有的方法
     * 5、execution（* per.spring.boot.learning.q1.service..*.*（..）），service包及子包下所有的类的所有的方法
     * 6、execution(* per.spring.boot.learning.q1.service..*.*(String,?,Long)) service包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
     */
    @Pointcut("execution(* per.spring.boot.learning.q1.service.MyService.*(..))")
    private void pointCut(){

    }

    /**
     * 方法之前
     */
    @Before("pointCut()")
    private void before(JoinPoint joinPoint){
        System.out.println("before");
    }

    /**
     * 方法执行完毕之后
     */
    @AfterReturning(returning = "returnObject",pointcut = "pointCut()")
    private void afterReturning(JoinPoint joinPoint,Object returnObject){
        //throw new RuntimeException("after running 报错");
        System.out.println("after return");
    }

    /**
     * 方法之后
     */
    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        //throw new RuntimeException("after 报错");
        System.out.println("after");
    }

    /**
     * 方法抛出异常执行
     */
    @AfterThrowing(pointcut = "pointCut()",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex){
        System.out.println("throwing");
    }

    /**
     * 环绕执行，执行之前，执行之后
     */
    @Around("pointCut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around-before");
        proceedingJoinPoint.proceed();
        System.out.println("around-after");
    }
}
