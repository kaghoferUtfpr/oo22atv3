package br.edu.utfpr;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Acervo {

    Scanner sc = new Scanner(System.in);

    public void listarAcervo(List<Livro> livros) {
        System.out.printf("\n\n");
        if (livros.size() < 1) {
            System.out.println("Lista Vazia!");
        }
        livros.forEach(l -> System.out.printf("Cod: %d\tQtd%d\t Autor: %s" + "\t" + "Livro: %s\n", l.getCodigo(), l.getQtdDisponivel(), l.getAutor(), l.getTitulo()));
        System.out.printf("\n\n");
    }

    public void listarReservas(List<Reserva> lista) {
        if (lista.size() < 1) {
            System.out.println("Lista Vazia!");
        }
        lista.forEach(l -> System.out.printf("Cod Livro: %d\tData Res: %d/%d/%d\t Data Fin: %d/%d/%d\t Autor: %s" + "\t" + "Livro: %s \t Para: %s\n", l.getLivro().getCodigo(), l.getDataReserva().getDayOfMonth(), l.getDataReserva().getMonthValue(), l.getDataReserva().getYear(), l.getDataPrazoFinal().getDayOfMonth(), l.getDataPrazoFinal().getMonthValue(), l.getDataPrazoFinal().getYear(), l.getLivro().getAutor(), l.getLivro().getTitulo(), l.getPessoa().getNome()));
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
        Bancos.bancoLivros.stream().filter(l -> l.getCodigo() == cod).forEach(l -> l.setQtdDisponivel(l.getQtdDisponivel() - 1));
    }

    public void acrescentarQtd(int cod) {
        Bancos.bancoLivros.stream().filter(l -> l.getCodigo() == cod).forEach(l -> l.setQtdDisponivel(l.getQtdDisponivel() + 1));
    }

    public void cadastrarReserva() {
        System.out.println("Digite código de um livro: ");
        int codLivro = sc.nextInt();
        sc.nextLine();
        Livro li = Bancos.bancoLivros.stream().filter(f -> f.getCodigo() == codLivro).findFirst().get();
        int qtdDisp = li.getQtdDisponivel();
        long contReservas = Bancos.bancoReservas.stream().filter(f -> f.getLivro().getCodigo() == codLivro).count();
        if (contReservas == li.getEstoque()) {
            System.out.println("Reservas suspensas para esse livro, atingido limite de reservas!");
        } else {
            if (qtdDisp > 0) {
                System.out.println("Não é possivel reservar, livro está disponivel no momento.");
            } else {
                System.out.println("Escolha uma das Datas");
                List<LocalDate> datas = Bancos.bancoEmprestimos.stream().filter(f -> f.getLivro().getCodigo() == codLivro && f.isStatus()).map(m -> m.getDataDevolucao()).toList();
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
                Bancos.bancoEmprestimos.stream().filter(fil -> fil.getLivro().getCodigo() == codLivro && fil.getDataDevolucao().isEqual(dataRes)).findFirst().get().setStatus(false);
                Bancos.bancoReservas.add(res);
            }
        }

    }

    public Pessoa coletarDadosPessoa() {
        String nome;
        System.out.println("Digite o nome:");
        nome = sc.nextLine();
        Pessoa pessoa = new Pessoa(nome);
        return pessoa;
    }

    public LocalDate disponibilidadeDataReservaEmprestimo(int codLivro) {
        LocalDate dataDisp = null;
        for (int i = 0; i < Bancos.bancoEmprestimos.size(); i++) {
            if (Bancos.bancoEmprestimos.get(i).getLivro().getCodigo() == codLivro) {
                if (dataDisp == null) {
                    dataDisp = Bancos.bancoEmprestimos.get(i).getDataDevolucao();
                } else if (dataDisp.isAfter(Bancos.bancoEmprestimos.get(i).getDataDevolucao())) {
                    dataDisp = Bancos.bancoEmprestimos.get(i).getDataDevolucao();
                }
            }
        }
        return dataDisp;
    }

    public boolean disponibilidadeQtd(int codLivro) {
        int qtd;
        for (int i = 0; i < Bancos.bancoLivros.size(); i++) {
            if (codLivro == Bancos.bancoLivros.get(i).getCodigo()) {
                qtd = Bancos.bancoLivros.get(i).getQtdDisponivel();
                if (qtd > 0) {
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

    public boolean dataEntrePeriodo(LocalDate d1, LocalDate d2, LocalDate dataReserva) {
        if (dataReserva.isAfter(d1) && dataReserva.isBefore(d2)) {
            System.out.println("está_entre");
            return true;
        }
        return false;
    }

    public static void imprimirListaEmprestimos() {
        List<Emprestimo> lista = Bancos.bancoEmprestimos;
        if (Bancos.bancoEmprestimos.isEmpty()) {
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

    public void apagarReservasAntigas() {
        for (int i = 0; i < Bancos.bancoReservas.size(); i++) {
            if (Bancos.bancoReservas.get(i).getDataPrazoFinal().isBefore(LocalDate.now())) {
                Bancos.bancoReservas.remove(i);
            }
        }
    }

    public void apagarEmprestimo(int codEmprestimo) {
        Livro livro = Bancos.bancoEmprestimos.stream().filter(l -> l.getCodigo() == codEmprestimo).findFirst().get().getLivro();
        int codLivro = livro.getCodigo();
        acrescentarQtd(codLivro);
        Bancos.bancoEmprestimos.removeIf(e -> e.getCodigo() == codEmprestimo);
    }


    public void apagarReserva(String nome) {
        for (int i = 0; i < Bancos.bancoReservas.size(); i++) {
            if (Bancos.bancoReservas.get(i).getPessoa().getNome().equals(nome)) {
                Bancos.bancoReservas.remove(i);
            }
        }
    }

    public void cadastrarEmprestimo(int codLivro, int codEmprestimo) {
        apagarReservasAntigas();
        if (disponibilidadeQtd(codLivro)) {
            System.out.println("Insira nome da pessoa: ");
            String nome = sc.nextLine();
            Pessoa p = new Pessoa(nome);
            apagarReserva(p.getNome());
            Emprestimo emp = new Emprestimo();
            emp.setPessoa(p);
            emp.setLivro(encontrarPorCod(codLivro));
            emp.setDataLocacao(LocalDate.now());
            emp.setDataDevolucao(LocalDate.now().plusDays(15));
            emp.setCodigo(codEmprestimo);
            decrementarQtd(codLivro);
            Bancos.bancoEmprestimos.add(emp);
        } else {
            System.out.println("Fazer uma reserva, não tem disponibilidade imediata!");
            LocalDate data = disponibilidadeDataReservaEmprestimo(codLivro);
            System.out.printf("Reservas à partir de: %d/%d/%d\n", data.getDayOfMonth(), data.getMonthValue(), data.getYear());
        }
    }

    public void diaParaReservar(int codLivro) {
        long result = Bancos.bancoEmprestimos.stream().filter(e -> e.getLivro().getCodigo() == codLivro).count();
        System.out.println("Qdt: " + result);
    }

    public long qtdDiasEmprestimo(LocalDate data) {
        LocalDate d1 = LocalDate.of(data.getYear(), data.getMonthValue(), data.getDayOfMonth());
        LocalDate d2 = LocalDate.now();
        long days = ChronoUnit.DAYS.between(d1, d2);
        return days;
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

    public boolean checarReserva(String nome, LocalDate data, int codLivro) {

        for (int i = 0; i < Bancos.bancoReservas.size(); i++) {
            if (Bancos.bancoReservas.get(i).getLivro().getCodigo() == codLivro) {
                if (nome == Bancos.bancoReservas.get(i).getPessoa().getNome()) {
                    return true;
                } else if (data.isAfter(Bancos.bancoReservas.get(i).getDataPrazoFinal())) {
                    return true;
                } else if (encontrarPorCod(codLivro).getQtdDisponivel() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void devolverEmprestimo(int codEmprestimo) {
        for (int i = 0; i < Bancos.bancoEmprestimos.size(); i++) {
            if (Bancos.bancoEmprestimos.get(i).getCodigo() == codEmprestimo) {
                System.out.println("Dados Devolução: ");
                System.out.println("Nome: " + Bancos.bancoEmprestimos.get(i).getPessoa().getNome());
                System.out.printf("Livro: %s\t Autor: %s\n", Bancos.bancoEmprestimos.get(i).getLivro().getTitulo(), Bancos.bancoEmprestimos.get(i).getLivro().getAutor());
                long dias = qtdDiasEmprestimo(Bancos.bancoEmprestimos.get(i).getDataLocacao());
                double multa = validarMulta(dias, 0.5, 1.0, 20.0);
                if (multa == 0.0) {
                    System.out.println("Devolução dentro do prazo.");
                    apagarEmprestimo(codEmprestimo);
                } else {
                    System.out.printf("Existe um valor à pagar de: R$ %.2f\n", multa);
                    apagarEmprestimo(codEmprestimo);

                }
            }
        }
    }

    public void testando() {
        Bancos.bancoLivros.stream().map(livro -> livro.getTitulo()).filter(aut -> aut.startsWith("A")).forEach(System.out::println);
    }

}
