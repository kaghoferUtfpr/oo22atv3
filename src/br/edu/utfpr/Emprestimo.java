package br.edu.utfpr;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Emprestimo {

    private List<Livro> listaLivros = new ArrayList<>();
    private Datas dataLocacao;
    private Datas dataDevolucao;
    private Status status;

    public Emprestimo(Livro livro, Datas dataLocacao, Datas dataDevolucao, Status status) {
        Livro l = new Livro();
        l = livro;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.status = status;
    }

    public Emprestimo() {
    }
    public void getDataLocacao() {
    }

    public void setDataLocacao(Datas dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Datas getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Datas dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate diaHoje() {
        LocalDate lc = LocalDate.now();
        return lc;
    }


    public long validarEntrega(Datas data){

        LocalDate d1 = LocalDate.of(data.getAno(), data.getMes(), data.getDia());
        LocalDate d2 = LocalDate.now();

        long days = ChronoUnit.DAYS.between(d1, d2);
        return days;
    }

    public Double validarMulta(long dias, Double multaDiaria,Double multaAcimaLimite, Double multaLimite)
    {
        Double valorMulta = dias * multaDiaria;
        Double diasComMultaPadrao, diasComMultaAux, totalMulta;

        int multaExtra = 0;

        if(dias > 7){
            if(valorMulta > multaLimite){
                diasComMultaAux = (valorMulta-multaLimite)/multaDiaria;
                diasComMultaPadrao = dias - diasComMultaAux;
                totalMulta = ((diasComMultaPadrao*multaDiaria) + (diasComMultaAux*multaAcimaLimite));
                return totalMulta;
            }
            else
            {
                return valorMulta;
            }
        }
        return 0.0;
    }

}
