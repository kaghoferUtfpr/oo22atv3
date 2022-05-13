package br.edu.utfpr;

public class Livro {

    private final String autor;
    private final String titulo;
    private int qtdDisponivel;

    public Livro(String autor, String titulo){
        this.autor = autor;
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(int qtd)
    {
        this.qtdDisponivel = qtd;
    }



}
