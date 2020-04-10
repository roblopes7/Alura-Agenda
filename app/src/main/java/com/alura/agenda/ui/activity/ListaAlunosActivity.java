package com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.agenda.R;
import com.alura.agenda.dao.AlunoDAO;
import com.alura.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity implements ConstantActivities{

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private FloatingActionButton fg;
    private ListView listView;
    AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        dao.salvar(new Aluno("Robson", "996035336", "rob_lopes7@hotmail.com"));
        dao.salvar(new Aluno("Lalaland", "3532-2305", "Teste2@hotmail.com"));

        fg = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        configurarFgAddAluno();
        configurarLista();
        setTitle(TITULO_APPBAR);
    }

    private void configurarFgAddAluno() {
        fg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        adapter.addAll(dao.getTodosAlunos());
    }

    private void configurarLista() {
        configurarAdapter();
        listView = findViewById(R.id.activity_lista_alunos_listview);
        listView.setAdapter(adapter);
        selecionarAluno();
        removerAluno();
    }

    private void configurarAdapter() {
        adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1);
    }

    private void removerAluno() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) parent.getItemAtPosition(position);
                dao.remover(alunoEscolhido);
                adapter.remove(alunoEscolhido);
                return true;
            }
        });
    }

    private void selecionarAluno() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEncontrado = (Aluno) parent.getItemAtPosition(position);
                AbrirFormularioModoEditaAluno(alunoEncontrado);
            }
        });
    }

    private void AbrirFormularioModoEditaAluno(Aluno aluno) {
        Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        intent.putExtra(CHAVE_ALUNO, aluno);
        startActivity(intent);
    }
}
