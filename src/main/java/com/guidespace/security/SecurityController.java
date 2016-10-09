package com.guidespace.security;


import com.guidespace.domain.Question;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/rest")
public class SecurityController {

//    @RequestMapping("/")
//    public String question() {
//        return "html/question.html";
//    }

    @GetMapping("/html/question")
    public String greetingForm(Model model) {
        model.addAttribute("question", new Question());
        return "question";
    }

    @PostMapping("/html/question")
    public String greetingSubmit(@ModelAttribute Question question) {
        return "result";
    }

    @RequestMapping(value = "/getCSRF", method = RequestMethod.GET)
    public ResponseEntity<SecurityController> getCSRF() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private static class RequestResponse {
        private String message;

        RequestResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
