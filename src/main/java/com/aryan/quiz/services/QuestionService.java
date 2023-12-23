package com.aryan.quiz.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aryan.quiz.dao.QuestionDao;
import com.aryan.quiz.models.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;

	public List<Question> getAllQuestions() {
		return questionDao.findAll();
	}

	public List<Question> getQuestionsByCategory(String name) {
		return questionDao.findByCategory(name);
	}

	public Optional<Question> getQuestionById(Integer id) {
		return questionDao.findById(id);
	}

	public String addQuestion(Question question) {
		questionDao.save(question);
		return "success";
	}

	public String deleteQuestion(Integer id) {
		if (questionDao.findById(id).isPresent()) {
			questionDao.deleteById(id);
			return "Deleted !!";
		}
		return "ID not Found";
	}

	public Question updateQuestion(Question question) {
		if (questionDao.findById(question.getId()).isPresent()) {
			return questionDao.save(question);
		}
		return null;
	}
}
