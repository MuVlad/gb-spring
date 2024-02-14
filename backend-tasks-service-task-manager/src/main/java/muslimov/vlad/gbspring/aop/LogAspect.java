package muslimov.vlad.gbspring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LogAspect {

    @After("@annotation(muslimov.vlad.gbspring.aop.TrackUserAction)")
    public void logAfterAction(JoinPoint joinPoint) {
        log.info("After advice: " + joinPoint);
    }
}
