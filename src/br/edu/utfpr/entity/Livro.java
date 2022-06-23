package br.edu.utfpr.entity;

public class Livro extends Entity{
    private String autor;
    private String titulo;
    private int qtdDisponivel;

    private int estoque;
    private Long codigo;

    public Livro(String autor, String titulo, int qtdDisponivel, Long codigo) {
        this.autor = autor;
        this.titulo = titulo;
        this.qtdDisponivel = qtdDisponivel;
        this.codigo = codigo;
        this.estoque = qtdDisponivel;
    }

    public int getEstoque() {
        return estoque;
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

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public Long getCodigo() {
        return codigo;
    }

}
