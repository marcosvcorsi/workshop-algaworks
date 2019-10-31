package com.algaworks.comercial;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.algaworks.comercial.model.Oportunidade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OportunidadeControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	ObjectMapper om = new ObjectMapper();
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void saveOportunidadeTest() throws Exception {
		Oportunidade oportunidade = new Oportunidade();
		oportunidade.setDescricao("Teste MVC");
		oportunidade.setNomeProspecto("Teste MVC");
		oportunidade.setValor(BigDecimal.TEN);
		
		String jsonRequest = om.writeValueAsString(oportunidade);
		
		MvcResult result = mockMvc.perform(post("/oportunidades")
				.content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated()).andReturn();
		
		String resultContent = result.getResponse().getContentAsString();
		
		Oportunidade op = om.readValue(resultContent, Oportunidade.class);
		
		assertNotNull(op);
	}
	
	@Test
	public void findAllOportunidadeTest() throws Exception {

		MvcResult result = mockMvc.perform(get("/oportunidades").content(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
		
		String resultContent = result.getResponse().getContentAsString();
		
		List<Oportunidade> list = om.readValue(resultContent, new TypeReference<List<Oportunidade>>() {});
		
		assertNotNull(list);
	}
}
