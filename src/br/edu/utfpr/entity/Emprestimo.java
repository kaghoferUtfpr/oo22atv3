package br.edu.utfpr.entity;

import br.edu.utfpr.entity.Livro;
import br.edu.utfpr.entity.Pessoa;

import java.time.LocalDate;

public class Emprestimo extends Entity{


    private Long codigo;
    private Livro livro;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private Pessoa pessoa;

    private boolean status;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Emprestimo(Long codigo, Livro livro, LocalDate dataLocacao, Pessoa p) {
        this.codigo = codigo;
        this.livro = livro;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataLocacao.plusDays(15);
        this.pessoa = p;
        this.status = true;
    }


}
