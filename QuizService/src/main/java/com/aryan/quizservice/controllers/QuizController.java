package com.aryan.quizservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.quizservice.models.QuestionWrapper;
import com.aryan.quizservice.models.Quiz;
import com.aryan.quizservice.models.QuizDto;
import com.aryan.quizservice.models.Response;
import com.aryan.quizservice.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService quizService;

	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
		return quizService.createQuiz(quizDto.getCategory(), quizDto.getNoOfQuestions(), quizDto.getTitle());

	}

	@GetMapping("/all")
	public ResponseEntity<List<Quiz>> getAllQuiz() {
		return quizService.getAllQuiz();
	}
}
