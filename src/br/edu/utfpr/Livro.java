package br.edu.utfpr;

public class Livro {

    private final String autor;
    private final String titulo;
    private int qtdDisponivel;

    private int codigo;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Livro(String autor, String titulo){
        this.autor = autor;
        this.titulo = titulo;
    }

    public Livro(String autor, String titulo, int qtdDisponivel, int codigo) {
        this.autor = autor;
        this.titulo = titulo;
        this.qtdDisponivel = qtdDisponivel;
        this.codigo = codigo;
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
