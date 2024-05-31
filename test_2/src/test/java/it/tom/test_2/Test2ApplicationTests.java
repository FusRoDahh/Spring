package it.tom.test_2;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.tom.test_2.Repositories.UserRepo;
import it.tom.test_2.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class Test2ApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private UserRepo userRepository;
	@Autowired
	private ObjectMapper objectMapper;


	@Test
	public void getAllUsersTest() throws Exception {
		User user1 = new User("John", "Doe", 1L);
		User user2 = new User("Jane", "Doe", 2L);
		userRepository.saveAll(Arrays.asList(user1, user2));

		MvcResult result = this.mockMvc.perform(get("/users/list")).andDo(print()).andExpect(status().isOk()).andReturn();

		List<User> studentFromResponse = objectMapper.readValue(result.getResponse().getContentAsString(), List.class);
		System.out.println("Students in database are: " + studentFromResponse.size());
		assertThat(studentFromResponse.size()).isNotZero();
	}

	@Test
	public void getUserByIdTest() throws Exception {
		User user = new User("John", "Doe", 1L);
		user = userRepository.save(user);

		MvcResult result = mockMvc.perform(get("/users/" + user.getId()))
				.andExpect(status().isOk())
				.andReturn();

		User savedUser = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
		assertThat(savedUser.getName()).isEqualTo(user.getName());
		assertThat(savedUser.getSurname()).isEqualTo(user.getSurname());
	}

	@Test
	public void createUserTest() throws Exception {
		User newUser = new User();
		newUser.setName("John");
		newUser.setSurname("Doe");

		String json = objectMapper.writeValueAsString(newUser);

		MvcResult result = mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isOk())
				.andReturn();

		User savedUser = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);
		assertThat(savedUser.getName()).isEqualTo(newUser.getName());
		assertThat(savedUser.getSurname()).isEqualTo(newUser.getSurname());
	}

	@Test
	public void updateUserTest() throws Exception {
		User user = new User("John", "Doe", 1L);
		user = userRepository.save(user);

		user.setName("Jane");
		user.setSurname("Doe");

		String json = objectMapper.writeValueAsString(user);

		mockMvc.perform(put("/users/" + user.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().isOk())
				.andReturn();

		User updatedUser = userRepository.findById(user.getId()).get();
		assertThat(updatedUser.getName()).isEqualTo(user.getName());
		assertThat(updatedUser.getSurname()).isEqualTo(user.getSurname());
	}

	@Test
	public void deleteUserTest() throws Exception {
		User user = new User("John", "Doe", 1L);
		user = userRepository.save(user);

		mockMvc.perform(delete("/users/" + user.getId()))
				.andExpect(status().isOk())
				.andReturn();

		assertThat(userRepository.findById(user.getId())).isEmpty();
	}
}
