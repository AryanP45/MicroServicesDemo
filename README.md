<center>

# MicroServices Demo in Spring Boot
</center>

# Question Service Endpoints
> /question

**getAllQuestion [GET]**
> /allquestions/{id}  
```java
public ResponseEntity<List<Question>> getAllQuestion()
```

**getQuestionById [GET]**
> /{id}  
```java
public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable Integer id)
```

**getQuestionsByCategory [GET]**
> /category/{categoryName}
```java
public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String categoryName)
```

**addQuestion [POST]**
> /add
```java
public ResponseEntity<String> addQuestion(@RequestBody Question question)
```

**updateQuestion [PUT]**
> /update/{id}
```java
public ResponseEntity<Question> updateQuestion(@RequestBody Question question, @PathVariable Integer id)
```

**deleteQuestion [DELETE]**
> /delete/{id}
```java
public ResponseEntity<String> deleteQuestion(@PathVariable Integer id)
```

**getQuestionsForQuiz [GET]**
> /generate?category=\<String>&noOfQuestions=\<Integer>
```java
public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,
			@RequestParam Integer noOfQuestions)
```

**getQuestionsWithoutAnswers [POST]**
> /getQuestions
```java
public ResponseEntity<List<QuestionWrapper>> getQuestionsWithoutAnswers(@RequestBody List<Integer> questionsIds)
```

**getScore [POST]**
> /getScore
```java
public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
```

# Quiz Service Endpoints
> /quiz

**createQuiz [POST]**
> /create
```java
public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
```

**getAllQuiz [GET]**
> /all
```java
public ResponseEntity<List<Quiz>> getAllQuiz()
```

**getQuizQuestionsById [GET]**
> /{id}
```java
public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@PathVariable Integer id)
```

**submitQuiz [POST]**
> /submit/{id}
```java
public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses)
```

## ***Done with Monolithic Services*** <u> [commit](https://github.com/AryanP45/MicroServicesDemo/commit/8efaf1b8faaf712becc41b4a27c56807785e055d)</u>

## ***Converted to Microservices*** <u> [commit](https://github.com/AryanP45/MicroServicesDemo/commit/8ecf66419513a1e7b4c52a5c08ab509fd37b88df)