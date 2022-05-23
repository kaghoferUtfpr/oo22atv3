package br.edu.utfpr;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Emprestimo {


    private int codigo;
    private Livro livro;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private Pessoa pessoa;

    private Status status;


    public Emprestimo(int codigo, Livro livro, LocalDate dataLocacao, Pessoa p) {
        this.codigo = codigo;
        this.livro = livro;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataLocacao.plusDays(7);
        this.pessoa = p;

    }

    public static void imprimirListaEmprestimos(List<Emprestimo> lista) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("COD LIVRO: " + lista.get(i).codigo);
            System.out.println("Nome = " + lista.get(i).pessoa.getNome());
            System.out.printf("Locação: %d/%d/%d\n", lista.get(i).dataLocacao.getDayOfMonth(), lista.get(i).dataLocacao.getMonthValue(), lista.get(i).dataLocacao.getYear());
            System.out.printf("Devolução: %d/%d/%d\n", lista.get(i).dataDevolucao.getDayOfMonth(), lista.get(i).dataDevolucao.getMonthValue(), lista.get(i).dataDevolucao.getYear());
            System.out.printf("Livro: %s De: %s\n", lista.get(i).livro.getTitulo(), lista.get(i).livro.getAutor());
            System.out.println("******************");
        }
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
