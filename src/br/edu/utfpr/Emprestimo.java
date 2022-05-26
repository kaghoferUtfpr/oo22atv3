package br.edu.utfpr;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {


    private int codigo;
    private Livro livro;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private Pessoa pessoa;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Emprestimo() {
    }

    public Emprestimo(int codigo, Livro livro, LocalDate dataLocacao, Pessoa p) {
        this.codigo = codigo;
        this.livro = livro;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataLocacao.plusDays(7);
        this.pessoa = p;
    }


}
