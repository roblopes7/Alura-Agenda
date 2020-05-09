package com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alura.agenda.R;
import com.alura.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = criarView(parent);
        Aluno aluno = alunos.get(position);
        vincularInformacoes(view, aluno);
        return view;
    }

    private void vincularInformacoes(View view, Aluno aluno) {
        TextView nome = view.findViewById(R.id.item_aluno_nome);
        TextView telefone = view.findViewById(R.id.item_aluno_telefone);
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
    }

    private View criarView(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, parent, false);
    }

    public void atualizar(List<Aluno> alunos) {
        this.alunos.clear();
        this.alunos.addAll(alunos);
    }

    public void remove(Aluno alunoEscolhido) {
        alunos.remove(alunoEscolhido);
        notifyDataSetChanged();
    }
}
