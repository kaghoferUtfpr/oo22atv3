package br.edu.utfpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Acervo {

    private final List<Livro> livros = new ArrayList<>();

    public void listarAcervo()
    {
        livros.forEach(l -> System.out.printf("Autor: %s\tLivro: %s\n",l.getAutor(), l.getTitulo()));
    }

    public void  addLivroAcervo()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do autor: ");
        String autor = sc.nextLine();
        System.out.println("Digite o nome do livro");
        String titulo = sc.nextLine();
        Livro l = new Livro(autor, titulo);
        livros.add(l);
    }


}
