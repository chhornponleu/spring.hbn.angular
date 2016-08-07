package com.ponleu.shopcommerce.test.context;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationTestInitializer.class })
public class AbstractContextTest {

	protected ObjectMapper objectMapper = new ObjectMapper();
	protected MockMvc mockMvc;

	@Inject
	protected WebApplicationContext context;

	public AbstractContextTest() {
	}

	protected <T> T readJson(String fileName, Class<T> target) {
		Resource resource = this.context.getResource("classpath:com/wingmoney/test/json/" + fileName);
		try {
			return this.objectMapper.readValue(resource.getFile(), target);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void printJson(Object obj) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Hibernate5Module hibernateModule = new Hibernate5Module();
			mapper.registerModule(hibernateModule);
			System.out.println("\n\n");
			System.out.println(mapper.writeValueAsString(obj));
			System.out.println("\n\n");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void before() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

}
