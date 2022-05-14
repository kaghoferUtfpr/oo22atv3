package br.edu.utfpr;

import java.util.ArrayList;
import java.util.List;

public class Alugueis {

    List<Emprestimo> listEmp = new ArrayList<>();

    public void listarEmprestimos()
    {
        listEmp.forEach(l -> System.out.println(l.getLivro()));
    }

    public void addAluguel()
    {

    }
}
