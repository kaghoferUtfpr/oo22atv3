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

        int codLivro = 1;

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

        Acervo acervo = new Acervo();
        acervo.addListaInicial(listaLivros, l1);
        acervo.addListaInicial(listaLivros, l2);
        acervo.addListaInicial(listaLivros, l3);
        acervo.addListaInicial(listaLivros, l4);
        acervo.addListaInicial(listaLivros, l5);

        List<Emprestimo> listaEmprestimos = new ArrayList<>();


        int opcao;
        do {

            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Deletar Livro");
            System.out.println("3 - Reservar Livro");
            System.out.println("4 - Emprestimo Livro");
            System.out.println("5 - Devolver Livro");
            System.out.println("6 - Listar Acervo");
            System.out.println("7 - Listar Empréstimos");
            System.out.println("0 - SAIR");
            System.out.println("Insira uma opção: ");
            Scanner sc = new Scanner(System.in);
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("1=Cadastro de Livro: ");
                    acervo.addLivroAcervo(listaLivros, codLivro);
                    codLivro++;
                    break;
                case 2:
                    System.out.println("2=Deletar Livro: ");
                    System.out.println("Insira o Código: ");
                    acervo.removerLivro(sc.nextInt(),listaLivros);
                    break;
                case 3:
                    System.out.println("3");
                    break;
                case 4:
                    System.out.println("3");
                    break;
                case 5:
                    System.out.println("Listar Acervo: ");

                    break;
                case 6:
                    System.out.println("Listar Acervo de Livros: ");
                    acervo.listarAcervo(listaLivros);
                    break;
                case 7:
                    System.out.println("Listar Emprestimos: ");
                    Emprestimo.imprimirListaEmprestimos(listaEmprestimos);
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
        } while (opcao != 0);

//        Emprestimo.imprimirListaEmprestimos(listaEmprestimos);


    }
}
