package com.dev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.domain.Professor;
import com.dev.domain.enums.Perfil;
import com.dev.dto.professores.ProfessorSaveDTO;
import com.dev.repository.ProfessorRepository;
import com.dev.security.UserSS;
import com.dev.services.exceptions.AuthorizationException;
import com.dev.services.exceptions.ObjectNotFoundException;

@Service
public class ProfessorService{
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private ProfessorRepository repo;
	
	public Professor find(Integer id) {
		Optional<Professor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Professor.class.getName()));
	}
	
	public List<Professor> findAll(){
		return repo.findAll();
	}
	
	public Professor insert(Professor obj) {
		obj.setId(null);
		obj.setPassword(pe.encode(obj.getPassword()));
		obj.addPerfil(Perfil.PROFESSOR);
		obj.setProjetos(null);
		return repo.save(obj);
	}
	
	public Professor update(Professor obj) {
		UserSS user = UserService.authenticated();

		if(user == null || !obj.getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		Professor newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		UserSS user = UserService.authenticated();

		if(user == null || !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		find(id);
		repo.deleteById(id);
	}
	
	private void updateData(Professor newObj, Professor obj) {
		newObj.setNome(obj.getNome());
		newObj.setMatricula(obj.getMatricula());
		newObj.setEmail(obj.getEmail());
		newObj.setPassword(pe.encode(obj.getPassword()));
		newObj.setAtuacao(obj.getAtuacao());
		newObj.setFormacao(obj.getFormacao());
		newObj.setProjetos(obj.getProjetos());

	}
	
	public Professor fromDTO(ProfessorSaveDTO objDTO) {
		return new Professor(objDTO.getId(), objDTO.getMatricula() ,objDTO.getNome(), objDTO.getEmail(), 
							objDTO.getPassword(), Perfil.PROFESSOR, objDTO.getAtuacao(),  objDTO.getFormacao(), new ArrayList<>());
	}
}