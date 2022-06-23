package br.edu.utfpr.CRUDservice;

import br.edu.utfpr.config.BancoDados;
import br.edu.utfpr.entity.Emprestimo;
import br.edu.utfpr.entity.Livro;
import br.edu.utfpr.entity.Pessoa;
import br.edu.utfpr.entity.Reserva;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class EmprestimoService implements CrudService<Emprestimo>{
    @Override
    public void salvar(Emprestimo object) {
    }

    @Override
    public void remover(Long codigo) {
        Livro livro = BancoDados.bancoEmprestimos.stream().filter(l -> l.getCodigo() == codigo).findFirst().get().getLivro();
        Long codLivro = livro.getCodigo();
        acrescentarQtd(codLivro);
        BancoDados.bancoEmprestimos.removeIf(e -> e.getCodigo() == codigo);
    }

    @Override
    public Emprestimo buscarPorCodigo(Long codigo) {
        return null;
    }

    public long qtdDiasEmprestimo(LocalDate data) {
        LocalDate d1 = LocalDate.of(data.getYear(), data.getMonthValue(), data.getDayOfMonth());
        LocalDate d2 = LocalDate.now();
        long days = ChronoUnit.DAYS.between(d1, d2);
        return days;
    }

    public void devolverEmprestimo(Long codigo) {
        for (int i = 0; i < BancoDados.bancoEmprestimos.size(); i++) {
            if (BancoDados.bancoEmprestimos.get(i).getCodigo() == codigo) {
                System.out.println("Dados Devolução: ");
                System.out.println("Nome: " + BancoDados.bancoEmprestimos.get(i).getPessoa().getNome());
                System.out.printf("Livro: %s\t Autor: %s\n", BancoDados.bancoEmprestimos.get(i).getLivro().getTitulo(), BancoDados.bancoEmprestimos.get(i).getLivro().getAutor());
                long dias = qtdDiasEmprestimo(BancoDados.bancoEmprestimos.get(i).getDataLocacao());
                double multa = validarMulta(dias, 0.5, 1.0, 20.0);
                if (multa == 0.0) {
                    System.out.println("Devolução dentro do prazo.");
                    remover(codigo);
                } else {
                    System.out.printf("Existe um valor à pagar de: R$ %.2f\n", multa);
                    remover(codigo);
                }
            }
        }
    }
    public void acrescentarQtd(int cod) {
        BancoDados.bancoLivros.stream().filter(l -> l.getCodigo() == cod).forEach(l -> l.setQtdDisponivel(l.getQtdDisponivel() + 1));
    }

    public Double validarMulta(long dias, Double multaDiaria, Double multaAcimaLimite, Double multaLimite) {
        Double valorMulta = dias * multaDiaria;
        Double diasComMultaPadrao, diasComMultaAux, totalMulta;

        int multaExtra = 0;

        if (dias > 15) {
            if (valorMulta > multaLimite) {
                diasComMultaAux = (valorMulta - multaLimite) / multaDiaria;
                diasComMultaPadrao = dias - diasComMultaAux;
                totalMulta = ((diasComMultaPadrao * multaDiaria) + (diasComMultaAux * multaAcimaLimite));
                return totalMulta;
            } else {
                return valorMulta;
                //teste
            }
        }
        return 0.0;
    }

    public LocalDate disponibilidadeDataReservaEmprestimo(Long codLivro) {
        LocalDate dataDisp = null;
        for (int i = 0; i < BancoDados.bancoEmprestimos.size(); i++) {
            if (BancoDados.bancoEmprestimos.get(i).getLivro().getCodigo() == codLivro) {
                if (dataDisp == null) {
                    dataDisp = BancoDados.bancoEmprestimos.get(i).getDataDevolucao();
                } else if (dataDisp.isAfter(BancoDados.bancoEmprestimos.get(i).getDataDevolucao())) {
                    dataDisp = BancoDados.bancoEmprestimos.get(i).getDataDevolucao();
                }
            }
        }
        return dataDisp;
    }

    public void cadastrarEmprestimo(Long codLivro, Long codEmprestimo) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira nome da pessoa: ");
        String nome = sc.nextLine();
        Pessoa p = new Pessoa(nome);
        if (disponibilidadeQtd(codLivro) && disponibilidadadeStatutus(nome, codLivro)) {

            ReservaService res = new ReservaService();
            res.removerPorNome(p.getNome());
            Emprestimo emp = new Emprestimo();
            emp.setPessoa(p);
            LivroService livroService = new LivroService();
            emp.setLivro(livroService.buscarPorCodigo(codLivro));
            emp.setDataLocacao(LocalDate.now());
            emp.setDataDevolucao(LocalDate.now().plusDays(15));
            emp.setCodigo(codEmprestimo);
            decrementarQtd(codLivro);
            BancoDados.bancoEmprestimos.add(emp);
        } else {
            System.out.println("Fazer uma reserva, não tem disponibilidade imediata!");
            LocalDate data = disponibilidadeDataReservaEmprestimo(codLivro);
            System.out.printf("Reservas à partir de: %d/%d/%d\n", data.getDayOfMonth(), data.getMonthValue(), data.getYear());
        }
    }

    public boolean disponibilidadadeStatutus(String nome, Long codLivro){

        Reserva reserva = BancoDados.bancoReservas.stream().filter(f -> f.getLivro().getCodigo()==codLivro && f.getPessoa().getNome().equals(nome)).findFirst().orElse(null);
        if(reserva==null){
            return false;
        }
        return true;
    }

    public boolean disponibilidadeQtd(Long codLivro) {
        int qtd;
        for (int i = 0; i < BancoDados.bancoLivros.size(); i++) {
            if (codLivro == BancoDados.bancoLivros.get(i).getCodigo()) {
                qtd = BancoDados.bancoLivros.get(i).getQtdDisponivel();
                if (qtd > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void decrementarQtd(Long cod) {
        BancoDados.bancoLivros.stream().filter(l -> l.getCodigo() == cod).forEach(l -> l.setQtdDisponivel(l.getQtdDisponivel() - 1));
    }

    public void acrescentarQtd(Long cod) {
        BancoDados.bancoLivros.stream().filter(l -> l.getCodigo() == cod).forEach(l -> l.setQtdDisponivel(l.getQtdDisponivel() + 1));
    }

    public static void imprimirListaEmprestimos() {
        List<Emprestimo> lista = BancoDados.bancoEmprestimos;
        if (BancoDados.bancoEmprestimos.isEmpty()) {
            System.out.println("Lista Vazia!");
        } else {
            for (int i = 0; i < lista.size(); i++) {
                System.out.println("COD EMPRÉSTIMO: " + lista.get(i).getCodigo());
                System.out.println("Nome = " + lista.get(i).getPessoa().getNome());
                System.out.printf("Locação: %d/%d/%d\n", lista.get(i).getDataLocacao().getDayOfMonth(), lista.get(i).getDataLocacao().getMonthValue(), lista.get(i).getDataLocacao().getYear());
                System.out.printf("Devolução: %d/%d/%d\n", lista.get(i).getDataDevolucao().getDayOfMonth(), lista.get(i).getDataDevolucao().getMonthValue(), lista.get(i).getDataDevolucao().getYear());
                System.out.printf("Livro: %s De: %s\n", lista.get(i).getLivro().getTitulo(), lista.get(i).getLivro().getAutor());
                System.out.println("******************");
            }
        }
    }

}
