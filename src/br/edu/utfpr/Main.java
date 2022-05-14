package br.edu.utfpr;/*

Criar programa orientado à objetos para solucionar os seguintes casos de uso:
- Será o controle de uma biblioteca
- Desenvolver método para a locação de livro
- deverá validar se livro está disponível
- Método para reserva de livro
- Validá se há alguma reserva para data e se estará disponível
- Método para devolução do livro
- Válida se a data da entrega é anterior a data máxima
- caso tenha vencido calcular 50 centavos ao dia até 20 reais, depois 1 real por dia
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {

        LocalDate d1 = LocalDate.of(2022, 5, 1);
        LocalDate d2 = LocalDate.of(2022, 5, 31);

        long days = ChronoUnit.DAYS.between(d1, d2);
        System.out.println( days );

        Calendario c = new Calendario();

        c.CompareTwoDatesTest("11/10/2022");

    }
}
