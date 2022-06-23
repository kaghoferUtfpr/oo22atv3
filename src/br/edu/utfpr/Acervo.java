package br.edu.utfpr;

import br.edu.utfpr.CRUDservice.EmprestimoService;
import br.edu.utfpr.config.BancoDados;
import br.edu.utfpr.entity.Emprestimo;
import br.edu.utfpr.entity.Livro;
import br.edu.utfpr.entity.Pessoa;
import br.edu.utfpr.entity.Reserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Acervo {

    public boolean dataEntrePeriodo(LocalDate d1, LocalDate d2, LocalDate dataReserva) {
        if (dataReserva.isAfter(d1) && dataReserva.isBefore(d2)) {
            System.out.println("está_entre");
            return true;
        }
        return false;
    }

    public void diaParaReservar(int codLivro) {
        long result = BancoDados.bancoEmprestimos.stream().filter(e -> e.getLivro().getCodigo() == codLivro).count();
        System.out.println("Qdt: " + result);
    }

    public long qtdDiasEmprestimo(LocalDate data) {
        LocalDate d1 = LocalDate.of(data.getYear(), data.getMonthValue(), data.getDayOfMonth());
        LocalDate d2 = LocalDate.now();
        long days = ChronoUnit.DAYS.between(d1, d2);
        return days;
    }



    public boolean checarReserva(String nome, int codLivro) {
        Reserva r = BancoDados.bancoReservas.stream().filter(f -> f.getLivro().getCodigo() == codLivro && f.getPessoa().getNome().equals(nome)).findFirst().get();
        if (r != null) {
            return true;
        }
        return false;
    }

    public void testando() {
        BancoDados.bancoLivros.stream().map(livro -> livro.getTitulo()).filter(aut -> aut.startsWith("A")).forEach(System.out::println);
    }

}
