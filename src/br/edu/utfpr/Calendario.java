package br.edu.utfpr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


public class Calendario {

    private static class dt{
        private int dia;
        private int mes;
        private int ano;

        public dt(int dia, int mes, int ano) {
            this.dia = dia;
            this.mes = mes;
            this.ano = ano;
        }

        public int getDia() {
            return dia;
        }

        public void setDia(int dia) {
            this.dia = dia;
        }

        public int getMes() {
            return mes;
        }

        public void setMes(int mes) {
            this.mes = mes;
        }

        public int getAno() {
            return ano;
        }

        public void setAno(int ano) {
            this.ano = ano;
        }
    }


    public String diaHoje() {
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    public void CompareTwoDatesTest(String dataDevolucao) throws ParseException {

        SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yyyy");
        LocalDate dtNow = LocalDate.now();

        Date d1 = sdformat.parse(dataDevolucao);

        System.out.println("The date 1 is: " + sdformat.format(d1));
        System.out.println("Data agora: "+dtNow);

    }

}
