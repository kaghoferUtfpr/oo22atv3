package br.edu.utfpr.CRUDservice;
import br.edu.utfpr.config.BancoDados;
import br.edu.utfpr.entity.Livro;
import br.edu.utfpr.entity.Pessoa;
import br.edu.utfpr.entity.Reserva;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservaService implements CrudService<Reserva> {
    @Override
    public void salvar(Reserva object) {

    }

    @Override
    public void remover(Long codigo) {

    }

    @Override
    public Reserva buscarPorCodigo(Long codigo) {
        return BancoDados.bancoReservas.stream().filter(fl -> fl.getCodigo()==codigo).findFirst().orElse(null);
    }

    public void removerPorNome(String nome) {
        BancoDados.bancoReservas.remove(BancoDados.bancoReservas.stream().filter(f -> f.getPessoa().getNome().equals(nome)).findFirst().get());
    }

    public void apagarReservasAntigas() {
        for (int i = 0; i < BancoDados.bancoReservas.size(); i++) {
            if (BancoDados.bancoReservas.get(i).getDataPrazoFinal().isBefore(LocalDate.now())) {
                BancoDados.bancoReservas.remove(i);
            }
        }
    }

    public void listarReservas(List<Reserva> lista) {
        if (lista.size() < 1) {
            System.out.println("Lista Vazia!");
        }
        lista.forEach(l -> System.out.printf("Cod Livro: %d\tData Res: %d/%d/%d\t Data Fin: %d/%d/%d\t Autor: %s" + "\t" + "Livro: %s \t Para: %s\n", l.getLivro().getCodigo(), l.getDataReserva().getDayOfMonth(), l.getDataReserva().getMonthValue(), l.getDataReserva().getYear(), l.getDataPrazoFinal().getDayOfMonth(), l.getDataPrazoFinal().getMonthValue(), l.getDataPrazoFinal().getYear(), l.getLivro().getAutor(), l.getLivro().getTitulo(), l.getPessoa().getNome()));
    }

    public void cadastrarReserva() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite código de um livro: ");
        int codLivro = sc.nextInt();
        sc.nextLine();
        Livro li = BancoDados.bancoLivros.stream().filter(f -> f.getCodigo() == codLivro).findFirst().get();
        int qtdDisp = li.getQtdDisponivel();
        long contReservas = BancoDados.bancoReservas.stream().filter(f -> f.getLivro().getCodigo() == codLivro).count();
        if (contReservas == li.getEstoque()) {
            System.out.println("Reservas suspensas para esse livro, atingido limite de reservas!");
        } else {
            if (qtdDisp > 0) {
                System.out.println("Não é possivel reservar, livro está disponivel no momento.");
            } else {
                System.out.println("Escolha uma das Datas");
                List<LocalDate> datas = BancoDados.bancoEmprestimos.stream().filter(f -> f.getLivro().getCodigo() == codLivro && f.isStatus()).map(m -> m.getDataDevolucao()).toList();
                int opc;
                LocalDate dataRes;
                do {
                    System.out.println("Opções de data de reserva: ");
                    for (int i = 0; i < datas.size(); i++) {
                        System.out.printf("Digite: >> %d para: %d/%d/%d\n", i + 1, datas.get(i).getDayOfMonth(), datas.get(i).getMonthValue(), datas.get(i).getYear());
                    }
                    opc = sc.nextInt();
                    sc.nextLine();
                } while (opc < 1 || opc > datas.size());
                dataRes = datas.get(opc - 1);
                System.out.println("Digite o nome: ");
                Pessoa p = new Pessoa(sc.nextLine());
                Reserva res = new Reserva(li, dataRes, p);
                BancoDados.bancoEmprestimos.stream().filter(fil -> fil.getLivro().getCodigo() == codLivro && fil.getDataDevolucao().isEqual(dataRes)).findFirst().get().setStatus(false);
                BancoDados.bancoReservas.add(res);
            }
        }

    }

}
