package br.edu.utfpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BinaryOperator;

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

    public Livro encontrarPorCod(int cod) {
        for (int i = 0; i < Bancos.bancoLivros.size(); i++) {
            if (cod == Bancos.bancoLivros.get(i).getCodigo()) {
                return Bancos.bancoLivros.get(i);
            }
        }
        return null;
    }

    public void decrementarQtd(int cod) {
        Bancos.bancoLivros.stream().filter(l -> l.getCodigo() == cod).forEach(l -> l.setQtdDisponivel(l.getQtdDisponivel()-1));
    }
    public void acrescentarQtd(int cod) {
        Bancos.bancoLivros.stream().filter(l -> l.getCodigo() == cod).forEach(l -> l.setQtdDisponivel(l.getQtdDisponivel()+1));
    }

    public void cadastrarReserva(Datas data, int codLivro) {
        Livro l = encontrarPorCod(codLivro);
        System.out.println(l.getQtdDisponivel());
        if (l != null && disponibilidadeQtd(codLivro)) {
            Reserva r = new Reserva(l, data);
            Bancos.bancoReservas.add(r);
        }
    }

    public boolean disponibilidadeData(Datas data, int codLivro)
    {
        if(encontrarPorCod(codLivro) != null)
        {
            Livro l = encontrarPorCod(codLivro);
            //if(Bancos.bancoReservas)
            return true;
        }
        return false;
    }

    public boolean disponibilidadeQtd(int codLivro) {
        int qtd;
        for (int i = 0; i < Bancos.bancoLivros.size(); i++) {
            if(codLivro == Bancos.bancoLivros.get(i).getCodigo())
            {
                qtd = Bancos.bancoLivros.get(i).getQtdDisponivel();
                if(qtd > 0){
                    return true;
                }
            }
        }
        return false;
    }

    public void removerLivro(int cod, List<Livro> lista) {
        for (int i = 0; i < lista.size(); i++) {
            if (cod == lista.get(i).getCodigo()) {
                lista.remove(i);
            }
        }
    }

}
