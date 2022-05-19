package br.edu.utfpr;

public class Reserva {
    private Livro livro;
    private Datas dataReserva;

    public Reserva(Livro livro, Datas dataReserva) {
        this.livro = livro;
        this.dataReserva = dataReserva;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Datas getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Datas dataReserva) {
        this.dataReserva = dataReserva;
    }
}
