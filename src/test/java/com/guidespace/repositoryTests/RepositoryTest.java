package com.guidespace.repositoryTests;

import com.guidespace.domain.*;
import com.guidespace.repository.*;
import com.guidespace.service.*;
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
    ExamQuestionService examQuestionService;

    @Autowired
    ExamQuestionAnswerService examQuestionAnswerService;

    @Autowired
    ClassificatorService classificatorService;

    @Autowired
    ExaminationService examinationService;

    @Autowired
    ExamResultService examResultService;

    @Autowired
    UserService personService;

    @Autowired
    UserRepository personRepository;

    @Test
    public void examQuestionSaveTest(){

        final String q = "Is this an exam question?";

        ExamQuestion eq = new ExamQuestion(q);

        examQuestionService.addQuestion(eq);

        ExamQuestion dbq = examQuestionService.getQuestionById(Long.valueOf(eq.getId()));

        Assert.assertNotNull(dbq);
        Assert.assertEquals(dbq.getQuestion(), q);
        Assert.assertEquals(0, dbq.getAnswers().size());
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

        examQuestionService.addQuestion(eq1);
        examQuestionService.addQuestion(eq2);

        examQuestionAnswerService.addQuestionAnswer(ea1);
        examQuestionAnswerService.addQuestionAnswer(ea2);
        examQuestionAnswerService.addQuestionAnswer(ea3);
        examQuestionAnswerService.addQuestionAnswer(ea4);

        ExamQuestion dbq1 = examQuestionService.getQuestionById(Long.valueOf(eq1.getId()));
        ExamQuestion dbq2 = examQuestionService.getQuestionById(Long.valueOf(eq2.getId()));

        List<ExamQuestionAnswer> dbanswer = dbq1.getAnswers();
        List<ExamQuestionAnswer> initialAnswers = new ArrayList<>();

        initialAnswers.add(ea1);
        initialAnswers.add(ea2);
        initialAnswers.add(ea3);
        initialAnswers.add(ea4);

        Assert.assertEquals(4, dbanswer.size());
        Assert.assertEquals(0, eq2.getAnswers().size());
        Assert.assertEquals(initialAnswers, dbanswer);

    }

    @Test
    public void examQuestionClassificatorTest(){
        final String q1 = "Is this exam question?";
        final String type = "LOCATION";
        final String code = "LOCATION_NORTH";
        final String name = "Pohja-Eesti";

        Classificator c = new Classificator(type, code, name);
        classificatorService.addClassificator(c);

        ExamQuestion q = new ExamQuestion(q1);
        q.setClassificator(c);
        examQuestionService.addQuestion(q);

        ExamQuestion dbq = examQuestionService.getQuestionById(Long.valueOf(q.getId()));
        Assert.assertEquals(q ,dbq);
        Assert.assertEquals(c, dbq.getClassificator());
        Assert.assertEquals(code, dbq.getClassificator().getClassif_code());

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

        examinationService.addExamination(e1);
        examinationService.addExamination(e2);
        examinationService.addExamination(e3);

        Examination dbExam1 = examinationService.getById(Long.valueOf(e1.getId()));
        Examination dbExam2 = examinationService.getById(Long.valueOf(e2.getId()));
        Examination dbExam3 = examinationService.getById(Long.valueOf(e3.getId()));

        Assert.assertNotNull(dbExam1);
        Assert.assertNotNull(dbExam2);
        Assert.assertNotNull(dbExam3);

        Assert.assertEquals(e1.getId(), dbExam1.getId());
        Assert.assertEquals(e2.getId(), dbExam2.getId());
        Assert.assertEquals(e3.getId(), dbExam3.getId());

        Assert.assertEquals(new ArrayList<ExamResult>(), dbExam1.getResults());
        Assert.assertEquals(new ArrayList<ExamResult>(), dbExam2.getResults());
        Assert.assertEquals(new ArrayList<ExamResult>(), dbExam3.getResults());
    }
}
