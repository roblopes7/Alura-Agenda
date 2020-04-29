package com.alura.agenda.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alura.agenda.R;
import com.alura.agenda.dao.AlunoDAO;
import com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity implements ConstantActivities {

    public static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
    public static final String TITULO_APPBAR_EDITA_ALUNO = "Editar Aluno";
    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    private EditText campoId;
    final AlunoDAO dao = new AlunoDAO();
    Aluno aluno = new Aluno();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        inicializarCampos();
        carregarInformation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void carregarInformation() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencherCampos(aluno);
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }

    }

    private void preencherCampos(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEmail.setText(aluno.getEmail());
        campoTelefone.setText(aluno.getTelefone());
        campoId.setText(String.valueOf(aluno.getId()));
    }

    private void finalizarFormulario() {
        informarAluno();
        if (aluno.validarId()) {
            dao.editar(aluno);
        } else {
            dao.salvar(aluno);
        }
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        int i = R.menu.activity_formulario_aluno_menu;
        if(itemId == R.id.activity_formulario_aluno_salvar){
            finalizarFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializarCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
        campoId = findViewById(R.id.activity_formulario_aluno_id);
    }

    private void salvar() {
        dao.salvar(aluno);
        finish();
    }

    private void informarAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();
        Integer id = campoId.getText().toString().isEmpty() ? 0 : Integer.parseInt(campoId.getText().toString());

        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
        aluno.setId(id);

        return;
    }
}
