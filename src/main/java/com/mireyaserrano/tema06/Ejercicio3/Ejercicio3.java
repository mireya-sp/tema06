package com.mireyaserrano.tema06.Ejercicio3;

import com.mireyaserrano.tema06.utils.Ansi;
import com.mireyaserrano.tema06.utils.Lib;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ejercicio3 {

    private static final int MAX_ALUMNOS = 100;
    private static final int MAX_GRUPOS = 10;
    private final Scanner lector;
    private final CentroEducativo centroEducativo;

    public Ejercicio3() {
        lector = new Scanner(System.in);
        int opcion;
        centroEducativo = new CentroEducativo("46000001", MAX_ALUMNOS, MAX_GRUPOS);
        do {
            opcion = menuPrincipal();
            switch (opcion) {
                case 1 -> nuevoAlumno();
                case 2 -> bajaAlumno();
                case 3 -> consultas();
                case 0 -> System.out.println("Hasta pronto!");
            }
        } while (opcion != 0);
    }

    private void nuevoAlumno() {
        boolean validado = false;
        int nia;
        String nombre;
        String apellidos;
        String fechaNacimientoString;
        LocalDate fechaNacimiento = null;
        Grupo grupo = null;
        long telefono;
        Ansi.clearScreen();
        System.out.println("*** NUEVO ALUMNO ***" );
        do {
            System.out.println("Nia: ");
            nia = Integer.parseInt(lector.nextLine());
            validado = nia > CentroEducativo.MIN_NIA && nia < CentroEducativo.MAX_NIA;
            if (!validado) {
                System.out.printf("Nia debe estar comprendido en el rango [%d, %d]\n", CentroEducativo.MIN_NIA, CentroEducativo.MAX_NIA);
                Lib.pause();
                continue;
            }
            /* Comprobamos que dicho NIA no esté duplicado */
            validado = centroEducativo.buscarAlumnoPorNia(String.valueOf(nia)) == null;
            if (!validado) {
                System.out.println("El nia introducido ya corresponde a un alumno.");
                System.out.println("Introduzca otro por favor");
                Lib.pause();
            }
        } while (!validado);

        do {
            System.out.println("Nombre: ");
            nombre = lector.nextLine();
            validado = nombre.length() > 2;
            if (!validado) {
                System.out.println("Nombre debe tener almenos 2 caracteres");
                Lib.pause();
            }
        } while (!validado);

        do {
            System.out.println("Apellidos: ");
            apellidos = lector.nextLine();
            validado = apellidos.length() > 2;
            if (!validado) {
                System.out.println("Apellidos debe tener almenos 2 caracteres");
                Lib.pause();
            }
        } while (!validado);

        do {
            System.out.println("Fecha nacimiento (dd-mm-yyyy): ");
            fechaNacimientoString = lector.nextLine();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            try {
                fechaNacimiento = LocalDate.parse(fechaNacimientoString, dateTimeFormatter);
                validado = true;
            } catch (DateTimeParseException dtpe) {
                validado = false;
                System.out.println("El formato de la fecha de nacimiento no es válido. Recuerde (dd-mm-yyyy).");
                Lib.pause();
            }
        } while (!validado);

        do {
            mostrarGrupos();
            System.out.println("Código del grupo: ");
            int codigo = Integer.parseInt(lector.nextLine());
            /* Buscamos que el código que ha introducido el usuario corresponde a un grupo válido */
            /* En caso afirmativo lo asignamos */
            grupo = centroEducativo.buscarGrupoPorCodigo(codigo);
            validado = grupo != null;
            if (!validado) {
                System.out.println("El código de grupo indicado corresponde a ningún grupo");
            }
        } while (!validado);

        do {
            System.out.println("Teléfono: ");
            telefono = Long.parseLong(lector.nextLine());
            /* Para facilitar la introducción de datos permitimos poner números pequeños como teléfono */
            validado = telefono > CentroEducativo.MIN_TELEFONO && telefono < CentroEducativo.MAX_TELEFONO;
        } while (!validado);

        // Hemos validado todos los datos, podemos proceder a dar de alta el alumno
        if (centroEducativo.nuevoAlumno(String.valueOf(nia), nombre, apellidos, fechaNacimiento, grupo, String.valueOf(telefono))) {
            System.out.println("Alumno guardado correctamente");
        } else {
            System.out.println("Imposible añadir el alumno.");
            System.out.println("El array de alumnos está lleno");
        }
        Lib.pause();
    }

    private void bajaAlumno() {
        String nia;
        Ansi.clearScreen();
        System.out.println("*** BAJA ALUMNO ***" );
        System.out.println("Nia: ");
        nia = lector.nextLine();
        if (centroEducativo.bajaAlumno(nia)) {
            System.out.println("Alumno borrado correctamente");
        } else {
            System.out.println("No existe ningún alumno con el nia " + nia);
        }
        Lib.pause();
    }

    private void consultas() {
        int opcion;
        do {
            opcion = menuConsultas();
            switch (opcion) {
                case 1 -> {
                    //Por grupo
                    buscarAlumnosPorGrupo();
                    Lib.pause();
                }
                case 2 -> {
                    //Por edad
                    buscarAlumnosPorEdad();
                    Lib.pause();
                }
                case 3 -> {
                    //Por nia
                    buscarAlumnoPorNia();
                    Lib.pause();
                }
                case 4 -> {
                    //Por apellidos
                    buscarAlumnosPorApellidos();
                    Lib.pause();
                }
                case 5 -> {
                    mostrarAlumnos();
                    Lib.pause();
                }
            }
        } while (opcion != 0);
    }

    private void mostrarGrupos() {
        System.out.println("Grupos disponibles");
        System.out.println(centroEducativo.listadoGrupos());
    }

    private void buscarAlumnosPorGrupo() {
        int codigo;
        Alumno[] alumnosGrupo;
        mostrarGrupos();
        System.out.println("Código del grupo: ");
        codigo = Integer.parseInt(lector.nextLine());
        alumnosGrupo = centroEducativo.buscarAlumnosPorGrupo(codigo);
        if (alumnosGrupo != null) {
            for (Alumno alumno : alumnosGrupo) {
                System.out.println(alumno.toString());
            }
        } else {
            System.out.println("No se han encontrado alumnos del grupo con código " + codigo);
        }
    }

    private void buscarAlumnosPorEdad() {
        int edad;
        Alumno[] alumnosEdad;
        System.out.println("Edad: ");
        edad = Integer.parseInt(lector.nextLine());
        alumnosEdad = centroEducativo.buscarAlumnosPorEdad(edad);
        if (alumnosEdad != null) {
            for (Alumno alumno : alumnosEdad) {
                System.out.println(alumno.toString());
            }
        } else {
            System.out.println("No se han encontrado alumnos que tengan " + edad + " años");
        }
    }

    private void buscarAlumnoPorNia() {
        String nia;
        System.out.println("Nia: ");
        nia = lector.nextLine();
        Alumno alumno = centroEducativo.buscarAlumnoPorNia(nia);
        if (alumno != null) {
            System.out.println(alumno);
        } else {
            System.out.println("No se ha encontrado ningún alumno con el nia " + nia);
        }
    }

    private void buscarAlumnosPorApellidos() {
        String apellidos;
        Alumno[] alumnosApellidos;
        System.out.println("Apellidos: ");
        apellidos = lector.nextLine();
        alumnosApellidos = centroEducativo.buscarAlumnosPorApellidos(apellidos);
        if (alumnosApellidos != null) {
            for (Alumno alumno : alumnosApellidos) {
                System.out.println(alumno.toString());
            }
        } else {
            System.out.println("No se han encontrado alumnos que tengan " + apellidos + " como apellidos");
        }
    }

    private void mostrarAlumnos() {
        System.out.println("Alumnos disponibles: ");
        System.out.println(centroEducativo.listadoAlumnos());
    }

    private int menuPrincipal() {
        int opcion = -1;
        do {
            Ansi.clearScreen();
            System.out.println("*********************");
            System.out.println("** GESTIÓN ALUMNOS **");
            System.out.println("*********************");
            System.out.println("1. Nuevo alumno...");
            System.out.println("2. Baja de alumno...");
            System.out.println("3. Consultas...");
            System.out.println("---------------------");
            System.out.println("0. Salir\n");
            System.out.println("Elija una opción: ");
            opcion = Integer.parseInt(lector.nextLine());
            if (opcion < 0 || opcion > 3) {
                System.out.println("Elija una opción del menú [0-3]");
                Lib.pause();
            }
        } while (opcion < 0 || opcion > 3);
        return opcion;
    }

    private int menuConsultas() {
        int opcion = -1;
        do {
            Ansi.clearScreen();
            System.out.println("***************");
            System.out.println("** CONSULTAS **");
            System.out.println("****************");
            System.out.println("1. Por grupo...");
            System.out.println("2. Por edad...");
            System.out.println("3. Por nia...");
            System.out.println("4. Por apellidos...");
            // Aunque no lo pide el ejercicio, añadimos la opción de mostrar todos para facilitar las pruebas
            System.out.println("5. Mostrar todos...");
            System.out.println("----------------");
            System.out.println("0. Volver al menú principal\n");
            System.out.println("Elija una opción: ");
            opcion = Integer.parseInt(lector.nextLine());
            if (opcion < 0 || opcion > 5) {
                System.out.println("Elija una opción del menú [0-5]");
                Lib.pause();
            }
        } while (opcion < 0 || opcion > 5);
        return opcion;
    }
}
