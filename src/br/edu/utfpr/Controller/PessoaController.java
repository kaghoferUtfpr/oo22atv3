package br.edu.utfpr.Controller;
import br.edu.utfpr.Bancos;
import br.edu.utfpr.Pessoa;

public class PessoaController {

    public void cadastrarPessoa(String nome)
    {
        Pessoa p = new Pessoa(nome);
        Bancos.bancoPessoas.add(p);
    }
}
