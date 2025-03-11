package com.mireyaserrano.tema06.Ejercicio7;

import com.mireyaserrano.tema06.utils.Lib;
import com.mireyaserrano.tema06.utils.Ansi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ejercicio7 {

    public static final int MAX_PACIENTES = 500;
    public static final int MAX_REGISTROS_HISTORICO = 2000;
    private final Scanner lector;
    private final CentroSalud centro;

    public Ejercicio7() {
        lector = new Scanner(System.in);
        int opcion;
        centro = new CentroSalud("CS01", MAX_PACIENTES, MAX_REGISTROS_HISTORICO);
        do {
            opcion = menuPrincipal();
            switch (opcion) {
                case 1 -> {
                    nuevoPaciente();
                    Lib.pause();
                }
                case 2 -> {
                    atenderPaciente();
                    Lib.pause();
                }
                case 3 -> {
                    consultas();
                    Lib.pause();
                }
                case 4 -> {
                    altaMedica();
                    Lib.pause();
                }
                case 0 -> {
                    System.out.println("Hasta pronto!");
                }
            }
        } while (opcion != 0);

        lector.close();
    }

    /**
     * Solicita y valida los datos para el alta médica de un paciente
     */
    private void altaMedica() {
        String sip;
        int i = 1;
        boolean validado;
        int opcion;
        AtencionPaciente atencionPaciente;
        System.out.println("Sip: ");
        sip = lector.nextLine();
        atencionPaciente = centro.obtenerRegistroAtencionPaciente(sip);
        if (atencionPaciente != null) {
            do {
                System.out.println("*****************");
                System.out.println("** MOTIVO ALTA **");
                System.out.println("*****************");
                for (AtencionPaciente.MotivoAlta motivoAlta : AtencionPaciente.MotivoAlta.values()) {
                    System.out.println(i + ". " + motivoAlta);
                    i++;
                }
                System.out.print("\nElija un motivo: ");
                opcion = Integer.parseInt(lector.nextLine());
                validado = opcion >= 1 && opcion <= AtencionPaciente.MotivoAlta.values().length;
                if (!validado) {
                    System.out.println("Elija una opción del menú");
                }
            } while (!validado);
            if (atencionPaciente.darAlta(AtencionPaciente.MotivoAlta.fromInteger(opcion-1))) {
                System.out.println("El paciente ha sido de alta correctamente");
            } else {
                System.out.println("No se ha podido dar de alta al paciente");
            }
        } else {
            System.out.println("No se ha encontrado el paciente con sip " + sip);
        }
    }

    /**
     * Solicita y valida las constantes vitales de un paciente
     */
    private void atenderPaciente() {
        String sip;
        boolean validado;
        AtencionPaciente atencionPaciente;
        System.out.println("Sip: ");
        sip = lector.nextLine();
        atencionPaciente = centro.obtenerRegistroAtencionPaciente(sip);
        if (atencionPaciente != null) {
            float temperatura;
            float ppm;
            float tenSis;
            float tenDias;
            do {
                System.out.println("Temperatura: ");
                temperatura = Float.parseFloat(lector.nextLine());
                validado = temperatura >= AtencionPaciente.MIN_TEMPERATURA && temperatura <= AtencionPaciente.MAX_TEMPERATURA;
                if (!validado) {
                    System.out.println("Comprueba el rango de temperaturas [" + AtencionPaciente.MIN_TEMPERATURA + "-" + AtencionPaciente.MAX_TEMPERATURA + "] ");
                }
            } while (!validado);
            do {
                System.out.println("Pulsaciones por minuto: ");
                ppm = Float.parseFloat(lector.nextLine());
                validado = ppm >= AtencionPaciente.MIN_PPM && ppm <= AtencionPaciente.MAX_PPM;
                if (!validado) {
                    System.out.println("Comprueba el rango de ppm [" + AtencionPaciente.MIN_PPM + "-" + AtencionPaciente.MAX_PPM + "] ");
                }
            } while (!validado);
            do {
                System.out.println("Tensión sistólica: ");
                tenSis = Float.parseFloat(lector.nextLine());
                validado = tenSis >= AtencionPaciente.MIN_TENSION_SISTOLICA && tenSis <= AtencionPaciente.MAX_TENSION_SISTOLICA;
                if (!validado) {
                    System.out.println("Comprueba el rango de la tensión sistólica [" +
                            AtencionPaciente.MIN_TENSION_SISTOLICA + "-" + AtencionPaciente.MAX_TENSION_SISTOLICA + "] ");
                }
            } while (!validado);
            do {
                System.out.println("Tensión diastólica: ");
                tenDias = Float.parseFloat(lector.nextLine());
                validado = tenDias >= AtencionPaciente.MIN_TENSION_DIASTOLICA && tenDias <= AtencionPaciente.MAX_TENSION_DIASTOLICA;
                if (!validado) {
                    System.out.println("Comprueba el rango de la tensión diastólica [" +
                            AtencionPaciente.MIN_TENSION_DIASTOLICA + "-" + AtencionPaciente.MAX_TENSION_DIASTOLICA + "] ");
                }
            } while (!validado);
            if (centro.atenderPaciente(sip, temperatura, ppm, tenSis, tenDias)) {
                System.out.println("Paciente atendido correctamente");
            } else {
                System.out.println("El paciente no ha podido ser atendido");
            }
        } else {
            System.out.println("El paciente con sip " + sip + " no está registrado en el sistema");
        }
    }

    /**
     * Solicita y valida los datos para crear un nuevo paciente
     */
    private void nuevoPaciente() {
        boolean validado;
        String sip;
        int pos;
        String sintomatologia;

        Ansi.clearScreen();
        System.out.println("**********************");
        System.out.println("*** NUEVO PACIENTE ***" );
        System.out.println("**********************");
        do {
            System.out.println("Sip: ");
            sip = lector.nextLine();
            // Aquí iría la validación
            try {
                validado = sip.length() == 8;
                if (validado) {
                    Integer.parseInt(sip);
                } else {
                    System.out.println("El sip debe tener 8 dígitos");
                    Lib.pause();
                }
            } catch (NumberFormatException nfe) {
                validado = false;
                System.out.println("El sip sólo puede contener valores numéricos");
                Lib.pause();
            }
        } while (!validado);

        pos = centro.buscarPacientePorSip(String.valueOf(sip));
        if (pos < 0) {
            System.out.println("No existe ningún paciente registrado con el sip " + sip);
            System.out.println("¿Desea registrarlo ahora?(s/n): ");
            boolean registrar = lector.nextLine().toLowerCase().charAt(0) == 's';
            if (registrar) {
                String nombre;
                String apellido1;
                String apellido2;
                Paciente.Sexo sexo = null;
                String fechaNacimientoStr;
                LocalDate fechaNacimiento = null;
                do {
                    System.out.println("Nombre: ");
                    nombre = lector.nextLine();
                    validado = nombre.length() >= 2;
                    if (!validado) {
                        System.out.println("Nombre debe tener almenos 2 caracteres");
                    }
                } while (!validado);
                do {
                    System.out.println("Apellido1: ");
                    apellido1 = lector.nextLine();
                    validado = apellido1.length() >= 2;
                    if (!validado) {
                        System.out.println("Apellido1 debe tener almenos 2 caracteres");
                    }
                } while (!validado);
                do {
                    System.out.println("Apellido2: ");
                    apellido2 = lector.nextLine();
                    validado = apellido2.length() >= 2;
                    if (!validado) {
                        System.out.println("Apellido2 debe tener almenos 2 caracteres");
                    }
                } while (!validado);
                do {
                    System.out.println("Sexo ([V-M]: ");
                    char s = lector.nextLine().toLowerCase().charAt(0);
                    if (s == 'v') {
                        sexo = Paciente.Sexo.V;
                        validado = true;
                    } else if (s == 'm') {
                        sexo = Paciente.Sexo.M;
                        validado = true;
                    } else {
                        validado = false;
                        System.out.println("Debe indicar 'V' ó 'M'");
                    }
                } while (!validado);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                do {
                    System.out.println("Fecha de nacimiento [dd/mm/yyyy]: ");
                    fechaNacimientoStr = lector.nextLine();
                    try {
                        fechaNacimiento = LocalDate.parse(fechaNacimientoStr, dtf);
                        LocalDate fechaActual = LocalDate.now();
                        validado = fechaNacimiento.isEqual(fechaActual) || fechaNacimiento.isBefore(fechaActual);
                        if (!validado) {
                            System.out.println("La fecha de nacimiento no puede ser posterior a la fecha actual");
                        }
                    } catch (DateTimeParseException dtpe) {
                        validado = false;
                        System.out.println("Debe introducir la fecha en formato dd/mm/yyyy");
                    }

                } while (!validado);
                pos = centro.nuevoPaciente(sip, nombre, apellido1, apellido2, sexo, fechaNacimiento);
                if (pos >= 0) {
                    System.out.println("Se ha creado el paciente: ");
                }
            } else { // Si el usuario no quiere registrar al paciente salimos
                return;
            }
        }
        // Mostramos el paciente
        System.out.println(centro.getPacientes()[pos].toString());
        do {
            System.out.println("Indique los síntomas del paciente: ");
            sintomatologia = lector.nextLine();
            validado = sintomatologia.length() > 5;
            if (!validado) {
                System.out.println("Por favor, explique mejor los síntomas del paciente");
            }
        } while (!validado);

        if (centro.registrarPaciente(centro.getPacientes()[pos], sintomatologia) >= 0) {
            System.out.println("Paciente registrado correctamente");
            System.out.println("El paciente debe esperar en la sala de espera");
        } else {
            System.out.println("Se ha producido un error al registrar el paciente");
        }
    }

    /**
     * Muestra el menú principal de la aplicación
     * @return int con la opción elegida por el usuario
     */
    private int menuPrincipal() {
        int opcion;
        do {
            Ansi.clearScreen();
            System.out.println("*********************");
            System.out.println("**    URGENCIAS    **");
            System.out.println("*********************");
            System.out.println("1. Nuevo paciente...");
            System.out.println("2. Atender paciente...");
            System.out.println("3. Consultas...");
            System.out.println("4. Alta médica...");
            System.out.println("---------------------");
            System.out.println("0. Salir\n");
            System.out.println("Elija una opción: ");
            opcion = Integer.parseInt(lector.nextLine());
            if (opcion < 0 || opcion > 4) {
                System.out.println("Elija una opción del menú [0-4]");
                Lib.pause();
            }

        } while (opcion < 0 || opcion > 4);
        return opcion;
    }

    /**
     * Muestra el submenú de consultas
     * @return int la opción del submenú elegida por el usuario
     */
    private int menuConsultas() {
        int opcion;
        do {
            Ansi.clearScreen();
            System.out.println("*********************");
            System.out.println("**    CONSULTAS    **");
            System.out.println("*********************");
            System.out.println("1. Por Sip...");
            System.out.println("2. Por fechas...");
            System.out.println("3. Estadísticas...");
            System.out.println("4. Histórico mensual...");
            System.out.println("------------------------");
            System.out.println("0. Volver al menú principal\n");
            System.out.println("Elija una opción: ");
            opcion = Integer.parseInt(lector.nextLine());
            if (opcion < 0 || opcion > 4) {
                System.out.println("Elija una opción del menú [0-4]");
                Lib.pause();
            }
        } while (opcion < 0 || opcion > 4);
        return opcion;
    }

    /**
     * Ejecuta la opción del submenú consultas elegida por el usuario
     */
    private void consultas() {
        int opcion;
        do {
            opcion = menuConsultas();
            switch (opcion) {
                case 1 -> consultaPorSip();
                case 2 -> consultaPorFechas();
                case 3 -> mostrarEstadisticas();
                case 4 -> mostrarHistorico();
            }
        } while (opcion != 0);
    }

    /**
     * Muestra el histórico de atenciones a pacientes
     */
    private void mostrarHistorico() {
        int i;
        i = 0;
        boolean fin = false;
        AtencionPaciente[] historial = centro.getHistorial();
        while (i < historial.length && !fin) {
            if (historial[i]!= null) {
                System.out.println(historial[i].toString());
                i++;
            } else {
                fin = true;
            }
        }
        Lib.pause();
    }

    /**
     * Muestra las estadísticas
     */
    private void mostrarEstadisticas() {
        // Podríamos haber implementado un método que calculara todas las medias con una sola pasada de bucle
        // pero cada uno por separado queda más claro
        float[] stats = centro.mediaPreRev();
        System.out.printf("Media temperatura: %.2f\n", stats[AtencionPaciente.TEMPERATURA]);
        // También podríamos hacerlo mediante:
        // System.out.printf("Media temperatura: %.2f\n", centro.mediaTemperatura());
        System.out.printf("Media ppm: %.0f\n", stats[AtencionPaciente.PPM]);
        // System.out.printf("Media ppm: %.0f\n", centro.mediaPpm());
        System.out.printf("Media tensión sistólica: %.0f\n", stats[AtencionPaciente.TENSION_SISTOLICA]);
        // System.out.printf("Media tensión sistólica: %.0f", centro.mediaTenSis());
        System.out.printf("Media tensión diastólica: %.0f\n", stats[AtencionPaciente.TENSION_DIASTOLICA]);
        // System.out.printf("Media tensión diastólica: %.0f", centro.mediaTenDias());
        System.out.printf("Media edad pacientes: %.1f\n", centro.mediaEdadPacientes());
        System.out.printf("Porcentaje hombres: %.2f\n", centro.porcentajeHombres());
        System.out.printf("Porcentaje mujeres: %.2f\n", centro.porcentajeMujeres());
        Lib.pause();
    }

    /**
     * Solicita y valida datos para la búsqueda en el histórico por fechas
     */
    private void consultaPorFechas() {
        boolean validado;
        int i;
        System.out.println("Fecha inicio (dd-mm-yyyy): ");
        String fechaInicioStr = lector.nextLine();
        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFin = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            fechaInicio =  LocalDateTime.parse(fechaInicioStr + " 00:00", dateTimeFormatter);
            validado = true;
        } catch (DateTimeParseException dtpe) {
            validado = false;
            System.out.println("El formato de la fecha inicio no es correcto. Recuerde (dd-mm-yyyy).");
            Lib.pause();
        }
        if (validado) {
            System.out.println("Fecha fin (dd-mm-yyyy): ");
            String fechaFinStr = lector.nextLine();
            if (fechaFinStr.isEmpty()) {
                fechaFin = LocalDateTime.now();
            } else {
                try {
                    fechaFin =  LocalDateTime.parse(fechaFinStr + " 23:59", dateTimeFormatter);
                } catch (DateTimeParseException dtpe) {
                    validado = false;
                    System.out.println("El formato de la fecha fin no es correcto. Recuerde (dd-mm-yyyy).");
                    Lib.pause();
                }
            }
            if (validado) {
                AtencionPaciente[] historialFechas = centro.buscarHistoricoPorFechas(fechaInicio, fechaFin);
                if (historialFechas != null) {
                    for (i = 0; i < historialFechas.length; i++) {
                        System.out.println(historialFechas[i].toString());
                    }
                } else {
                    System.out.println("No se han encontrado registros");
                }
            }
        }
        Lib.pause();
    }

    /**
     * Solicita y valida datos para la búsqueda en el histórico por SIP
     */
    private void consultaPorSip() {
        int i;
        String sip;
        System.out.println("Sip: ");
        sip = lector.nextLine();
        AtencionPaciente[] historialSip = centro.buscarHistoricoPorSip(sip);
        if (historialSip != null) {
            for (i = 0; i < historialSip.length; i++) {
                System.out.println(historialSip[i].toString());
            }
        } else {
            System.out.println("No se han encontrado registros");
        }
        Lib.pause();
    }

}
