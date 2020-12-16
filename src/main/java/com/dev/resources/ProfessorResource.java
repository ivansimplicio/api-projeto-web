package com.dev.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.domain.Professor;
import com.dev.dto.professores.ProfessorSaveDTO;
import com.dev.dto.professores.ProfessorDTO;
import com.dev.services.ProfessorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "professores")
@CrossOrigin
@RestController
@RequestMapping(value = "/professores")
public class ProfessorResource {
	
	@Autowired
	public ProfessorService service;
	
	@ApiOperation(value = "Buscar todos os professores cadastrados")
	@GetMapping
	public ResponseEntity<List<ProfessorDTO>> findAll(){
		List<Professor> list = service.findAll();
		List<ProfessorDTO> listDTO = list.stream().map(obj -> new ProfessorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation(value = "Buscar um professor pelo seu identificador")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProfessorDTO> find(@PathVariable Integer id){
		Professor obj = service.find(id);
		ProfessorDTO objDTO = new ProfessorDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@ApiOperation(value = "Cadastrar um novo professor no sistema")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ProfessorSaveDTO objDTO){
		Professor obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Atualizar as informações de um professor específico")
	@PreAuthorize("hasAnyRole('PROFESSOR')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ProfessorSaveDTO objDTO, @PathVariable Integer id){
		Professor obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Remover o cadastro de um professor do sistema")
	@PreAuthorize("hasAnyRole('PROFESSOR')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}