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

public class Main {
    public static void main(String[] args) throws ParseException {

        Livro l1 = new Livro( "Erik Brynjolfsson e Andrew Mcafee","A Segunda Era das Máquinas", 2, 1111);
        Livro l2 = new Livro("Susan Cain", "O Poder dos Quietos", 2, 2222);
        Livro l3 = new Livro("Paul Barry e David Griffiths", "Use a cabeça! Programação", 2, 3333);
        Livro l4 = new Livro("Klaus Schwab", "A Quarta Revolução Industrial", 2, 4444);
        Livro l5 = new Livro("Ernesto Mario Haberkorn", "Um Bate-papo sobre T.I", 2, 5555);

        Acervo acervo = new Acervo();
        acervo.addListaInicial(l1);
        acervo.addListaInicial(l2);
        acervo.addListaInicial(l3);
        acervo.addListaInicial(l4);
        acervo.addListaInicial(l5);

        //acervo.addLivroAcervo();
        //acervo.listarAcervo();

        Alugueis al = new Alugueis();


        al.listarEmprestimos();


    }
}
