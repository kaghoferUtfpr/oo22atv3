package br.edu.utfpr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;


public class Calendario {

    public String diaHoje() {
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }

    public long validarEntrega(Datas data, int dias){

        LocalDate d1 = LocalDate.of(data.getAno(), data.getMes(), data.getDia());
        LocalDate d2 = LocalDate.of(2022, 5, 31);

        long days = ChronoUnit.DAYS.between(d1, d2);
        return days;
    }

    public Double validarMulta(long dias, Double multaDiaria, Double multaLimite)
    {
        Double valorMulta = dias * multaDiaria;
        int diasComMultaPadrao, diasComMultaAux;

        int multaExtra = 0;

        if(valorMulta > multaLimite){

        }
        if(dias > 0)
        {

        }
        return 0.0;
    }

    public boolean multaAteValor(Double valor)
    {

        return false;
    }

}
