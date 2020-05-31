package ru.itis.marshrutssite.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import ru.itis.marshrutssite.security.details.UserDetailsImpl;
import ru.itis.marshrutssite.services.EmailService;

import java.io.IOException;

@Aspect
@Component
@EnableAspectJAutoProxy
public class SignInLogAspect {


    @Autowired
    private EmailService emailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * ru.itis.marshrutssite.security.details.UserDetailsServiceImpl.loadUserByUsername(..))")
    public void callAtSignIn() {

    }

    @AfterReturning(pointcut = "callAtSignIn()", returning = "userDetails")
    public void afterCallMethodSaveFile(JoinPoint jp, UserDetailsImpl userDetails) {
        String text = "В ваш аккаунт был выполнен вход сайте localhost";
        emailService.sendMail("Security from localhost", text, jp.getArgs()[0].toString());
        logger.info("Sended letter to email: " + jp.getArgs()[0].toString());
    }

}