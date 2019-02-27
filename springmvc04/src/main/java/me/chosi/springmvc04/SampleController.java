package me.chosi.springmvc04;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping(method = RequestMethod.GET)
//@RequestMapping("/hello")
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SampleController {

//    @RequestMapping(value = "/hello", method = {RequestMethod.GET, RequestMethod.PUT})
//    @GetMapping("/hello")
//    @RequestMapping({"/hello", "hi"})
//    @GetMapping("/hello?")
//    @GetMapping("/hello/*")
//    @GetMapping("/hello/**")
//    @RequestMapping("/**")
//    @RequestMapping("/{name:[a-z]+}")
//    @RequestMapping("/seongil")
//    @ResponseBody
//    public String helloSeongil(/*@PathVariable String name*/) {
//        return "hello seongil";
//    }

    // 같은 경로면 위에 메소드가 우선순위가 더 높음
//    @RequestMapping("/**")
//    @ResponseBody
//    public String hello(/*@PathVariable String name*/) {
//        return "hello";
//    }

    /*
    @RequestMapping(
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,   // 요청이 json인 것만 처리, 클래스에 정의하면 메소드에 쓴 것으로 오버라이딩 됨
            produces = MediaType.TEXT_PLAIN_VALUE               // 응답을 텍스로 함
    )
    @ResponseBody
    public String hello() {
        return "hello";
    }
    */

//    @RequestMapping(value = "/hello", headers = "!" + HttpHeaders.FROM)
//    @RequestMapping(value = "/hello", headers = HttpHeaders.AUTHORIZATION + "=" + 111)
//    @GetMapping(value = "/hello", params = "name=seongil")
//    @GetMapping("/hello")
    @GetHelloMapping
    @ResponseBody
    public String hello() {
        return "hello";
    }

    /*
    @PostMapping("hello")
    @ResponseBody
    public String helloPost() {
        return "hello";
    }
    */

    @GetMapping("/events")
    @ResponseBody
    public String events() {
        return "events";
    }

    @GetMapping("/events/{id}")
    @ResponseBody
    public String getAnEvents(@PathVariable("id") int id) {
        return "event";
    }

    @DeleteMapping("/events/{id}")
    @ResponseBody
    public String removeAnEvents(@PathVariable("id") int id) {
        return "event";
    }

}
