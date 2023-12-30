package com.aryan.questionservice;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.aryan.questionservice.controllers.QuestionController;
import com.aryan.questionservice.dao.QuestionDao;
import com.aryan.questionservice.models.Question;
import com.aryan.questionservice.services.QuestionService;

@TestInstance(Lifecycle.PER_CLASS)
public class QuestionControllerTest {
	private QuestionController questionController;

	@Mock
	QuestionDao questionDao;

	@InjectMocks
	private QuestionService questionService;

	private MockMvc mockMvc;

	public QuestionControllerTest() {
		questionService = Mockito.mock(QuestionService.class);
		questionController = new QuestionController(questionService);

	}

	@BeforeAll
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(questionController).build();
	}
	
	// Testing : @GetMapping("/allquestions")
	
	@Test
	public void whenQuestionsListExists_200() throws Exception {
		Question question = new Question(1, "demo title", "op1", "op2", "op3", "op4", "right_answer", "easy/hard",
				"prog_lang_category");
		List<Question> questions = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			question.setId(i);
			questions.add(question);
		}
		Mockito.doReturn(new ResponseEntity<>(questions, HttpStatus.OK)).when(questionService).getAllQuestions();
		mockMvc.perform(MockMvcRequestBuilders.get("/question/allquestions").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$[1].category", is("prog_lang_category")));
	}

	@Test
	public void whenQuestionsListNotExists_400() throws Exception {
		Mockito.doReturn(new ResponseEntity<>(null, HttpStatus.NOT_FOUND)).when(questionService).getAllQuestions();
		mockMvc.perform(MockMvcRequestBuilders.get("/question/allquestions").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andDo(print());
	}
	}
	
	
}
