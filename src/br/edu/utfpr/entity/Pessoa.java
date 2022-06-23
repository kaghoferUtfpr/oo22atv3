package br.edu.utfpr.entity;

public class Pessoa extends Entity{
    private String nome;

    public Pessoa(String nome)
    {
        this.nome = nome;
    }

    public String getNome()
    {
        return this.nome;
    }

}
