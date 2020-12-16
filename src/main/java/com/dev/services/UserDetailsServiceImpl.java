package com.dev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.domain.Aluno;
import com.dev.domain.Professor;
import com.dev.repository.AlunoRepository;
import com.dev.repository.ProfessorRepository;
import com.dev.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private AlunoRepository alunoRepo;
	
	@Autowired
	private ProfessorRepository profRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Professor prof = profRepo.findByEmail(email);
		Aluno aluno = alunoRepo.findByEmail(email);
		
		UserSS userSS = null;
		
		if (prof != null && prof.getEmail().equals(email)) {
			userSS = new UserSS(prof.getId(), prof.getEmail(), prof.getPassword(), prof.getPerfis());
		}
		
		if (aluno != null && aluno.getEmail().equals(email)) {
			userSS = new UserSS(aluno.getId(), aluno.getEmail(), aluno.getPassword(), aluno.getPerfis());
		}
		
		if(prof == null && aluno == null) {
			throw new UsernameNotFoundException(email);
		}
		return userSS;
	}
}