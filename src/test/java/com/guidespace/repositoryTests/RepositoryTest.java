package com.guidespace.repositoryTests;

import com.guidespace.domain.*;
import com.guidespace.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vlad on 02.11.2016.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional //rolls back any changes in DB after test, kek
public class RepositoryTest {

    @Autowired
    ExamQuestionRepository examQuestionRepository;

    @Autowired
    ExamQuestionAnswerRepository examQuestionAnswerRepository;

    @Autowired
    ClassificatorRepository classificatorRepository;

    @Autowired
    ExaminationRepository examinationRepository;

    @Autowired
    ExamResultRepository examResultRepository;

    @Autowired
    UserRepository personRepository;

    @Test
    public void examQuestionSaveTest(){

        final String q = "Is this an exam question?";

        ExamQuestion eq = new ExamQuestion(q);

        examQuestionRepository.save(eq);

        List<ExamQuestion> dbq = examQuestionRepository.findAll();

        ExamQuestion dbq1 = dbq.get(0);

        Assert.assertNotNull(dbq1);
        Assert.assertEquals(1, dbq.size());
        Assert.assertEquals(dbq1.getQuestion(), q);
        Assert.assertEquals(0, dbq1.getAnswers().size());
    }

    @Test
    public void examQuestionAnswerSaveTest(){
        final String q1 = "Is this exam question?";
        final String q2 = "Is this another exam question?";
        final String a1 = "Yes this is.";
        final String a2 = "No, it's not.";
        final String a3 = "Maybe.";
        final String a4 = "It's classified.";
        final Boolean tf = true;
        final Boolean mf = false;

        ExamQuestion eq1 = new ExamQuestion(q1);
        ExamQuestion eq2 = new ExamQuestion(q2);

        ExamQuestionAnswer ea1 = new ExamQuestionAnswer(tf, a1);
        ExamQuestionAnswer ea2 = new ExamQuestionAnswer(tf, a2);
        ExamQuestionAnswer ea3 = new ExamQuestionAnswer(mf, a3);
        ExamQuestionAnswer ea4 = new ExamQuestionAnswer(mf, a4);

        eq1.getAnswers().add(ea1);
        eq1.getAnswers().add(ea2);
        eq1.getAnswers().add(ea3);
        eq1.getAnswers().add(ea4);

        ea1.setExamQuestion(eq1);
        ea2.setExamQuestion(eq1);
        ea3.setExamQuestion(eq1);
        ea4.setExamQuestion(eq1);

        examQuestionRepository.save(eq1);
        examQuestionRepository.save(eq2);

        examQuestionAnswerRepository.save(ea1);
        examQuestionAnswerRepository.save(ea2);
        examQuestionAnswerRepository.save(ea3);
        examQuestionAnswerRepository.save(ea4);

        List<ExamQuestion> dbquestion = examQuestionRepository.findAll();
        ExamQuestion dbq1 = dbquestion.get(0);
        List<ExamQuestionAnswer> dbanswer = examQuestionAnswerRepository.findAll();

        Assert.assertEquals(2, dbquestion.size());
        Assert.assertEquals(4, dbanswer.size());
        Assert.assertEquals(0, eq2.getAnswers().size());
        Assert.assertEquals(4, eq1.getAnswers().size());

        Assert.assertEquals(eq1, dbq1);
        for(ExamQuestionAnswer eqa: dbanswer){
            Assert.assertEquals(dbq1.getId(), eqa.getExamQuestion().getId());
        }
    }

    @Test
    public void examQuestionClassificatorTest(){
        final String q1 = "Is this exam question?";
        final String type = "LOCATION";
        final String code = "LOCATION_NORTH";
        final String name = "Pohja-Eesti";

        Classificator c = new Classificator(type, code, name);
        classificatorRepository.save(c);

        ExamQuestion q = new ExamQuestion(q1);
        q.setClassificator(c);
        examQuestionRepository.save(q);

        List<ExamQuestion> dbq = examQuestionRepository.findAll();
        Assert.assertEquals(q ,dbq.get(0));
        Assert.assertEquals(c, dbq.get(0).getClassificator());
        Assert.assertEquals(code, dbq.get(0).getClassificator().getClassif_code());

    }

    @Test
    public void examinationsSaveTest() throws ParseException {
        //"dd-MM-yyyy HH:mm"
        final DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        final String startDate1 = "02-11-2016 09:00";
        final String endDate1 = "02-11-2016 12:00";
        final String startDate2 = "15-01-2016 13:00";
        final String endDate2 = "15-01-2016 18:00";
        final String startDate3 = "16-01-2016 13:00";
        final String endDate3 = "16-01-2016 18:00";
        final Date sd3 = df.parse(startDate3);
        final Date ed3 = df.parse(endDate3);

        Examination e1 = new Examination();
        Examination e2 = new Examination(startDate2);
        Examination e3 = new Examination(startDate3, endDate3);

        e1.setStart_date(startDate1);
        e1.setEnd_date(endDate1);
        e2.setEnd_date(endDate2);
        e3.setStart_date(sd3);
        e3.setEnd_date(ed3);

        examinationRepository.save(e1);
        examinationRepository.save(e2);
        examinationRepository.save(e3);

        List<Examination> dbexams = examinationRepository.findAll();

        Assert.assertEquals(3, dbexams.size());

        Examination dbExam1 = dbexams.get(0);
        Examination dbExam2 = dbexams.get(1);
        Examination dbExam3 = dbexams.get(2);

        Assert.assertNotNull(dbExam1);
        Assert.assertNotNull(dbExam2);
        Assert.assertNotNull(dbExam3);

        Assert.assertEquals(e1.getId(), dbExam1.getId());
        Assert.assertEquals(e2.getId(), dbExam2.getId());
        Assert.assertEquals(e3.getId(), dbExam3.getId());

        Assert.assertEquals(new ArrayList<ExamResult>(), dbExam1.getResults());
    }

    @Test
    public void examResultPersonExamSaveTest() throws ParseException {
        final Boolean tf = true;
        final Boolean mf = false;
        final Integer s1 = 99;
        final Integer s2 = 32;
        final DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        final Date sd1 = df.parse("02-11-2016 09:00");
        final Date ed1 = df.parse("02-11-2016 12:00");
        final String username = "VasjaPupkin";
        final String email = "vasjapupkin@gmail.com";
        final String hash = "NKUxcRLwTiUlxfR4Z4EXrWGXVkU/o9bJMT6sma2J6SmoOjV/QhjXn3PP/jqE4XV1qvqqifN3lbqVHEL9wjo5ZA==";
        final String salt = "+U43D57Z4gPq4rcHduvhWGrwzR4UngHQpQJcCs02XZo=";

        Examination e = new Examination(sd1, ed1);
        ExamResult er1 = new ExamResult(tf, s1);
        ExamResult er2 = new ExamResult(mf, s2);
        Person p1 = new Person();

        e.getResults().add(er1);
        e.getResults().add(er2);

        er1.setExamination(e);
        er2.setExamination(e);
        er1.setPerson(p1);
        er2.setPerson(p1);

        p1.setUsername(username);
        p1.setEmailAddress(email);
        p1.setPasswordHash(hash);
        p1.setPasswordSalt(salt);
        p1.getResults().add(er1);
        p1.getResults().add(er2);

        examinationRepository.save(e);
        examResultRepository.save(er1);
        examResultRepository.save(er2);
        personRepository.save(p1);

        Examination dbE = examinationRepository.findAll().get(0);
        List<ExamResult> dbER = examResultRepository.findAll();
        ExamResult dbER1 = dbER.get(0);
        ExamResult dbER2 = dbER.get(1);
        Person dbp = personRepository.findAll().get(0);
        Person dbp1 = personRepository.findByUsername(username);
        List<ExamResult> personsER = dbp.getResults();

        Assert.assertNotNull(dbE);
        Assert.assertNotNull(dbER);
        Assert.assertNotNull(dbp);
        Assert.assertNotNull(dbp1);

        Assert.assertEquals(er1, dbER1);
        Assert.assertEquals(er2, dbER2);
        Assert.assertEquals(dbp, dbp1);

        Assert.assertEquals(p1, dbp);
        Assert.assertEquals(p1, dbp1);

        Assert.assertEquals(dbER1.getPerson().getId(), dbp.getId());
        Assert.assertEquals(dbER2.getPerson().getId(), dbp.getId());
        Assert.assertEquals(dbER, personsER);

    }
}
