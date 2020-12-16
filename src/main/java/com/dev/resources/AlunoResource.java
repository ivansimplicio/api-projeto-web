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

import com.dev.domain.Aluno;
import com.dev.dto.alunos.AlunoSaveDTO;
import com.dev.dto.alunos.AlunoDTO;
import com.dev.services.AlunoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Aluno")
@CrossOrigin
@RestController
@RequestMapping(value = "/alunos")
public class AlunoResource {
	
	@Autowired
	public AlunoService service;
	
	@ApiOperation(value = "Buscar todos os alunos cadastrados")
	@GetMapping
	public ResponseEntity<List<AlunoDTO>> findAll(){
		List<Aluno> list = service.findAll();
		List<AlunoDTO> listDTO = list.stream().map(obj -> new AlunoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation(value = "Buscar um aluno pelo seu identificador")
	@GetMapping(value = "/{id}")
	public ResponseEntity<AlunoDTO> find(@PathVariable Integer id){
		Aluno obj = service.find(id);
		AlunoDTO objDTO = new AlunoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	@ApiOperation(value = "Cadastrar um novo aluno no sistema")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody AlunoSaveDTO objDTO){
		Aluno obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Atualiza as informações de um aluno específico")
	@PreAuthorize("hasAnyRole('ALUNO')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody AlunoSaveDTO objDTO, @PathVariable Integer id){
		Aluno obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Remove o cadastro de um aluno do sistema")
	@PreAuthorize("hasAnyRole('ALUNO')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}