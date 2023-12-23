package com.aryan.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aryan.quiz.models.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz, Integer> {
	
}
