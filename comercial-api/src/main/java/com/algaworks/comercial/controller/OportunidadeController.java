package com.algaworks.comercial.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.comercial.dto.OportunidadeDTO;
import com.algaworks.comercial.model.Oportunidade;
import com.algaworks.comercial.services.OportunidadeService;

@CrossOrigin
@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {
	
	@Autowired
	private OportunidadeService oportunidadeService;

	@GetMapping
	public ResponseEntity<List<Oportunidade>>list() {
		List<Oportunidade> oportunidades = oportunidadeService.findAll();
		
		return ResponseEntity.ok(oportunidades);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> get(@PathVariable Long id) {
		Oportunidade oportunidade = oportunidadeService.get(id);
		
		if(oportunidade == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(oportunidade);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Oportunidade> edit(@PathVariable Long id, @RequestBody OportunidadeDTO dto) {	
		Oportunidade oportunidade = oportunidadeService.edit(id, dto);
		
		if(oportunidade == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(oportunidade);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Oportunidade> delete(@PathVariable Long id){
		oportunidadeService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade save(@Valid @RequestBody Oportunidade oportunidade) {
		return oportunidadeService.save(oportunidade);
	}

}
