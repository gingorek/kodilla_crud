package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    public class MyClass {
        @Override
        public String toString(){
            return "Hello";
        }

    }
    public class MyConsumer{
        private MyClass myClass;

        public MyConsumer(MyClass myClass) {
            this.myClass = myClass;
        }

        public MyClass getMyClass() {
            return myClass;
        }

        public void sayMyClassHello() {
            if(myClass == null){
                System.out.println("My class is null");
            } else {
                System.out.println(myClass);
            }
        }

        public void sayMyClassOptionalHello() {
            Optional<MyClass> myOptional = Optional.ofNullable(myClass);
            System.out.println(myOptional.orElse(new MyClass()));
        }
    }


    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendMail() {
        //Given
        Mail mail = new Mail("test@test.com", "Test Topic", "Test Message","ccmail@test.com");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        mailMessage.setCc(mail.getToCc());

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
    @Test
    public void testOptional(){
        MyClass myClass = new MyClass();
        MyConsumer myConsumer = new MyConsumer(myClass);
        myConsumer.sayMyClassHello();
        myConsumer.sayMyClassOptionalHello();
    }
}