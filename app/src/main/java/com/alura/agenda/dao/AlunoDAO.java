package com.alura.agenda.dao;

import com.alura.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int id = 1;

    public void salvar(Aluno aluno) {
        aluno.setId(id);
        alunos.add(aluno);
        atualizarId();
    }

    private void atualizarId() {
        id++;
    }

    public void editar(Aluno aluno) {
        Aluno alunoEncontrado = buscarAlunoPeloId(aluno.getId());
        if(alunoEncontrado != null) {
            int position = alunos.indexOf(alunoEncontrado);
            alunos.set(position, aluno);
        }
    }

    private Aluno buscarAlunoPeloId(Integer id) {
        for (Aluno a: alunos) {
            Integer i = a.getId();
            if(i == id) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> getTodosAlunos() {
        return alunos;
    }

    public void remover(Aluno aluno) {
        Aluno alunoRemovido = buscarAlunoPeloId(aluno.getId());
        if(alunoRemovido != null) {
            alunos.remove(alunoRemovido);
        }
    }
}

