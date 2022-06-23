package br.edu.utfpr.CRUDservice;

import br.edu.utfpr.config.BancoDados;
import br.edu.utfpr.entity.Pessoa;

import javax.swing.text.html.parser.Entity;

public class PessoaService implements CrudService<Pessoa>{
    @Override
    public void salvar(Pessoa object) {

    }

    @Override
    public void remover(Long codigo) {

    }

    @Override
    public Pessoa buscarPorCodigo(Long codigo) {
        return BancoDados.bancoPessoas.stream().filter(fi -> fi.getId()==codigo).findFirst().orElse(null);
    }
}
