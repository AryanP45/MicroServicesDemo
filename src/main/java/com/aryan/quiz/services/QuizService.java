package com.aryan.quiz.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aryan.quiz.dao.QuestionDao;
import com.aryan.quiz.dao.QuizDao;
import com.aryan.quiz.models.Question;
import com.aryan.quiz.models.Quiz;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, String noOfQuestion, String title) {
		try {
			List<Question> questions = questionDao.findRandomQuestionsByCategory(category,
					Integer.parseInt(noOfQuestion));
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestions(questions);
			quizDao.save(quiz);
			return new ResponseEntity<>(quiz.toString() + " Success", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Quiz cannot be created please check parameters correctly",
				HttpStatus.NOT_ACCEPTABLE);
	}
}
