package com.alinesno.infra.ops.container.api.aspect;

import com.alinesno.infra.ops.container.entity.ManagerRecordEntity;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * ManagerRecordAspect类
 * 
 * 用户登录及页面操作监控的AOP切面。多个切面时，使用@Order(i)注解来标识切面的优先级。i的值越小，优先级越高，用于记录方法运行的时间。
 * 
 * @author WeiXiaoJin
 * @Since 2019年4月8日 下午8:30:43
 */
@Order(5)
@Aspect
@Component
public class ManagerRecordAspect {

  private static final Logger log = LoggerFactory.getLogger(ManagerRecordAspect.class);

  /**
   * 是否保存用户操作记录
   */
  private boolean saveOperationLogger;

  /**
   * 定义切入点，使用@annotation(com.alinesno.infra.ops.kubernetes.ManagerRecord)注解匹配带有ManagerRecord注解的方法
   */
  @Pointcut("@annotation(com.alinesno.infra.ops.container.api.aspect.ManagerRecord)")
  public void pointcut() {
  }

  /**
   * 环绕通知，在切入点方法执行前后进行处理
   *
   * @param point 切入点
   * @return 切入点方法的返回值
   * @throws Throwable 如果切入点方法抛出异常
   */
  @Around("pointcut()")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    Object result = null;
    long beginTime = System.currentTimeMillis();

    try {
      // 执行方法
              result = point.proceed();
    } catch (Throwable e) {
      throw e;
    } finally {
      long time = System.currentTimeMillis() - beginTime;
      // 保存日志
      monitorAccountOperator(point, time);
    }

    return result;
  }

  /**
   * 记录用户操作日志
   *
   * @param joinPoint 切入点
   * @param time      方法执行时间
   */
  @Async
  public void monitorAccountOperator(ProceedingJoinPoint joinPoint, long time) {
    ManagerRecordEntity bean = new ManagerRecordEntity();

    bean.setMethodTime(time);
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    String className = joinPoint.getTarget().getClass().getName(); // 请求的方法名
    String methodName = signature.getName();
    bean.setMethod(className + ":" + methodName);

    ManagerRecord accountRecord = method.getAnnotation(ManagerRecord.class);
    String value = accountRecord.value();
    String type = accountRecord.type().value();

    if (StringUtils.isBlank(value) && StringUtils.isNotBlank(type)) {
      ManagerRecord.RecordType record = ManagerRecord.RecordType.queryLabel(type);
      value = record.label();
    }

    bean.setMethodDesc(value);
    bean.setRecordType(type);

    Object[] args = joinPoint.getArgs(); // 请求的方法参数值
  }
}
