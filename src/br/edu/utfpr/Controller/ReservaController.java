package br.edu.utfpr.Controller;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class ReservaController {

    private String está_entre;

    public boolean verificarDisponibilidade(LocalDate d1, LocalDate d2) {
        if (d1.isAfter(d2)) {
            return true;
        }
        return false;
    }

    public void diasEmAtraso(LocalDate d1, LocalDate d2) {
        long dias = Duration.between(d1.atStartOfDay(), d2.atStartOfDay()).toDays();
    }



}
