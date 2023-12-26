package com.aryan.quizservice.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.aryan.quizservice.dao.QuizDao;
import com.aryan.quizservice.feign.QuizInterface;
import com.aryan.quizservice.models.QuestionWrapper;
import com.aryan.quizservice.models.Quiz;
import com.aryan.quizservice.models.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;

	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, String noOfQuestion, String title) {

		try {
			List<Integer> questions = quizInterface.getQuestionsForQuiz(category, Integer.parseInt(noOfQuestion))
					.getBody();
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestionIds(questions);
			quizDao.save(quiz);

			return new ResponseEntity<>("Quiz Created !!", HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Quiz cannot be created please check parameters correctly",
				HttpStatus.NOT_ACCEPTABLE);
	}
}
