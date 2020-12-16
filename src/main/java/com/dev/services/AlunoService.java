package com.dev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.domain.Aluno;
import com.dev.domain.enums.Perfil;
import com.dev.dto.alunos.AlunoSaveDTO;
import com.dev.repository.AlunoRepository;
import com.dev.services.exceptions.ObjectNotFoundException;

@Service
public class AlunoService {
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private AlunoRepository repo;
	
	public Aluno find(Integer id) {
		Optional<Aluno> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Aluno.class.getName()));
	}
	
	public List<Aluno> findAll(){
		return repo.findAll();
	}
	
	public Aluno insert(Aluno obj) {
		obj.setId(null);
		obj.setPassword(pe.encode(obj.getPassword()));
		obj.addPerfil(Perfil.ALUNO);
		obj.setProjeto(null);
		obj.setPapel("");
		return repo.save(obj);
	}
	
	public Aluno update(Aluno obj) {
		Aluno newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}
	
	private void updateData(Aluno newObj, Aluno obj) {
		newObj.setMatricula(obj.getMatricula());
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setPassword(pe.encode(obj.getPassword()));
		newObj.setCurso(obj.getCurso());
		newObj.setPapel(obj.getPapel());
	}
	
	public Aluno fromDTO(AlunoSaveDTO objDTO) {
		return new Aluno(objDTO.getId(), objDTO.getMatricula() ,objDTO.getNome(), objDTO.getEmail(), 
						objDTO.getPassword(), Perfil.ALUNO, objDTO.getCurso(),  objDTO.getProjeto(), objDTO.getPapel());
	}
}