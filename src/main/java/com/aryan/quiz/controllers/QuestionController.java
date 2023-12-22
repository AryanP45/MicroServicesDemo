package com.aryan.quiz.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.quiz.models.Question;
import com.aryan.quiz.services.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@GetMapping("/allquestions")
	public List<Question> getAllQuestion() {
		return questionService.getAllQuestions();
	}

	@GetMapping("/category/{categoryName}")
	public List<Question> getQuestionsByCategory(@PathVariable String categoryName) {
		// categoryName is case sensitive
		return questionService.getQuestionsByCategory(categoryName);
	}

	@GetMapping("/{id}")
	public Optional<Question> getQuestionById(@PathVariable Integer id) throws JsonProcessingException {
		return questionService.getQuestionById(id);

	}
}
