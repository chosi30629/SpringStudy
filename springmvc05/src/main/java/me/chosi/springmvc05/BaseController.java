package me.chosi.springmvc05;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

// 모든 컨트롤러에 다 적용
@ControllerAdvice(assignableTypes = {EventController.class, EventApi.class})  // 범위 적용 가능
//@RestControllerAdvice
public class BaseController {

    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorHandler(RuntimeException ex, Model model) {
        model.addAttribute("message", "runtime error");
        return "error";
    }

    @InitBinder("event") // 반드시 void
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
        webDataBinder.addValidators(new EventValidator());
    }

    @ModelAttribute
    public void categories(Model model) {
        model.addAttribute("categories", Arrays.asList("study", "seminar", "hobby", "social"));
    }

}
