package com.alura.agenda.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;


import com.alura.agenda.dao.AlunoDAO;
import com.alura.agenda.model.Aluno;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.dao = new AlunoDAO();
        this.adapter = new ListaAlunosAdapter(this.context);
    }

    public void confirmaRemocao(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Removendo Aluno")
                .setMessage("Tem certeza que deseja remover o aluno?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
                        removerAluno(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void removerAluno(Aluno alunoEscolhido) {
        dao.remover(alunoEscolhido);
        adapter.remove(alunoEscolhido);
        atualizarAlunos();
    }

    public void atualizarAlunos() {
        adapter.atualizar(dao.todos());
    }

    public void configurarAdapter(@NonNull ListView listView) {
        listView.setAdapter(adapter);
    }
}
