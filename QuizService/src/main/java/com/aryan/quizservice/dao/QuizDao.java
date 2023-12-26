package com.aryan.quizservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aryan.quizservice.models.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
	
}
