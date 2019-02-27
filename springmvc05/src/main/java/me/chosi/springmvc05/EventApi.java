package me.chosi.springmvc05;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@RestController
@Controller
@RequestMapping("/api/events")
public class EventApi {

//    @ExceptionHandler
//    public ResponseEntity errorHandler() {
//        return ResponseEntity.badRequest().body("cant't create event as..");
//    }

    @PostMapping
//    public Event createEvent(@RequestBody Event event) {  // 둘이 비슷하지만 바디만 가능하냐, 헤더와 바디 둘다 가능하냐 차이
//    public Event createEvent(HttpEntity<Event> request) {
//    public Event createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {

        // save event
//        return event;

//        MediaType contentType = request.getHeaders().getContentType();
//        System.out.println(contentType);
//        return request.getBody();
        if (bindingResult.hasErrors()) {
//            bindingResult.getAllErrors().forEach(error -> {
//                System.out.println(error);

//            });
            return ResponseEntity.badRequest().build();

        }
        return ResponseEntity.ok().body(event);
    }

}
