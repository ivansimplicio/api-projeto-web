package com.dev;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.dev.repository.AlunoRepository;
import com.dev.services.AlunoService;

class AlunoServiceTest {

	@Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    void test() {
    	
    }
}