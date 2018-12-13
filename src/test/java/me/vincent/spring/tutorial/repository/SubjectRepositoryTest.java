package me.vincent.spring.tutorial.repository;

import me.vincent.spring.tutorial.domain.Subject;
import me.vincent.spring.tutorial.repositoroy.mongodb.ScoresRepository;
import me.vincent.spring.tutorial.repositoroy.mongodb.SubjectRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataMongoTest
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
public class SubjectRepositoryTest {

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
    private ScoresRepository scoresRepository;

	@Before
    public void prepare(){
	    subjectRepository.deleteAll();
	    scoresRepository.deleteAll();
    }

	@Test
	public void testSubjectRepository() {
		Subject subject = new Subject();
		subject.setSubjectName("testSubject");

        //Test insert
		subjectRepository.insert(subject);
		List<Subject> subjects = subjectRepository.findBySubjectName("testSubject");
		Assert.assertEquals(1, subjects.size());

		Subject testTargetSubject = subjects.get(0);
		testTargetSubject.setSubjectName("testSubjectModified");

		//Test update
        subjectRepository.save(testTargetSubject);
        Assert.assertEquals(1, subjectRepository.findBySubjectName("testSubjectModified").size());
        Assert.assertEquals(0, subjectRepository.findBySubjectName("testSubject").size());


		subjectRepository.delete(testTargetSubject);
        Assert.assertEquals(0, subjectRepository.findBySubjectName("testSubjectModified").size());
	}

}
