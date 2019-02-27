package me.chosi.springmvc03;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
//@WebMvcTest   // 자동으로 MockMvc 사용가능하게 됨
@SpringBootTest
@AutoConfigureMockMvc   // WebMvcTest가 아니면 이렇게 따로 설정
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

//    @Test
//    public void hello() throws Exception {
//        this.mockMvc.perform(get("/hello/seongil")) // /hello로 요청을 하면
//                .andDo(print()) // 요청과 응답을 출력
//                .andExpect(content().string("hello seongil"));  // 문자열로 "hello"가 나올것이다.
//    }

//    @Test
//    public void hello() throws Exception {
//        this.mockMvc.perform(get("/hello")
//                    .param("name", "seongil"))
//                .andDo(print()) // 요청과 응답을 출력
//                .andExpect(content().string("hello seongil"));  // 문자열로 "hello"가 나올것이다.
//    }

    @Autowired
    PersonRepository personRepository;

    @Test
    public void hello() throws Exception {
        Person person = new Person();
        person.setName("seongil");
        Person savedPerson = personRepository.save(person);

        this.mockMvc.perform(get("/hello")
                    .param("id", savedPerson.getId().toString()))   // 파라미터는 문자열로 넘겨야함
                .andDo(print())
                .andExpect(content().string("hello seongil"));
    }

    @Test
    public void helloStatic() throws Exception {
        this.mockMvc.perform(get("/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("Hello")));
    }

    @Test
    public void helloStatic1() throws Exception {
        this.mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("hello mobile")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL));
    }

    @Test
    public void stringMessage() throws Exception {
        this.mockMvc.perform(get("/message")
                .content("hello"))  // 본문에 hello 라고 보냄
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void jsonMessage() throws Exception {
        Person person = new Person();
        person.setId(2019l);
        person.setName("seongil");

        String jsonString = objectMapper.writeValueAsString(person);

        this.mockMvc.perform(get("/jsonMessage")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)   // 요청 타입
                    .accept(MediaType.APPLICATION_JSON_UTF8)    // 응답 타입
                    .content(jsonString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2019))
                .andExpect(jsonPath("$.name").value("seongil"));
    }

    @Autowired
    Jaxb2Marshaller jaxb2Marshaller;

    @Test
    public void xmlMessage() throws Exception {
        Person person = new Person();
        person.setId(2019l);
        person.setName("seongil");

        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        jaxb2Marshaller.marshal(person, result);
        String xmlString = stringWriter.toString();

        this.mockMvc.perform(get("/jsonMessage")
                    .contentType(MediaType.APPLICATION_XML)   // 요청 타입
                    .accept(MediaType.APPLICATION_XML)    // 응답 타입
                    .content(xmlString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("person/name").string("seongil"))
                .andExpect(xpath("person/id").string("2019"));
    }

}