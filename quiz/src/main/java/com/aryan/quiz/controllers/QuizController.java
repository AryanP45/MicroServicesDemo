package com.aryan.quiz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.quiz.models.QuestionWrapper;
import com.aryan.quiz.models.Quiz;
import com.aryan.quiz.models.Response;
import com.aryan.quiz.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService quizService;

	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam String noOfQuestion,
			@RequestParam String title) {
		return quizService.createQuiz(category, noOfQuestion, title);

	}

	@GetMapping("/all")
	public ResponseEntity<List<Quiz>> getAllQuiz() {
		return quizService.getAllQuiz();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@PathVariable Integer id){
		return quizService.getQuizQuestionsById(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
		return quizService.calculateResult(id,responses);
	}
}
