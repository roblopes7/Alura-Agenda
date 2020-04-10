package com.alura.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {

    private Integer id = 0;
    private String nome;
    private String telefone;
    private String email;

    public Aluno() {
    }

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
        //return nome + ", " + telefone + " - " + email;
    }

    public void setId(int id) {
        this.id =id;
    }

    public Integer getId() {
        return id;
    }

    public boolean validarId() {
        if(id > 0 ) { return true; }
        else return false;

    }
}
