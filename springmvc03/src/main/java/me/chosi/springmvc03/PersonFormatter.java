package me.chosi.springmvc03;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;


// 스프링 JPA를 사용하면 도메인컨버터가 자동으로 해주므로 이런 포멧터 필요 없음


// 패스배리어블과 리퀘스트파람의 특정 요청 값을 객체로 받고 싶을 때
//@Component
public class PersonFormatter /*implements Formatter<Person>*/ {

//    @Override
//    public Person parse(String text, Locale locale) throws ParseException {
//        Person person = new Person();
//        person.setName(text);
//        return person;
//    }
//
//    @Override
//    public String print(Person object, Locale locale) {
//        return object.toString();
//    }

}
