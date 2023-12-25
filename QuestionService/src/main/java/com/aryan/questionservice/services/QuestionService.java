package com.aryan.questionservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aryan.questionservice.dao.QuestionDao;
import com.aryan.questionservice.models.Question;
import com.aryan.questionservice.models.QuestionWrapper;
import com.aryan.questionservice.models.Response;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String name) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(name), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Optional<Question>> getQuestionById(Integer id) {
		try {
			if (questionDao.findById(id).isPresent())
				return new ResponseEntity<>(questionDao.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(questionDao.findById(id), HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		try {
			questionDao.save(question);
			return new ResponseEntity<>("Question Added", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Question cannot be added please check that no fields are null",
				HttpStatus.NOT_ACCEPTABLE);
	}

	public ResponseEntity<String> deleteQuestion(Integer id) {
		try {
			if (questionDao.findById(id).isPresent()) {
				questionDao.deleteById(id);

				return new ResponseEntity<>("Deleted !!", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Question Not Found !!", HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Question> updateQuestion(Question question) {
		try {
			if (questionDao.findById(question.getId()).isPresent())
				return new ResponseEntity<>(questionDao.save(question), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new Question(), HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer noOfQuestion) {
		try {
			List<Integer> questions = questionDao.findRandomQuestionsByCategory(category, noOfQuestion);
			return new ResponseEntity<>(questions, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_ACCEPTABLE);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestionsById(List<Integer> questionsIds) {

		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();

		// Getting Specified Questions from database
		for (Integer id : questionsIds) {
			questions.add(questionDao.findById(id).get());
		}

		// converting question to questionwrapper
		for (Question q : questions) {
			QuestionWrapper questionWrapper = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(),
					q.getOption2(), q.getOption3(), q.getOption4());
			wrappers.add(questionWrapper);
		}

		return new ResponseEntity<>(wrappers, HttpStatus.OK);

	}

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		try {
			Integer score = 0;
			for (int i = 0; i < responses.size(); i++) {
				Question question = questionDao.findById(responses.get(i).getId()).get();
				if (question.getRightAnswer().equals(responses.get(i).getResponse())) {
					score++;
				}
			}
			return new ResponseEntity<>(score, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(-1, HttpStatus.NOT_ACCEPTABLE);
	}
}
