package com.aryan.questionservice.controllers;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aryan.questionservice.models.Question;
import com.aryan.questionservice.models.QuestionWrapper;
import com.aryan.questionservice.models.Response;
import com.aryan.questionservice.services.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

	private final QuestionService questionService;

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
	public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable Integer id) {
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

	// Generate
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,
			@RequestParam Integer noOfQuestions) {
		return questionService.getQuestionsForQuiz(category, noOfQuestions);
	}

	// getQuestion (from questionID)
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsWithoutAnswers(@RequestBody List<Integer> questionsIds) {
		return questionService.getQuestionsById(questionsIds);
	}

	// getscore
	@PostMapping("/getscore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
		return questionService.getScore(responses);
	}
}
