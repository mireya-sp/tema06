package com.mireyaserrano.tema06.Ejercicio7;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AtencionPaciente {
    public final static int MIN_TEMPERATURA = 33;
    public final static int MAX_TEMPERATURA = 45;
    public final static int MIN_PPM = 35;
    public final static int MAX_PPM = 230;
    public final static int MIN_TENSION_SISTOLICA = 50;
    public final static int MAX_TENSION_SISTOLICA = 250;
    public final static int MIN_TENSION_DIASTOLICA = 20;
    public final static int MAX_TENSION_DIASTOLICA = 150;

    // Constantes para el acceso a los componentes del array preRev
    public final static int TEMPERATURA = 0;
    public final static int PPM = 1;
    public final static int TENSION_SISTOLICA = 2;
    public final static int TENSION_DIASTOLICA = 3;

    public enum MotivoAlta {
        MEJORA, DERIVACION_HOSPITAL, DEFUNCION;

        @Override
        public String toString() {
            return switch (this) {
                case MEJORA -> "Mejora";
                case DERIVACION_HOSPITAL -> "Derivación hospital";
                case DEFUNCION -> "Defunción";
            };
        }

        public static MotivoAlta fromInteger(int x) {
            return switch (x) {
                case 0 -> MEJORA;
                case 1 -> DERIVACION_HOSPITAL;
                case 2 -> DEFUNCION;
                default -> null;
            };
        }
    }
    private final Paciente paciente;
    private final LocalDateTime fechaEntrada;
    private final String sintomatologia;
    private float[] preRev;
    private LocalDateTime fechaAlta;
    private MotivoAlta motivoAlta;

    private AtencionPaciente(Paciente paciente, String sintomatologia, LocalDateTime fechaEntrada) {
        this.paciente = paciente;
        this.sintomatologia = sintomatologia;
        this.fechaEntrada = fechaEntrada;
        this.fechaAlta = null;
        this.preRev = null;
    }

    public AtencionPaciente(Paciente paciente, String sintomatologia) {
        this(paciente, sintomatologia, LocalDateTime.now());
    }

    public AtencionPaciente(Paciente paciente, String sintomatologia, LocalDateTime fechaEntrada, float temperatura, float ppm,
                          float tenSis, float tenDias, LocalDateTime fechaAlta, MotivoAlta motivoAlta) {
        this(paciente, sintomatologia, fechaEntrada);
        atender(temperatura, ppm, tenSis, tenDias);
        this.fechaAlta = fechaAlta;
        this.motivoAlta = motivoAlta;
    }

    /**
     * Registra las constantes vitales
     * @param temperatura Temperatura del paciente en grados centígrados
     * @param ppm Pulsaciones por minuto
     * @param tenSis Tensión Sistólica
     * @param tenDias Tensión Diastólica
     * @return true si el paciente ha sido atendido, false si ya había sido dado de alta
     */
    public boolean atender(float temperatura, float ppm, float tenSis, float tenDias) {
        if (fechaAlta == null) {
            this.preRev = new float[4];
            this.preRev[TEMPERATURA] = temperatura;
            this.preRev[PPM] = ppm;
            this.preRev[TENSION_SISTOLICA] = tenSis;
            this.preRev[TENSION_DIASTOLICA] = tenDias;
            return true;
        }
        return false;
    }

    /**
     * Da de alta al paciente con el motivo indicado
     * @param motivoAlta Motivo por el cual se da de alta al paciente
     * @return true si el paciente ha sido dado de alta, false si ya había sido dado de alta previamente
     */
    public boolean darAlta(MotivoAlta motivoAlta) {
        if (fechaAlta == null) {
            fechaAlta = LocalDateTime.now();
            this.motivoAlta = motivoAlta;
            return true;
        }
        return false;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public String getSintomatologia() {
        return sintomatologia;
    }

    public float getTemperatura() {
        return preRev[TEMPERATURA];
    }

    public float getPpm() {
        return preRev[PPM];
    }

    public float getTensionSistolica() {
        return preRev[TENSION_SISTOLICA];
    }
    public float getTensionDiastolica() {
        return preRev[TENSION_DIASTOLICA];
    }

    public float[] getPreRev() {
        return preRev;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public MotivoAlta getMotivoAlta() {
        return motivoAlta;
    }

    public boolean isAtendido() {
        return preRev != null;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof AtencionPaciente that)) return false;

        return paciente.equals(that.paciente) && fechaEntrada.equals(that.fechaEntrada);
    }

    @Override
    public int hashCode() {
        int result = paciente.hashCode();
        result = 31 * result + fechaEntrada.hashCode();
        return result;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtfFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dtfHora = DateTimeFormatter.ofPattern("HH:mm");
        String result = String.format("%10s", paciente.getSip()) + "\t" +
                String.format("%-24s", paciente.getNombre() + " " + paciente.getApellido1()) +
                String.format("%-14s", fechaEntrada.format(dtfFecha)) +
                String.format("%-6s", fechaEntrada.format(dtfHora)) +
                String.format("%4d", paciente.getEdad()) + "\t" +
                String.format("%-28s", sintomatologia);
        if (preRev != null) {
            result += String.format("%5.1f", preRev[TEMPERATURA]) +
                    String.format("%5.0f", preRev[PPM]) +
                    String.format("%5.0f", preRev[TENSION_SISTOLICA]) +
                    String.format("%5.0f", preRev[TENSION_DIASTOLICA]) + "\t";
        }
        if (fechaAlta != null) {
            result += String.format("%-14s", fechaAlta.format(dtfFecha)) +
                    String.format("%-8s", fechaAlta.format(dtfHora)) +
                    String.format("%-12s", motivoAlta);
        }
        return result;
    }
}
