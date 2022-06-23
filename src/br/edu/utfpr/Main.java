package br.edu.utfpr;/*

Criar programa orientado à objetos para solucionar os seguintes casos de uso:
- Será o controle de uma biblioteca
- Desenvolver método para a locação de livro
- deverá validar se livro está disponível
- Método para reserva de livro
- Validá se há alguma reserva para data e se estará disponível
- Método para devolução do livro
- Válida se a data da entrega é anterior a data máxima
- caso tenha vencido calcular 50 centavos ao dia até 20 reais, depois 1 real por dia
 */

import br.edu.utfpr.CRUDservice.EmprestimoService;
import br.edu.utfpr.CRUDservice.LivroService;
import br.edu.utfpr.CRUDservice.ReservaService;
import br.edu.utfpr.config.BancoDados;
import br.edu.utfpr.entity.Emprestimo;
import br.edu.utfpr.entity.Livro;
import br.edu.utfpr.entity.Pessoa;
import br.edu.utfpr.entity.Reserva;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        Long codLivro = 1L;
        Long codEmprestimo = 1L;
        Long codReserva = 1L;

        EmprestimoService emprestimoService = new EmprestimoService();
        LivroService livroService = new LivroService();
        ReservaService reservaService = new ReservaService();

        Livro l1 = new Livro("Erik Brynjolfsson e Andrew Mcafee", "A Segunda Era das Máquinas", 2, codLivro);
        codLivro++;
        Livro l2 = new Livro("Susan Cain", "O Poder dos Quietos", 2, codLivro);
        codLivro++;
        Livro l3 = new Livro("Paul Barry e David Griffiths", "Use a cabeça! Programação", 2, codLivro);
        codLivro++;
        Livro l4 = new Livro("Klaus Schwab", "A Quarta Revolução Industrial", 2, codLivro);
        codLivro++;
        Livro l5 = new Livro("Ernesto Mario Haberkorn", "Um Bate-papo sobre T.I", 2, codLivro);
        codLivro++;
        Livro l6 = new Livro("Ernesto Mario Haberkorn", "Teste", 1, codLivro);
        codLivro++;

        BancoDados.bancoLivros.add(l1);
        BancoDados.bancoLivros.add(l2);
        BancoDados.bancoLivros.add(l3);
        BancoDados.bancoLivros.add(l4);
        BancoDados.bancoLivros.add(l5);
        BancoDados.bancoLivros.add(l6);

        LocalDate d1 = LocalDate.of(2022, 5, 27);
        //LocalDate between = LocalDate.of(2022, 5, 10);
        LocalDate d2 = LocalDate.of(2022, 5, 25);

        Pessoa p1 = new Pessoa("Daniel");

        Emprestimo emp1 = new Emprestimo(codEmprestimo, l1, d1, p1);
        codEmprestimo++;
        emprestimoService.decrementarQtd(1L);
        Emprestimo emp2 = new Emprestimo(codEmprestimo, l1, d2, p1);
        codEmprestimo++;
        emprestimoService.decrementarQtd(1L);

        BancoDados.bancoEmprestimos.add(emp1);
        BancoDados.bancoEmprestimos.add(emp2);

        Reserva res2 = new Reserva(l1, d2, p1, codReserva);
        codReserva++;

        BancoDados.bancoReservas.add(res2);


        int opcao;
        do {
            reservaService.apagarReservasAntigas();
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Deletar Livro");
            System.out.println("3 - Reservar Livro");
            System.out.println("4 - Emprestimo Livro");
            System.out.println("5 - Devolver Livro");
            System.out.println("6 - Listar Acervo");
            System.out.println("7 - Listar Empréstimos");
            System.out.println("8 - Listar Reservas");
            System.out.println("9 - Testar Funcoes:");
            System.out.println("0 - SAIR");
            System.out.println("Insira uma opção: ");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("1=Cadastro de Livro: ");
                    livroService.salvar(livroService.lerDadosLivro(codLivro));
                    codLivro++;
                    break;
                case 2:
                    System.out.println("2=Deletar Livro: ");
                    System.out.println("Insira o Código: ");
                    livroService.remover(sc.nextLong());
                    break;
                case 3:
                    reservaService.cadastrarReserva(1L);
                    codReserva++;
                    break;
                case 4:
                    System.out.println("Insira o Cod. do Livro ");
                    Long cdLivro = sc.nextLong();
                    sc.nextLine();
                    emprestimoService.cadastrarEmprestimo(cdLivro, codEmprestimo);
                    codEmprestimo++;
                    break;
                case 5:
                    System.out.println("Devolver Livro: ");
                    System.out.println("Insira o Cod. do Empréstimo: ");
                    emprestimoService.devolverEmprestimo(sc.nextLong());
                    break;
                case 6:
                    System.out.println("Listar Acervo de Livros: ");
                    livroService.listarAcervo(BancoDados.bancoLivros);
                    break;
                case 7:
                    System.out.println("Listar Emprestimos: ");
                    emprestimoService.imprimirListaEmprestimos();
                    break;
                case 8:
                    System.out.println("Lista de Reservas: ");
                    reservaService.listarReservas();
                    break;
                case 9:
                    //System.out.println(acervo.disponibilidadadeStatutus("joao", 1));
                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        } while (opcao != 0);
    }
}
