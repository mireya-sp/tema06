package com.mireyaserrano.tema06.pilasycolas.Ejercicio7;

import java.time.LocalDateTime;

public class Hospital {
     private ColaConsulta[] consultas;
     private final int TAMANYO_CONSULTAS = 5;

    public Hospital() {
        consultas = new ColaConsulta[TAMANYO_CONSULTAS];
        for (int i = 0; i < TAMANYO_CONSULTAS; i++){
            Medicos medico = new Medicos("Doctor"+i, 111111*(i+1));
            consultas[i] = new ColaConsulta(medico);

            for (int j = 0; i < TAMANYO_CONSULTAS; j++){
                Paciente paciente = new Paciente("Paciente", 77777777/(j+1)+"y", LocalDateTime.now());
                consultas[i].anyadirPaciente(paciente);
            }
        }
    }

    public ColaConsulta[] getConsultas() {
        return consultas;
    }

    public void mostrarEstado(){
        for (int i = 0; i < consultas.length; i++) {
            System.out.println("Consulta " + (i+1) + ": " + consultas[i]);
        }
    }
}
