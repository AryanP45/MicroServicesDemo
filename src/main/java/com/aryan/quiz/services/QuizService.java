package com.aryan.quiz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aryan.quiz.dao.QuizDao;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
	
	
}
