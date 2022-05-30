package br.edu.utfpr;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class Acervo {

    Scanner sc = new Scanner(System.in);

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
        lista.forEach(l -> System.out.printf("Cod: %d\tData Res: %d/%d/%d\t Data Fin: %d/%d/%d\t Autor: %s" + "\t" + "Livro: %s \t Para: %s\n",
                l.getLivro().getCodigo(), l.getDataReserva().getDayOfMonth(),
                l.getDataReserva().getMonthValue(), l.getDataReserva().getYear(), l.getDataPrazoFinal().getDayOfMonth(),
                l.getDataPrazoFinal().getMonthValue(), l.getDataPrazoFinal().getYear(),
                l.getLivro().getAutor(), l.getLivro().getTitulo(), l.getPessoa().getNome()));
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

    public void cadastrarReserva(LocalDate data, int codLivro, Pessoa pessoa) {
        apagarReservasAntigas();
        Livro l = encontrarPorCod(codLivro);
        if (l != null && disponibilidadeQtd(codLivro)) {
            Reserva r = new Reserva(l, data, pessoa);
            Bancos.bancoReservas.add(r);
        }
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
//        for (int i = 0; i < Bancos.bancoEmprestimos.size(); i++) {
//            if (Bancos.bancoEmprestimos.get(i).getCodigo() == codEmprestimo) {
//                Bancos.bancoEmprestimos.remove(i);
//            }
//        }
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
        //apagarReservasAntigas();
        System.out.println("Insira nome da pessoa: ");
        Pessoa p = new Pessoa(sc.nextLine());
        apagarReserva(p.getNome());
        Emprestimo emp = new Emprestimo();
        if (disponibilidadeQtd(codLivro)) {
            System.out.println("Insira nome da pessoa: ");
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
            System.out.printf("Reservas à partir de: %d/%d/%d\n", data.getDayOfMonth(), data.getDayOfMonth(), data.getYear());
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

    public void testando(){
        Bancos.bancoEmprestimos.stream().forEach(e -> e.getLivro().getCodigo());
    }

}
