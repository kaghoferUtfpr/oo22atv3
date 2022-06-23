package br.edu.utfpr.config;

import br.edu.utfpr.entity.Emprestimo;
import br.edu.utfpr.entity.Livro;
import br.edu.utfpr.entity.Pessoa;
import br.edu.utfpr.entity.Reserva;

import java.util.ArrayList;
import java.util.List;

public class BancoDados {
    public static List<Livro> bancoLivros = new ArrayList<>();
    public static List<Reserva> bancoReservas = new ArrayList<>();
    public static List<Pessoa> bancoPessoas = new ArrayList<>();
    public static List<Emprestimo> bancoEmprestimos = new ArrayList<>();
}
