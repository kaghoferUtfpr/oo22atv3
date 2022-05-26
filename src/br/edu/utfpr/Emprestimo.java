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



    public long validarEntrega(Datas data) {

        LocalDate d1 = LocalDate.of(data.getAno(), data.getMes(), data.getDia());
        LocalDate d2 = LocalDate.now();

        long days = ChronoUnit.DAYS.between(d1, d2);
        return days;
    }

    public Double validarMulta(long dias, Double multaDiaria, Double multaAcimaLimite, Double multaLimite) {
        Double valorMulta = dias * multaDiaria;
        Double diasComMultaPadrao, diasComMultaAux, totalMulta;

        int multaExtra = 0;

        if (dias > 7) {
            if (valorMulta > multaLimite) {
                diasComMultaAux = (valorMulta - multaLimite) / multaDiaria;
                diasComMultaPadrao = dias - diasComMultaAux;
                totalMulta = ((diasComMultaPadrao * multaDiaria) + (diasComMultaAux * multaAcimaLimite));
                return totalMulta;
            } else {
                return valorMulta;
                //teste
            }
        }
        return 0.0;
    }

}
