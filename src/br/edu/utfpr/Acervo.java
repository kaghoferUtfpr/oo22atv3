package br.edu.utfpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Acervo {

    public void listarAcervo(List<Livro> livros) {
        if (livros.size() < 1) {
            System.out.println("Lista Vazia!");
        }
        livros.forEach(l -> System.out.printf("Cod: %d\tQtd%d\t Autor: %s" + "\t" + "Livro: %s\n", l.getCodigo(), l.getQtdDisponivel(), l.getAutor(), l.getTitulo()));
    }

    public void listarReservas(List<Reserva> lista) {
        if (lista.size() < 1) {
            System.out.println("Lista Vazia!");
        }
        lista.forEach(l -> System.out.printf("Cod: %d\tData Res: %d/%d/%d\t Autor: %s" + "\t" + "Livro: %s\n",
                l.getLivro().getCodigo(), l.getDataReserva().getDia(),
                l.getDataReserva().getMes(), l.getDataReserva().getAno(),
                l.getLivro().getAutor(), l.getLivro().getTitulo()));
    }


    public void addLivroAcervo(int cod) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do autor: ");
        String autor = sc.nextLine();
        System.out.println("Digite o nome do livro");
        String titulo = sc.nextLine();
        System.out.println("Insira a quantidade disponível: ");
        int qtdDisp = sc.nextInt();

        Livro l = new Livro(autor, titulo, qtdDisp, cod);
        Bancos.bancoLivros.add(l);
    }

    public Livro encontrarPorCod(int cod, List<Livro> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (cod == lista.get(i).getCodigo()) {
                return lista.get(i);
            }
        }
        return null;
    }
    public Livro encontrarPorCod2(int cod, List<Livro> lista) {
        lista.stream().filter(l -> l.getCodigo() == cod).forEach(l -> l.setQtdDisponivel(l.getQtdDisponivel()-1));
        return null;
    }

    public void cadastrarReserva(Datas data, int codLivro) {
        Livro l = encontrarPorCod(codLivro, Bancos.bancoLivros);
        System.out.println(l.getQtdDisponivel());
        if (l != null) {
            Bancos.bancoLivros.get(codLivro).setQtdDisponivel(Bancos.bancoLivros.get(codLivro).getQtdDisponivel() - 1);
            Reserva r = new Reserva(l, data);
            Bancos.bancoReservas.add(r);
        } else {
            System.out.println("Erro Data ou Código do Livro não existe, ou fora de estoque");
        }
    }

//    public boolean disponibilidadeQtd(int codLivro) {
//        //int d = Bancos.bancoLivros.stream().filter(l -> l.getCodigo() == codLivro).forEach(Livro::getQtdDisponivel);
//        if (l.getQtdDisponivel() > 0) {
//            return true;
//        }
//        return false;
//    }

    public void removerLivro(int cod, List<Livro> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (cod == lista.get(i).getCodigo()) {
                lista.remove(i);
            }
        }
    }

}
