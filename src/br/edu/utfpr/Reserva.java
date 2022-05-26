package br.edu.utfpr;

import java.time.LocalDate;

public class Reserva {

    private Pessoa pessoa;
    private Livro livro;
    private LocalDate dataReserva;
    private LocalDate dataPrazoFinal;

    public Reserva(Livro livro, LocalDate dataReserva) {
        this.livro = livro;
        this.dataReserva = dataReserva;
        this.dataPrazoFinal = dataReserva.plusDays(2);
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDate getDataPrazoFinal() {
        return dataPrazoFinal;
    }

    public void setDataPrazoFinal(LocalDate dataPrazoFinal) {
        this.dataPrazoFinal = dataPrazoFinal;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }
}
