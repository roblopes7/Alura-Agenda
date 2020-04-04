package com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alura.agenda.R;
import com.alura.agenda.dao.AlunoDAO;
import com.alura.agenda.model.Aluno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";
    private FloatingActionButton fg;
    AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        fg = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        ConfigurarFgAddAluno();
        setTitle(TITULO_APPBAR);
    }

    private void ConfigurarFgAddAluno() {
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
        configurarLista();
    }

    private void configurarLista() {
        ListView listView = findViewById(R.id.activity_lista_alunos_listview);
        listView.setAdapter(new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, dao.getTodosAlunos()));
    }
}
