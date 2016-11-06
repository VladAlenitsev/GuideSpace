package com.guidespace.web;

import com.guidespace.domain.ExamQuestion;
import com.guidespace.domain.ExamQuestionAnswer;
import com.guidespace.domain.Person;
import com.guidespace.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class AppController {

    @Autowired
    private UserService userService;

    @Autowired
    private ExamQuestionService examQuestionService;

    @Autowired
    private ExamQuestionAnswerService examQuestionAnswerService;


    @RequestMapping("/")
    public String index() {
        return "html/index.html";
    }

    @RequestMapping("/kkk")
    public String kkk() {
        return "html/kkk.html";
    }

    @RequestMapping("/kontakt")
    public String kontakt() {
        return "html/kontakt.html";
    }


    @RequestMapping("/exam")
    public String exam() {
        return "html/exam.html";
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


    @RequestMapping(value = "/getAnswers", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<String> getAnswers(String question)  {
        ExamQuestion uus = examQuestionService.getQuestion(question).get(0);
        ArrayList<String> result = new ArrayList<String>();
        for (ExamQuestionAnswer eq: uus.getAnswers()){
            result.add(eq.getAnswer());
        }
        return result;
    }


    //saves question and 4 answers
    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public void addQuestion(@RequestBody Map<String, List<String>> params){
        System.out.println("Printing stuff");
        System.out.println(params);

        ExamQuestion q = new ExamQuestion(params.get("question").get(0));
        List<ExamQuestionAnswer> answers = new ArrayList<>();

        for(String s: params.get("correctAnswers")){
            answers.add(new ExamQuestionAnswer(false, s, q));
        }
        for(String s: params.get("wrongAnswers")){
            answers.add(new ExamQuestionAnswer(true, s, q));
        }
        q.setAnswers(answers);

        examQuestionService.addQuestion(q);
        for(ExamQuestionAnswer a: answers){
            examQuestionAnswerService.addQuestionAnswer(a);
        }
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


    @RequestMapping(value = "/getAll", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public HashMap<String,List<String>> getAll() {
        HashMap<String,List<String>> uus = new HashMap<>();
        for (ExamQuestion eq: examQuestionService.getQuestions()){
            ArrayList<String> result = new ArrayList<String>();
            for (ExamQuestionAnswer eq2: eq.getAnswers()){
                result.add(eq2.getAnswer());
            }
            uus.put(eq.getQuestion(),result);
        }
        return uus;
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



/**
    @RequestMapping(value = "/addQuests")
    @ResponseBody
    public void addQuests() {
        ExamQuestion b = new ExamQuestion("Esimene Küsimus");
        ExamQuestion b1 = new ExamQuestion("Teine Küsimus");
        ExamQuestion b2 = new ExamQuestion("Kolmas Küsimus");
        String a1 = "Yes this is.";
        String a2 = "No, it's not.";
        String a3 = "Maybe.";
        String a4 = "It's classified.";
        Boolean tf = true;
        Boolean mf = false;
        ExamQuestionAnswer ea1 = new ExamQuestionAnswer(tf, a1);
        ExamQuestionAnswer ea2 = new ExamQuestionAnswer(tf, a2);
        ExamQuestionAnswer ea3 = new ExamQuestionAnswer(mf, a3);
        ExamQuestionAnswer ea4 = new ExamQuestionAnswer(mf, a4);
        b.getAnswers().add(ea1);
        b.getAnswers().add(ea2);
        b.getAnswers().add(ea3);
        b.getAnswers().add(ea4);
        ea1.setExamQuestion(b);
        ea2.setExamQuestion(b);
        ea3.setExamQuestion(b);
        ea4.setExamQuestion(b);
        examQuestionService.addQuestion(b);
        examQuestionService.addQuestion(b1);
        examQuestionService.addQuestion(b2);
        examQuestionAnswerService.addQuestionAnswer(ea1);
        examQuestionAnswerService.addQuestionAnswer(ea2);
        examQuestionAnswerService.addQuestionAnswer(ea3);
        examQuestionAnswerService.addQuestionAnswer(ea4);
    }*/
}
