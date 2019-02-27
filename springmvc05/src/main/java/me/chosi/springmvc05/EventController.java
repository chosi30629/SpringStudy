package me.chosi.springmvc05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("event") // 이 이름에 해당하는 모델 어트리뷰트를 세션에 넣어줌
public class EventController {

    @ExceptionHandler({EventException.class, RuntimeException.class})
//    public String eventErrorHandler(EventException exception, Model model) {
    public String eventErrorHandler(RuntimeException ex, Model model) {
//        model.addAttribute("message", "event error");
        model.addAttribute("message", "runtime error");
        return "error";
    }

    /*
    @ExceptionHandler
    public String runtimeErrorHandler(RuntimeException exception, Model model) {
        model.addAttribute("message", "runtime error");
        return "error";
    }
    */

    /*
    @GetMapping("/events/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable("id") Integer idValue, @MatrixVariable String name) {   // required = false 와 Optional 은 동일함.
        Event event = new Event();
        event.setId(idValue);
        event.setName(name);
        return event;
    }
    */

    /*
    @PostMapping("/events")
    @ResponseBody
    public Event postEvent(@RequestParam String name) {
        Event event = new Event();
        event.setName(name);
        return event;
    }
    */

//    @Autowired
//    EventValidator eventValidator;

    /*
    @InitBinder("event") // 반드시 void
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
//        webDataBinder.addValidators(new EventValidator());
    }

    @ModelAttribute
    public void categories(Model model) {
        model.addAttribute("categories", Arrays.asList("study", "seminar", "hobby", "social"));
    }
    */

//    @ModelAttribute("categories")
//    public List<String> categories(Model model) {
//        return Arrays.asList("study", "seminar", "hobby", "social");
//    }

    @GetMapping("/events/form/name")
//    @ModelAttribute
    public String eventsFormName(Model model/*, HttpSession httpSession*/) {
        throw new EventException();
//    public Event eventsFormName() {
//        Event newEvent = new Event();
//        newEvent.setLimit(50);
//        model.addAttribute("event", newEvent);
//        model.addAttribute("event", new Event());
//        httpSession.setAttribute("event", newEvent);
//        return "events/form-name";
//        return new Event();
    }

    /*
//    @PostMapping("/events")
    @PostMapping("/events/name/{name}")
    @ResponseBody
//    public Event postEvent1(@RequestParam Map<String, String> params) {
//    public Event postEvent1(
//            @RequestParam String name,
//            @RequestParam Integer limit) {
    public Event postEvent1(/*@Valid / @Validated(Event.ValidateName.class) @ModelAttribute Event event, BindingResult bindingResult) { // 배드 리퀘스트를 받음 bindingResult
        Event event = new Event();
        event.setName(params.get("name"));
        event.setName(name);
        event.setLimit(limit);
        if (bindingResult.hasErrors()) {
            System.out.println("===========================");
            bindingResult.getAllErrors().forEach(c -> {
                System.out.println(c.toString());
            });
        }
        return event;
    }
    */

    @PostMapping("/events/form/name")
//    public String createEvent(@Validated @ModelAttribute Event event,
//    public String eventsFormNameSubmit(@Validated @ModelAttribute Event event,
    public String eventsFormNameSubmit(@Validated @ModelAttribute Event event,
                                       BindingResult bindingResult
//                            SessionStatus sessionStatus
            /*Model model*/) {
        if (bindingResult.hasErrors()) {
            return "/events/form-name";
        }
//        sessionStatus.setComplete();    // 세션에 있는 데이터 정리
//        List<Event> eventList = new ArrayList<>();
//        eventList.add(event);
//        model.addAttribute(eventList);  // 키 값이 같으면 하나로 해도됨
//        return "/events/list";
//        eventValidator.validate(event, bindingResult);

        return "redirect:/events/form/limit";
//        return "redirect:/events/list";     // 포스트 요청 처리할때는 리다이렉트해서 중복 서브밋이 발생하지 않도록 함
    }                                       // 새로고침할때 양식 제출하는거 없앰

    @GetMapping("/events/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        return "events/form-limit";
    }

    @PostMapping("/events/form/limit")
    public String eventsFormLimitSubmit(@Validated @ModelAttribute Event event,
                                        BindingResult bindingResult,
                                        SessionStatus sessionStatus,
                                        Model model,
                                        RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            return "/events/form-limit";
        }

        sessionStatus.setComplete();
//        model.addAttribute("name", event.getName());    // 리다이렉트할때 쿼리스트링으로 값이 들어감, 스프링부트에서는 설정을 따로 해줘야함
//        model.addAttribute("limit", event.getLimit());  // application.properties 참고
//        attributes.addAttribute("name", event.getName());   // 일부 데이터만 넘길거면 RedirectAttributes 사용
//        attributes.addAttribute("limit", event.getLimit()); // 따로 설정안해도됨, 객체 못 넘김
        attributes.addFlashAttribute("newEvent", event);    // 객체로 넘길 수 있으며 값은 세션에 넣어짐
        return "redirect:/events/list";                                 // 리다이렉트 된 곳에서 데이터를 처리하면 사라짐. 1회성.
    }

    @GetMapping("/events/list")
    public String getEvents(
//                            @ModelAttribute("newEvent") Event newEvent, // 세션에 설정한 값과 같으면 안됨
//                            @RequestParam String name,
//                            @RequestParam Integer limit,
                            Model model, @SessionAttribute LocalDateTime visitTime/*, HttpSession httpSession*/) {
        System.out.println(visitTime);

//        LocalDateTime visitTimes = (LocalDateTime) httpSession.getAttribute("visitTime");
//        System.out.println(visitTimes);

//        Event newEvent = new Event();
//        newEvent.setName(name);
//        newEvent.setLimit(limit);

        Event event = new Event();
        event.setName("spring");
        event.setLimit(10);

        Event newEvent = (Event) model.asMap().get("newEvent"); // addFlashAttribute를 모델로도 받을 수 있음

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);
        eventList.add(newEvent);

        model.addAttribute(eventList);

        return "/events/list";
    }

}
