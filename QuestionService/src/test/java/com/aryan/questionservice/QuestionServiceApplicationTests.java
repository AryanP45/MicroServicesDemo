package com.aryan.questionservice;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.aryan.questionservice.controllers.QuestionController;


@SpringBootTest
class QuestionServiceApplicationTests {

	@Autowired
	private QuestionController controller;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	

}
