package com.alura.agenda.dao;

import com.alura.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> getTodosAlunos() {
        return Collections.unmodifiableList(alunos);
    }
}
