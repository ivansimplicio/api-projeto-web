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

import com.dev.domain.Projeto;
import com.dev.dto.projetos.ProjetoSaveDTO;
import com.dev.dto.alunos.AlunoAuxDTO;
import com.dev.dto.projetos.ProjetoDTO;
import com.dev.services.ProjetoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "projetos")
@CrossOrigin
@RestController
@RequestMapping(value = "/projetos")
public class ProjetoResource {
	
	@Autowired
	public ProjetoService service;
	
	@ApiOperation(value = "Buscar todos os Projetoes cadastrados")
	@GetMapping
	public ResponseEntity<List<ProjetoDTO>> findAll(){
		List<Projeto> list = service.findAll();
		List<ProjetoDTO> listDTO = list.stream().map(obj -> new ProjetoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@ApiOperation(value = "Buscar um Projeto pelo seu identificador")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProjetoDTO> find(@PathVariable Integer id){
		Projeto obj = service.find(id);
		ProjetoDTO objDTO = new ProjetoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
    }
	
	@ApiOperation(value = "Cadastrar um novo Projeto no sistema")
	@PreAuthorize("hasAnyRole('PROFESSOR')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ProjetoSaveDTO objDTO){
		Projeto obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Atualizar as informações de um Projeto específico")
	@PreAuthorize("hasAnyRole('PROFESSOR')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ProjetoSaveDTO objDTO, @PathVariable Integer id){
		Projeto obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Remover o cadastro de um Projeto do sistema")
	@PreAuthorize("hasAnyRole('PROFESSOR')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Adicionar um novo aluno a um projeto específico")
	@PutMapping(value = "/{id}/add_aluno")
	public ResponseEntity<Void> addAluno(@RequestBody AlunoAuxDTO obj, @PathVariable Integer id){
		Projeto projeto = service.find(id);
		service.addAluno(projeto, obj);
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Remover um aluno a um projeto específico")
	@PutMapping(value = "/{id}/remove_aluno")
	public ResponseEntity<Void> removeAluno(@RequestBody AlunoAuxDTO obj, @PathVariable Integer id){
		Projeto projeto = service.find(id);
		service.removeAluno(projeto, obj);
		
		return ResponseEntity.noContent().build();
	}
	
}