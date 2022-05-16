package br.edu.utfpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Acervo {

    public void listarAcervo(List<Livro> livros) {
        livros.forEach(l -> System.out.printf("Autor: %s" + "\t" + "Livro: %s\n", l.getAutor(), l.getTitulo()));
    }

    public void addLivroAcervo(List<Livro> lista) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira um código para o livro: ");
        int cod = sc.nextInt();
        sc.nextLine();
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
                //System.out.println("Achou");
                return true;
            }
        }
        //System.out.println("Não Achou");
        return false;
    }

    public void removerLivro(int cod, List<Livro> lista)
    {
        for (int i = 0; i < lista.size(); i++) {
            if(encontrarPorCod(cod, lista))
            {
                lista.remove(i);
            }
        }
    }

}
