package com.aryan.quiz.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam String noOfQuestion,@RequestParam String title){
		
		return new ResponseEntity<>("Quiz Created"
				+ "Category : "+category
				+ "Number of Question : "+Integer.parseInt(noOfQuestion)
				+ "Title : "+title,HttpStatus.OK);
		
	}
}
