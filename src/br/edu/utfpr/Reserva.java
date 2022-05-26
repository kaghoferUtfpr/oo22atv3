package br.edu.utfpr;

import java.time.LocalDate;

public class Reserva {
    private Livro livro;
    private LocalDate dataReserva;

    public Reserva(Livro livro, LocalDate dataReserva) {
        this.livro = livro;
        this.dataReserva = dataReserva;
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
