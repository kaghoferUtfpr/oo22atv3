package br.edu.utfpr.entity;

import java.time.LocalDate;

public class Reserva extends Entity{

    private Long codigo;
    private Pessoa pessoa;
    private Livro livro;
    private LocalDate dataReserva;
    private LocalDate dataPrazoFinal;
    private boolean statusDispReserva;

    public Reserva(Livro livro, LocalDate dataReserva, Pessoa pessoa, Long codigo) {
        this.livro = livro;
        this.dataReserva = dataReserva;
        this.dataPrazoFinal = dataReserva.plusDays(2);
        this.pessoa = pessoa;
        this.codigo = codigo;
    }

    public boolean isStatusDispReserva() {
        return statusDispReserva;
    }

    public void setStatusDispReserva(boolean statusDispReserva) {
        this.statusDispReserva = statusDispReserva;
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

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
}
