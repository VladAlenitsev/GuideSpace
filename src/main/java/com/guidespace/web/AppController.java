package com.guidespace.web;

import com.guidespace.domain.ExamQuestion;
import com.guidespace.domain.Person;
import com.guidespace.service.DuplicateEmailException;
import com.guidespace.service.DuplicateUsernameException;
import com.guidespace.service.ExamQuestionService;
import com.guidespace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;


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


    @RequestMapping(value = "/isAdmin", method = RequestMethod.GET)
    @ResponseBody
    public Boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
    }

    @RequestMapping(value = "/giveAdmin", method = RequestMethod.GET)
    @ResponseBody
    public String isAuthenticated2() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(authentication.getName());
        leo.setUser_role_id(2);
        userService.update(leo);
        return authentication.getAuthorities().toString();
    }

    @RequestMapping(value = "/isTest", method = RequestMethod.GET)
    @ResponseBody
    public String isAuthenticated3() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().toString();
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

    @RequestMapping(value = "/getQuestions", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ArrayList<String> getQuestions() {
        ArrayList<String> result = new ArrayList<String>();
        for (ExamQuestion eq: examQuestionService.getQuestions()){
            result.add(eq.getQuestion());
        }
        return result;
    }


    @RequestMapping(value = "/getUsers", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ArrayList<String> getUsers() {
        ArrayList<String> result = new ArrayList<String>();
        for (Person eq: userService.getUsers()){
            result.add(eq.getUsername());
        }
        return result;
    }
}
