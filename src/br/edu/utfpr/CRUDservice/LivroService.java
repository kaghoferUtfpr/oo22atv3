package br.edu.utfpr.CRUDservice;

import br.edu.utfpr.config.BancoDados;
import br.edu.utfpr.entity.Livro;
import java.util.List;
import java.util.Scanner;

public class LivroService implements CrudService<Livro>{


    @Override
    public void salvar(Livro livro) {
        BancoDados.bancoLivros.add(livro);
    }

    @Override
    public void remover(Long codigo) {
        Livro l = buscarPorCodigo(codigo);
        BancoDados.bancoLivros.remove(l);
    }

    @Override
    public Livro buscarPorCodigo(Long codigo) {
        return BancoDados.bancoLivros.stream().filter(fi -> fi.getCodigo()==codigo).findFirst().orElse(null);
    }

    public Livro lerDadosLivro(Long cod) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do autor: ");
        String autor = sc.nextLine();
        System.out.println("Digite o nome do livro");
        String titulo = sc.nextLine();
        System.out.println("Insira a quantidade disponível: ");
        int qtdDisp = sc.nextInt();
        return new Livro(autor, titulo, qtdDisp, cod);
    }

    public void listarAcervo(List<Livro> livros) {
        System.out.printf("\n\n");
        if (livros.size() < 1) {
            System.out.println("Lista Vazia!");
        }
        livros.forEach(l -> System.out.printf("Cod: %d\tQtd%d\t Autor: %s" + "\t" + "Livro: %s\n", l.getCodigo(), l.getQtdDisponivel(), l.getAutor(), l.getTitulo()));
        System.out.printf("\n\n");
    }
}
