package com.algaworks.comercial;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.algaworks.comercial.model.Oportunidade;
import com.algaworks.comercial.repository.OportunidadeRepository;
import com.algaworks.comercial.services.OportunidadeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OportunidadeServiceTest {
	
	@Autowired
	private OportunidadeService oportunidadeService;
	
	@MockBean
	private OportunidadeRepository oportunidadeRepository;
	
	@Test
	public void getOportunidadesTest() {
		when(oportunidadeRepository.findAll()).thenReturn(Stream.of(
				new Oportunidade("Teste", "Teste", BigDecimal.ZERO)).collect(Collectors.toList()));
		assertEquals(1, oportunidadeService.findAll().size());
	}
	
	@Test
	public void saveOportunidadeTest() {
		Oportunidade oportunidade = new Oportunidade("Teste 123", "Teste 123", BigDecimal.TEN);
		when(oportunidadeRepository.save(oportunidade)).thenReturn(oportunidade);
		assertEquals(oportunidade, oportunidadeService.save(oportunidade));
	}
	
	@Test
	public void deleteOportunidadeTest() {
		Oportunidade oportunidade = new Oportunidade("Teste 123", "Teste 123", BigDecimal.TEN);
		oportunidadeService.delete(oportunidade.getId());
		verify(oportunidadeRepository, times(1)).deleteById(oportunidade.getId());
	}
}
