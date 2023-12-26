package com.aryan.quizservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizDto {
	String category;
	String noOfQuestions;
	String title;
}
