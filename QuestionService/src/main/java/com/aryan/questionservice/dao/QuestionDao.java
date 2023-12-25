package com.aryan.questionservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.aryan.questionservice.models.Question;

import java.util.List;


@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{
	List<Question> findByCategory(String category);

	@Query(value = "select q.id from question q Where q.category =:category ORDER BY RANDOM() LIMIT :noOfQuestion",nativeQuery=true)
	List<Integer> findRandomQuestionsByCategory(String category, Integer noOfQuestion);

}
