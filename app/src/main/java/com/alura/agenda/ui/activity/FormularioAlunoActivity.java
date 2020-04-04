package com.alura.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alura.agenda.R;
import com.alura.agenda.dao.AlunoDAO;
import com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        setTitle(TITULO_APPBAR);
        inicializarCampos();
        Button botao = findViewById(R.id.activity_formulario_aluno_botao);
        configurarBtnSalvar(botao);
    }

    private void configurarBtnSalvar(Button botao) {
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Aluno aluno = CriarAluno();
                salvar(aluno);
            }
        });
    }

    private void inicializarCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salvar(Aluno aluno) {
        dao.salvar(aluno);
        finish();
    }

    private Aluno CriarAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}
