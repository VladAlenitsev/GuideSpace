package com.guidespace.web;

import com.guidespace.domain.Classificator;
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
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private ClassificatorService classificatorService;


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

    @RequestMapping("/adminpanel")
    public String panel() {
        return "html/adminpanel.html";
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
        Person leo = userService.getUser(authentication.getName());
        if (leo.getUser_role_id() == 2){
            return true;
        }
        return false;
    }

//    @RequestMapping(value = "/isAdmin", method = RequestMethod.GET)
//    @ResponseBody
//    public Boolean isAdmin() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"));
//    }

    @RequestMapping(value = "/isVerified", method = RequestMethod.GET)
    @ResponseBody
    public Boolean isVerified() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(authentication.getName());
        if (leo.getUser_role_id() == 4){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/isUnVerified", method = RequestMethod.GET)
    @ResponseBody
    public Boolean isUnVerified() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(authentication.getName());
        if (leo.getUser_role_id() == 1){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/isQuestionAdder", method = RequestMethod.GET)
    @ResponseBody
    public Boolean isQuestionAdder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(authentication.getName());
        if (leo.getUser_role_id() == 6){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/giveAdminToSomeone", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String isAuthenticated22(@RequestBody Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(id);
        leo.setUser_role_id(2);
        userService.update(leo);
        return authentication.getAuthorities().toString();
    }

    @RequestMapping(value = "/giveUnverifiedToSomeone", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String isAuthenticated11(@RequestBody Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(id);
        leo.setUser_role_id(1);
        userService.update(leo);
        return authentication.getAuthorities().toString();
    }

    @RequestMapping(value = "/giveVerifiedToSomeone", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String isAuthenticated44(@RequestBody Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(id);
        leo.setUser_role_id(4);
        userService.update(leo);
        return authentication.getAuthorities().toString();
    }

    @RequestMapping(value = "/giveQuestionAdderToSomeone", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String isAuthenticated66(@RequestBody Long id) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(id);
        leo.setUser_role_id(6);
        userService.update(leo);
        return authentication.getAuthorities().toString();
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

    @RequestMapping(value = "/giveVerified", method = RequestMethod.GET)
    @ResponseBody
    public String isAuthenticated4() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(authentication.getName());
        leo.setUser_role_id(4);
        userService.update(leo);
        return authentication.getAuthorities().toString();
    }

    @RequestMapping(value = "/giveUnverified", method = RequestMethod.GET)
    @ResponseBody
    public String isAuthenticated1() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(authentication.getName());
        leo.setUser_role_id(1);
        userService.update(leo);
        return authentication.getAuthorities().toString();
    }

    @RequestMapping(value = "/giveQuestionAdder", method = RequestMethod.GET)
    @ResponseBody
    public String isAuthenticated6() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person leo = userService.getUser(authentication.getName());
        leo.setUser_role_id(6);
        userService.update(leo);
        return authentication.getAuthorities().toString();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public void authenticate(String username, String password, String email) throws DuplicateEmailException, DuplicateUsernameException {
        userService.register(username, password, email);
    }

    @RequestMapping(value = "/getAnswers", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<String> getAnswers(String question) {
        ExamQuestion uus = examQuestionService.getQuestion(question);
        ArrayList<String> result = new ArrayList<String>();
        for (ExamQuestionAnswer eq : uus.getAnswers()) {
            result.add(eq.getAnswer());
        }
        return result;
    }

    //saves question and 4 answers
    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public void addQuestion(@RequestBody Map<String, List<String>> params) {

        Classificator classif = classificatorService.getClassifById(Long.valueOf(params.get("classif").get(0)));
        ExamQuestion q = new ExamQuestion(params.get("question").get(0));
        List<ExamQuestionAnswer> answers = new ArrayList<>();

        for (String s : params.get("correctAnswers")) {
            answers.add(new ExamQuestionAnswer(false, s, q));
        }
        for (String s : params.get("wrongAnswers")) {
            answers.add(new ExamQuestionAnswer(true, s, q));
        }
        q.setClassificator(classif);
        q.setAnswers(answers);

        examQuestionService.addQuestion(q);
        for (ExamQuestionAnswer a : answers) {
            examQuestionAnswerService.addQuestionAnswer(a);
        }
        System.out.println("Hibernate: New exam question saved. Question id: " + q.getId());
    }


    @RequestMapping(value = "/getQuestions", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ArrayList<String> getQuestions() {
        ArrayList<String> result = new ArrayList<String>();
        for (ExamQuestion eq : examQuestionService.getQuestions()) {
            result.add(eq.getQuestion());
        }
        return result;
    }

    @RequestMapping(value = "/getClassificators", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ArrayList<Classificator> getClassificators() {
        ArrayList<Classificator> result = new ArrayList<Classificator>();
        for (Classificator eq : classificatorService.getClassificators()) {
            result.add(eq);
        }
        return result;
    }

    @RequestMapping(value = "/getPersons", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ArrayList<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<Person>();
        for (Person p : userService.getUsers()) {
            persons.add(p);
        }
        return persons;
    }

    @RequestMapping(value = "/getAll", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public HashMap<String, List<String>> getAll() {
        HashMap<String, List<String>> uus = new HashMap<>();
        for (ExamQuestion eq : examQuestionService.getQuestions()) {
            ArrayList<String> result = new ArrayList<String>();
            for (ExamQuestionAnswer eq2 : eq.getAnswers()) {
                result.add(eq2.getAnswer());
            }
            uus.put(eq.getQuestion(), result);
        }
        return uus;
    }

    @RequestMapping(value = "/getUsers", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public ArrayList<String> getUsers() {
        ArrayList<String> result = new ArrayList<String>();
        for (Person eq : userService.getUsers()) {
            result.add(eq.getUsername());
        }
        return result;
    }

    @RequestMapping(value = "/addQuests")
    @ResponseBody
    public void addQuests() {
        /**
         ExamQuestion b = new ExamQuestion("Esimene Küsimus(first 2 are correct)");
         ExamQuestion b1 = new ExamQuestion("Teine Küsimus(first 2 are correct)");
         ExamQuestion b2 = new ExamQuestion("Kolmas Küsimus(first 2 are correct)");
         String a1 = "Yes this is.";
         String a2 = "No, it's not.";
         String a3 = "Maybe.";
         String a4 = "It's classified.";
         String c1 = "Yes this is.";
         String c2 = "No, it's not.";
         String c3 = "Maybe.";
         String c4 = "It's classified.";
         String d1 = "Yes this is.";
         String d2 = "No, it's not.";
         String d3 = "Maybe.";
         String d4 = "It's classified.";
         Boolean tf = true;
         Boolean mf = false;
         ExamQuestionAnswer ea1 = new ExamQuestionAnswer(tf, a1);
         ExamQuestionAnswer ea2 = new ExamQuestionAnswer(tf, a2);
         ExamQuestionAnswer ea3 = new ExamQuestionAnswer(mf, a3);
         ExamQuestionAnswer ea4 = new ExamQuestionAnswer(mf, a4);
         ExamQuestionAnswer ec1 = new ExamQuestionAnswer(tf, c1);
         ExamQuestionAnswer ec2 = new ExamQuestionAnswer(tf, c2);
         ExamQuestionAnswer ec3 = new ExamQuestionAnswer(mf, c3);
         ExamQuestionAnswer ec4 = new ExamQuestionAnswer(mf, c4);
         ExamQuestionAnswer ed1 = new ExamQuestionAnswer(tf, d1);
         ExamQuestionAnswer ed2 = new ExamQuestionAnswer(tf, d2);
         ExamQuestionAnswer ed3 = new ExamQuestionAnswer(mf, d3);
         ExamQuestionAnswer ed4 = new ExamQuestionAnswer(mf, d4);
         b.getAnswers().add(ea1);
         b.getAnswers().add(ea2);
         b.getAnswers().add(ea3);
         b.getAnswers().add(ea4);
         b1.getAnswers().add(ec1);
         b1.getAnswers().add(ec2);
         b1.getAnswers().add(ec3);
         b1.getAnswers().add(ec4);
         b2.getAnswers().add(ed1);
         b2.getAnswers().add(ed2);
         b2.getAnswers().add(ed3);
         b2.getAnswers().add(ed4);
         ea1.setExamQuestion(b);
         ea2.setExamQuestion(b);
         ea3.setExamQuestion(b);
         ea4.setExamQuestion(b);
         ec1.setExamQuestion(b1);
         ec2.setExamQuestion(b1);
         ec3.setExamQuestion(b1);
         ec4.setExamQuestion(b1);
         ed1.setExamQuestion(b2);
         ed2.setExamQuestion(b2);
         ed3.setExamQuestion(b2);
         ed4.setExamQuestion(b2);
         examQuestionService.addQuestion(b);
         examQuestionService.addQuestion(b1);
         examQuestionService.addQuestion(b2);
         examQuestionAnswerService.addQuestionAnswer(ea1);
         examQuestionAnswerService.addQuestionAnswer(ea2);
         examQuestionAnswerService.addQuestionAnswer(ea3);
         examQuestionAnswerService.addQuestionAnswer(ea4);
         examQuestionAnswerService.addQuestionAnswer(ec1);
         examQuestionAnswerService.addQuestionAnswer(ec2);
         examQuestionAnswerService.addQuestionAnswer(ec3);
         examQuestionAnswerService.addQuestionAnswer(ec4);
         examQuestionAnswerService.addQuestionAnswer(ed1);
         examQuestionAnswerService.addQuestionAnswer(ed2);
         examQuestionAnswerService.addQuestionAnswer(ed3);
         examQuestionAnswerService.addQuestionAnswer(ed4);*/
    }


    @RequestMapping(value = "/listTest", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String getValues(@RequestBody Map<String, List<String>> hmap) {
        int size = hmap.size();
        int counter = 0;
        for (String key : hmap.keySet()) {
            ExamQuestion examQuestion = examQuestionService.getQuestion(key);
            System.err.println(examQuestion.getQuestion());
            List<String> trueQuestionAnswers = examQuestion.getRightAnswers();
            List<String> gotQuestionAnswers = hmap.get(key);
            if (trueQuestionAnswers.size() == gotQuestionAnswers.size()) {
                for (String gotQuest : gotQuestionAnswers) {
                    if (!trueQuestionAnswers.contains(gotQuest)) {
                        counter += 1;
                        break;
                    }
                }
            } else counter += 1;
        }
        return "Valesti vastatuid on: " + Integer.toString(counter);
    }


}
