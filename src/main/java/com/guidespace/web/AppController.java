package com.guidespace.web;

import com.guidespace.domain.ExamQuestion;
import com.guidespace.service.DuplicateEmailException;
import com.guidespace.service.DuplicateUsernameException;
import com.guidespace.service.ExamQuestionService;
import com.guidespace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExamQuestionService examQuestionService;

    @RequestMapping("/")
    public String index() {
        return "html/index.html";
    }

    @RequestMapping("/question")
    public String question() {
        return "html/question.html";
    }

    @RequestMapping(value = "/isAuth", method = RequestMethod.GET)
    @ResponseBody
    public Boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(authentication instanceof AnonymousAuthenticationToken);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public void authenticate(String username, String password, String email) throws DuplicateEmailException, DuplicateUsernameException {
        userService.register(username, password, email);
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    @ResponseBody
    public void addQuestion(String question){
        examQuestionService.addQuestion(new ExamQuestion(question));
    }
}
