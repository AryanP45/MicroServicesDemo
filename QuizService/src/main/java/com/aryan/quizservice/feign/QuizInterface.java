package com.aryan.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.aryan.quizservice.models.QuestionWrapper;
import com.aryan.quizservice.models.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	
	// Methods that will be used in Quiz-Service from Question-Service
	// all 3 methods should be declared here
	
	@GetMapping("/question/generate")
		public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,
				@RequestParam Integer noOfQuestions);

	@PostMapping("/question/getQuestions")
		public ResponseEntity<List<QuestionWrapper>> getQuestionsWithoutAnswers(@RequestBody List<Integer> questionsIds);

	@PostMapping("/question/getscore")
		public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
