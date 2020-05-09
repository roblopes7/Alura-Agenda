package com.alura.agenda;

import android.app.Application;

import com.alura.agenda.dao.AlunoDAO;
import com.alura.agenda.model.Aluno;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salvar(new Aluno("Robson", "996035336", "rob_lopes7@hotmail.com"));
        dao.salvar(new Aluno("Lalaland", "3532-2305", "Teste2@hotmail.com"));
    }
}
