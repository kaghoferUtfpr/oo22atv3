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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);
        int codLivro = 1;
        int codEmprestimo = 1;

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

        List<Livro> listaLivros = new ArrayList<>();

        Bancos.bancoLivros.add(l1);
        Bancos.bancoLivros.add(l2);
        Bancos.bancoLivros.add(l3);
        Bancos.bancoLivros.add(l4);
        Bancos.bancoLivros.add(l5);

        Acervo acervo = new Acervo();

        List<Emprestimo> listaEmprestimos = new ArrayList<>();

        Datas d1 = new Datas(10, 05, 2022);
        Datas d2 = new Datas(17, 05, 2022);

        Pessoa p1 = new Pessoa("Daniel", "32894327328947");

        Emprestimo emp1 = new Emprestimo(1, l1, d1, d2, p1);
        Emprestimo emp2 = new Emprestimo(1, l2, d1, d2, p1);


        listaEmprestimos.add(emp1);
        listaEmprestimos.add(emp2);


        int opcao;
        do {

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
                    acervo.addLivroAcervo(codLivro);
                    codLivro++;
                    break;
                case 2:
                    System.out.println("2=Deletar Livro: ");
                    System.out.println("Insira o Código: ");
                    acervo.removerLivro(sc.nextInt(), listaLivros);
                    break;
                case 3:
                    int dia, mes, ano;
                    System.out.println("Cadastrar reserva: ");
                    System.out.println("Insira o Dia");
                    dia = sc.nextInt();
                    System.out.println("Insira o Mês");
                    mes = sc.nextInt();
                    System.out.println("Insira o Ano");
                    ano = sc.nextInt();
                    Datas d = new Datas(dia, mes, ano);
                    System.out.println("Insira o Código do Livr0: ");
                    int cod = sc.nextInt();
                    acervo.cadastrarReserva(d, cod);
                    break;
                case 4:

                    break;
                case 5:
                    System.out.println("Listar Acervo: ");

                    break;
                case 6:
                    System.out.println("Listar Acervo de Livros: ");
                    acervo.listarAcervo(Bancos.bancoLivros);
                    break;
                case 7:
                    System.out.println("Listar Emprestimos: ");
                    Emprestimo.imprimirListaEmprestimos(listaEmprestimos);
                    break;
                case 8:
                    System.out.println("Lista de Reservas: ");
                    acervo.listarReservas(Bancos.bancoReservas);
                    break;
                case 9:
                    acervo.encontrarPorCod2(1, Bancos.bancoLivros);
                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        } while (opcao != 0);
    }
}
