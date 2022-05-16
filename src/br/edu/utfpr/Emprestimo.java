package br.edu.utfpr;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Emprestimo {


    private int codigo;
    private Livro livro;
    private Datas dataLocacao;
    private Datas dataDevolucao;
    private Status status;

    public Emprestimo(int codigo, Livro livro, Datas dataLocacao, Datas dataDevolucao) {
        this.codigo = codigo;
        this.livro = livro;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
    }

    public static void imprimirListaEmprestimos(List<Emprestimo> lista)
    {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).codigo);
            System.out.println(lista.get(i).dataDevolucao.getDia());
            System.out.println(lista.get(i).dataLocacao.getDia());
            System.out.println(lista.get(i).livro.getAutor());
        }
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
