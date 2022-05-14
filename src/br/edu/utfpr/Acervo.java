package br.edu.utfpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Acervo {

    private final List<Livro> livros = new ArrayList<>();

    public void listarAcervo()
    {
        livros.forEach(l -> System.out.printf("Autor: %s" + "\t" + "Livro: %s\n",l.getAutor(), l.getTitulo()));
    }

    public void addLivroAcervo()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do autor: ");
        String autor = sc.nextLine();
        System.out.println("Digite o nome do livro");
        String titulo = sc.nextLine();
        System.out.println("Insira a quantidade disponível: ");
        int qtdDisp = sc.nextInt();
        System.out.println("Insira um código para o livro: ");
        int cod = sc.nextInt();
        Livro l = new Livro(autor,titulo,qtdDisp, cod);
        livros.add(l);
    }

    public void addListaInicial(Livro livro)
    {
        livros.add(livro);
    }

}
