package it.tom.test_1;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.tom.test_1.Controllers.StudentController;
import it.tom.test_1.Entities.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class StudentControllerTests {

	@Autowired
	private StudentController studentController;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	Student createStudent() throws Exception {
		Student student = new Student();
		student.setName("name");
		student.setSurname("surname");
		student.setIsWorking(true);

		String json = objectMapper.writeValueAsString(student);

		MvcResult result = this.mockMvc.perform(post("/student").contentType(MediaType.APPLICATION_JSON)
				.content(json)).andDo(print()).andExpect(status().isOk()).andReturn();

		return objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
	}

	@Test
	void userControllerLoads() {
		assertThat(studentController).isNotNull();
	}

	@Test
	void createStudentTest() throws Exception {
		Student student = new Student();
		student.setName("name");
		student.setSurname("surname");
		student.setIsWorking(true);

		String json = objectMapper.writeValueAsString(student);

		MvcResult result = this.mockMvc.perform(post("/student").contentType(MediaType.APPLICATION_JSON)
				.content(json)).andDo(print()).andExpect(status().isOk()).andReturn();

		Student createdStudent = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		assertThat(createdStudent.getId()).isNotNull();
		assertThat(createdStudent.getName()).isEqualTo(student.getName());
		assertThat(createdStudent.getSurname()).isEqualTo(student.getSurname());
	}

	@Test
	void getAStudentTest() throws Exception {
		Student student =createStudent();
		assertThat(student).isNotNull();

		MvcResult result = this.mockMvc.perform(get("/student/" + student.getId())).andDo(print()).andExpect(status().isOk()).andReturn();
		Student createdStudent = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		assertThat(createdStudent.getId()).isNotNull();
		assertThat(createdStudent.getName()).isEqualTo(student.getName());
		assertThat(createdStudent.getSurname()).isEqualTo(student.getSurname());
	}

	@Test
	void getAllStudentsTest() throws Exception {
		createStudent();
		createStudent();
		createStudent();

		MvcResult result = this.mockMvc.perform(get("/student/list")).andDo(print()).andExpect(status().isOk()).andReturn();

		List<Student> studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
		System.out.println("Students in database are: " + studentFromResponse.size());
		assertThat(studentFromResponse.size()).isNotZero();
	}

	@Test
	void updateStudentTest() throws Exception {
		Student student = createStudent();
		student.setName("newName");
		student.setSurname("newSurname");
		student.setIsWorking(false);
		String json = objectMapper.writeValueAsString(student);
		MvcResult result = this.mockMvc.perform(put("/student/" + student.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andDo(print()).andExpect(status().isOk()).andReturn();
		Student createdStudent = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		assertThat(createdStudent.getName()).isEqualTo(student.getName());
		assertThat(createdStudent.getSurname()).isEqualTo(student.getSurname());
		assertThat(createdStudent.getIsWorking()).isEqualTo(student.getIsWorking());
	}

	@Test
	void updateIsWorkingTest() throws Exception {
		Student student = createStudent();
		student.setIsWorking(false);
		String json = objectMapper.writeValueAsString(student.getIsWorking());
		MvcResult result = this.mockMvc.perform(patch("/student/isWorking/" + student.getId()).contentType(MediaType.APPLICATION_JSON)
				.content(json)).andDo(print()).andExpect(status().isOk()).andReturn();
		Student createdStudent = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		assertThat(createdStudent.getIsWorking()).isEqualTo(false);
	}

	@Test
	void deleteStudentTest() throws Exception {
		Student student = new Student();
		student.setName("name");
		student.setSurname("surname");
		student.setIsWorking(true);

		String json = objectMapper.writeValueAsString(student);

		MvcResult result = this.mockMvc.perform(post("/student").contentType(MediaType.APPLICATION_JSON)
				.content(json)).andDo(print()).andExpect(status().isOk()).andReturn();

		Student createdStudent = objectMapper.readValue(result.getResponse().getContentAsString(), Student.class);
		this.mockMvc.perform(delete("/student/" + createdStudent.getId())).andDo(print()).andExpect(status().isOk()).andReturn();
	}

}
