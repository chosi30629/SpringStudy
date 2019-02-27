package me.chosi.springmvc03;

import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

//    @GetMapping("/hello/{name}")
//    public String hello(@PathVariable("name") Person person) {
//        return "hello " + person.getName();
//    }

    // --- 기본

    // hello
    // 요청처리
    // 뷰 랜더링
    @GetMapping("/hello")
    public String hello(@RequestParam("id") Person person) {
        return "hello " + person.getName();
    }

    // --- 핸들러 인터셉터 적용

    // preHandle
    // 요청 처리
    // postHandler
    // 뷰 랜더링
    // aferCompletion

    // --- 여러개 적용시 순서
    // WebConfig 에서 특별하게 설정한 것 없으면애드한 순서대로 적용하지만...

    // preHandle 1
    // preHandle 2
    // 요청 처리
    // postHandler 2
    // postHandler 1
    // 뷰 랜더링
    // aferCompletion 2
    // aferCompletion 1


    @GetMapping("/message")
    public String message(@RequestBody String body) {   // @RequestBody 요청 본문으로 들어오는 데이터를 받음
        return body;
    }

    @GetMapping("/jsonMessage")
    public Person jsonMessage(@RequestBody Person person) {
        return person;
    }

}
