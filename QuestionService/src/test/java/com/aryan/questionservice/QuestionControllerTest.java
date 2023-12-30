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
	
	// Testing  : @GetMapping("/{id}")

	@Test
	public void whenQuestionExists_200() throws Exception {
		// Define the Question ID for the test
		int questionId = 1;
		Question question = new Question(questionId, "demo title", "op1", "op2", "op3", "op4", "right_answer",
				"easy/hard", "prog_lang_category");

		// Mocking the service behavior to return an Optional containing a specific
		// Question instance
		Mockito.doReturn(new ResponseEntity<>(Optional.of(question), HttpStatus.OK)).when(questionService)
				.getQuestionById(questionId);

		// Performing an HTTP GET request to retrieve an Question by ID
		ResultActions response = mockMvc.perform(
				MockMvcRequestBuilders.get("/question/{id}", questionId).contentType(MediaType.APPLICATION_JSON));

		// Asserting the response expectations
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.category", is("prog_lang_category")));
	}

	@Test
	public void whenQuestionExistsButIsNull_200() throws Exception {
		// Define the Question ID for the test
		int questionId = 1;
		Question question = new Question();
		// Mocking the service behavior to return an Optional containing a specific
		// Question instance
		Mockito.doReturn(new ResponseEntity<>(Optional.of(question), HttpStatus.OK)).when(questionService)
				.getQuestionById(questionId);

		// Performing an HTTP GET request to retrieve an Question by ID
		mockMvc.perform(
				MockMvcRequestBuilders.get("/question/{id}", questionId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.category").doesNotExist());
	}

	@Test
	public void whenQuestionDoesNotExists_400() throws Exception {
		// Define the Question ID for the test
		int questionId = 1;
		// Mocking the service behavior to return an Optional containing a specific
		// Question instance
		Mockito.doReturn(new ResponseEntity<>(Optional.empty(), HttpStatus.NOT_FOUND)).when(questionService)
				.getQuestionById(questionId);

		// Performing an HTTP GET request to retrieve an Question by ID
		mockMvc.perform(
				MockMvcRequestBuilders.get("/question/{id}", questionId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andDo(print());
	}
	
	
}
