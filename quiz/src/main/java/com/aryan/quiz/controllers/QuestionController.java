package com.aryan.quiz.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<List<Question>> getAllQuestion() {
		return questionService.getAllQuestions();
	}

	@GetMapping("/category/{categoryName}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String categoryName) {
		// categoryName is case sensitive
		return questionService.getQuestionsByCategory(categoryName);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable Integer id) throws JsonProcessingException {
		return questionService.getQuestionById(id);

	}

	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
		return questionService.deleteQuestion(id);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question, @PathVariable Integer id) {
		question.setId(id);
		return questionService.updateQuestion(question);
	}
}
