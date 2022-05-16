package br.edu.utfpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Acervo {

    public void listarAcervo(List<Livro> livros) {
        if (livros.size() < 1)
        {
            System.out.println("Lista Vazia!");
        }
        livros.forEach(l -> System.out.printf("Cod: %d\tQtd%d\t Autor: %s" + "\t" + "Livro: %s\n",l.getCodigo(),l.getQtdDisponivel(), l.getAutor(), l.getTitulo()));
    }

    public void addLivroAcervo(List<Livro> lista, int cod) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do autor: ");
        String autor = sc.nextLine();
        System.out.println("Digite o nome do livro");
        String titulo = sc.nextLine();
        System.out.println("Insira a quantidade disponível: ");
        int qtdDisp = sc.nextInt();

        Livro l = new Livro(autor, titulo, qtdDisp, cod);
        lista.add(l);
    }

    public void addListaInicial(List<Livro> livros, Livro l) {
        livros.add(l);
    }

    public boolean encontrarPorCod(int cod, List<Livro> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (cod == lista.get(i).getCodigo()) {
                return true;
            }
        }
        return false;
    }

    public void removerLivro(int cod, List<Livro> lista)
    {
        for (int i = 0; i < lista.size(); i++) {
            if(cod == lista.get(i).getCodigo())
            {
                lista.remove(i);
            }
        }
    }

}
