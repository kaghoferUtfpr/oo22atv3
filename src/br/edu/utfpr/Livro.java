package br.edu.utfpr;

public class Livro {

    private String autor;
    private String titulo;
    private int qtdDisponivel;
    private int codigo;

    public Livro(Livro livro) {
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Livro(){
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

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public int getCodigo() {
        return codigo;
    }
}
