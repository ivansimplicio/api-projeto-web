package com.dev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dev.domain.Aluno;
import com.dev.domain.Projeto;
import com.dev.domain.enums.Perfil;
import com.dev.dto.alunos.AlunoAuxDTO;
import com.dev.dto.projetos.ProjetoSaveDTO;
import com.dev.repository.AlunoRepository;
import com.dev.repository.ProjetoRepository;
import com.dev.security.UserSS;
import com.dev.services.exceptions.AuthorizationException;
import com.dev.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjetoService {

    @Autowired
	private ProjetoRepository repo;
    
    @Autowired
    private ProfessorService professorService;
    
    @Autowired
    private AlunoService alunoService;
    
    @Autowired
    private AlunoRepository alunoRepository;
	
	public Projeto find(Integer id) {
		Optional<Projeto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+", Tipo: "+Projeto.class.getName()));
	}
	
	public List<Projeto> findAll(){
		return repo.findAll();
	}
	
	public Projeto insert(Projeto obj) {
		UserSS user = UserService.authenticated();

		if(user == null || !user.hasRole(Perfil.PROFESSOR)) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		obj.setId(null);
		obj.setCoordenador(professorService.find(user.getId()));
		return repo.save(obj);
	}
	
	public Projeto update(Projeto obj) {
		Projeto newObj = find(obj.getId());
		
		UserSS user = UserService.authenticated();

		if(user == null || !newObj.getCoordenador().getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		
		Projeto obj = find(id);
		UserSS user = UserService.authenticated();

		if(user == null || !obj.getCoordenador().getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		obj.getAlunos().forEach(aluno -> removeAluno(aluno.getId()));
		repo.deleteById(id);
	}
	
	public void addAluno(Projeto projeto, AlunoAuxDTO obj) {
		
		UserSS user = UserService.authenticated();

		if(user == null || !projeto.getCoordenador().getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		Aluno aluno = alunoService.find(obj.getId());
		projeto.addAluno(aluno);
		aluno.setProjeto(projeto);
		aluno.setPapel(obj.getPapel());
		alunoRepository.save(aluno);
	}
	
	public void removeAluno(Projeto projeto, AlunoAuxDTO obj) {
		
		UserSS user = UserService.authenticated();

		if(user == null || !projeto.getCoordenador().getId().equals(user.getId())) {
			throw new AuthorizationException("Acesso negado!");
		}
		
		removeAluno(obj.getId());
	}
	
	private void removeAluno(Integer id) {
		Aluno aluno = alunoService.find(id);
		aluno.setProjeto(null);
		aluno.setPapel("");
		alunoRepository.save(aluno);
	}
	
	private void updateData(Projeto newObj, Projeto obj) {
        newObj.setNome(obj.getNome());
        newObj.setDescricao(obj.getDescricao());
	}
	
	public Projeto fromDTO(ProjetoSaveDTO objDTO) {
		return new Projeto(objDTO.getId(), objDTO.getNome(), objDTO.getDescricao(), null, new ArrayList<>());
	}
}