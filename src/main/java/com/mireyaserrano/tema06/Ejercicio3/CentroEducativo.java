package com.mireyaserrano.tema06.Ejercicio3;

import com.mireyaserrano.tema06.Config;
import com.mireyaserrano.tema06.utils.Lib;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

import net.datafaker.Faker;

public class CentroEducativo {

    public static final int MIN_NIA = 0;
    public static final int MAX_NIA = 1999999;
    public static final int MIN_TELEFONO = 0;
    public static final int MAX_TELEFONO = 999999999;

    private final String codigo;
    private final Alumno[] alumnos;
    private int contadorAlumnos;
    private final Grupo[] grupos;

    public CentroEducativo(String codigo, int maxAlumnos, int maxGrupos) {
        this.codigo = codigo;
        alumnos = new Alumno[maxAlumnos];
        contadorAlumnos = 0;
        grupos = new Grupo[maxGrupos];
        if (Config.DEBUG) {
            generarDatosAleatorios(8);
        }
    }

    /**
     * Crea cantidadAlumnos aleatorios para poder realizar pruebas
     * No confundir con testing
     * @param cantidadAlumnos
     */
    private void generarDatosAleatorios(int cantidadAlumnos) {
        generarGruposAleatorios();
        generarAlumnosAleatorios(cantidadAlumnos);
    }

    /**
     * Crea grupos aleatorios
     */
    private void generarGruposAleatorios() {
        for (int i = 0; i < grupos.length; i++) {
            grupos[i] = new Grupo(i, "GRUPO " + i);
        }
    }

    private void generarAlumnosAleatorios(int nAlumnos) {
        for (int i = 0; i < nAlumnos; i++) {
            alumnos[i] = alumnoAleatorio();
            contadorAlumnos++;
        }
    }

    private Alumno alumnoAleatorio() {
        Faker faker = new Faker(new Locale("ES"));
        String nia = String.valueOf(Lib.random(1000000, 1099999));
        String nombre = faker.name().firstName();
        String apellido1 = faker.name().lastName();
        String apellido2 = faker.name().lastName();
        LocalDate fechaActual = LocalDate.now();
        int anyoActual = fechaActual.getYear();
        LocalDate fechaNacimiento = LocalDate.of(Lib.random(anyoActual - 30, anyoActual - 20), Lib.random(1, 12), Lib.random(1,28));
        Grupo grupo = grupos[Lib.random(0, grupos.length-1)];
        String telefono = String.valueOf(Lib.random(965700000, 965799999));
        return new Alumno(nia, nombre, apellido1 + " " + apellido2, fechaNacimiento, grupo, telefono);
    }

    public String getCodigo() {
        return codigo;
    }

    /**
     * Da de alumno un nuevo alumno con los datos indicados
     * @param nia Nia
     * @param nombre Nombre
     * @param apellidos Los apellidos
     * @param fechaNacimiento Fecha nacimiento
     * @param grupo Grupo
     * @param telefono Teléfono
     * @return True si se ha añadido el alumno, False en caso contrario
     */
    public boolean nuevoAlumno(String nia, String nombre, String apellidos, LocalDate fechaNacimiento, Grupo grupo, String telefono) {
        // En esta implementación (A) sabemos que la primera posición libre el la apuntada por el contadorAlumnos
        if (contadorAlumnos < alumnos.length) {
            alumnos[contadorAlumnos] = new Alumno(nia, nombre, apellidos, fechaNacimiento, grupo, telefono);
            contadorAlumnos++;
            return true;
        }
        return false;
    }

    /**
     * Borra el alumno que tiene el nia indicado
     * @param nia Nia del alumno a borrar
     * @return True si se ha borrado, False en caso contrario
     */
    public boolean bajaAlumno(String nia) {
        int pos = buscarPosicionAlumno(nia);
        if (pos >= 0) {
            alumnos[pos] = alumnos[--contadorAlumnos];
            alumnos[contadorAlumnos] = null;
            return true;
        }
        return false;
    }

    /**
     * Busca un alumno por Nia
     * @param nia
     * @return Si lo encuentra devuelve el alumno, en caso contrario devuelve null
     */
    public Alumno buscarAlumnoPorNia(String nia) {
        int posicion = buscarPosicionAlumno(nia);
        return posicion >= 0 ? alumnos[posicion] : null;
    }

    /**
     * Busca un alumno por Nia
     * @param nia
     * @return Si lo encuentra devuelve su posición, en caso contrario devuelve -1
     */
    private int buscarPosicionAlumno(String nia) {
        int i;
        for (i = 0; i < contadorAlumnos; i++) {
            if (alumnos[i].getNia().equals(nia)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca alumnos por Grupo
     * @param codigoGrupo
     * @return Un array con los alumnos que ha encontrado, null si no ha encontrado
     */
    public Alumno[] buscarAlumnosPorGrupo(int codigoGrupo) {
        Alumno[] alumnosGrupo = null;
        /* Lo ideal sería hacerlo con ArrayLists pero aún no los hemos visto */
        /* Calculamos primero cuantos alumnos hay que coincidan con el criterio de búsqueda */
        int nAlumnos = 0;
        for (int i = 0; i < contadorAlumnos; i++) {
            if (alumnos[i].getGrupo().getCodigo() == codigoGrupo) {
                nAlumnos++;
            }
        }
        if (nAlumnos > 0) {
            alumnosGrupo = new Alumno[nAlumnos];
            int cont = 0;
            /* Una vez sabemos el número de alumnos los asignamos al vector */
            for (int i = 0; i < contadorAlumnos; i++) {
                if (alumnos[i].getGrupo().getCodigo() == codigoGrupo) {
                    alumnosGrupo[cont] = alumnos[i];
                    // si quisiéramos devolver una copia en lugar de los originales haríamos:
                    // alumnosGrupo[cont] = new Alumno(alumnos[i]);
                    cont++;
                }
            }
        }
        return alumnosGrupo;
    }

    /**
     * Busca alumnos por Edad
     * @param edad Edad en años
     * @return Un array con los alumnos que ha encontrado, null si no ha encontrado
     */
    public Alumno[] buscarAlumnosPorEdad(int edad) {
        Alumno[] alumnosEdad = null;
        /* Lo ideal sería hacerlo con ArrayLists pero aún no los hemos visto */
        /* Calculamos primero cuantos alumnos hay que coincidan con el criterio de búsqueda */
        int nAlumnos = 0;
        for (int i = 0; i < contadorAlumnos; i++) {
            if (alumnos[i].getEdad() == edad) {
                nAlumnos++;
            }
        }
        if (nAlumnos > 0) {
            alumnosEdad = new Alumno[nAlumnos];
            int cont = 0;
            /* Una vez sabemos el número de alumnos los asignamos al vector */
            for (int i = 0; i < contadorAlumnos; i++) {
                if (alumnos[i].getEdad() == edad) {
                    alumnosEdad[cont] = alumnos[i];
                    // si quisiéramos devolver una copia en lugar de los originales haríamos:
                    // alumnosEdad[cont] = new Alumno(alumnos[i]);
                    cont++;
                }
            }
        }
        return alumnosEdad;
    }

    /**
     * Busca alumnos por Apellidos
     * @param apellidos
     * @return Un array con los alumnos que ha encontrado, null si no ha encontrado
     */
    public Alumno[] buscarAlumnosPorApellidos(String apellidos) {
        Alumno[] alumnosApellidos = null;
        /* Lo ideal sería hacerlo con ArrayLists pero aún no los hemos visto */
        /* Calculamos primero cuantos alumnos hay que coincidan con el criterio de búsqueda */
        int nAlumnos = 0;
        //Convertimos a minúsculas
        apellidos = apellidos.toLowerCase();
        for (int i = 0; i < contadorAlumnos; i++) {
            if (alumnos[i].getApellidos().toLowerCase().contains(apellidos)) {
                nAlumnos++;
            }
        }
        if (nAlumnos > 0) {
            alumnosApellidos = new Alumno[nAlumnos];
            int cont = 0;
            /* Una vez sabemos el número de alumnos los asignamos al vector */
            for (int i = 0; i < contadorAlumnos; i++) {
                if (alumnos[i].getApellidos().toLowerCase().contains(apellidos)) {
                    alumnosApellidos[cont] = alumnos[i];
                    // si quisiéramos devolver una copia en lugar de los originales haríamos:
                    // alumnosApellidos[cont] = new Alumno(alumnos[i]);
                    cont++;
                }
            }
        }
        return alumnosApellidos;
    }

    /**
     * Busca el Grupo con código indicado
     * @param codigo Código del grupo
     * @return Grupo o null
     */
    public Grupo buscarGrupoPorCodigo(int codigo) {
        /* Buscamos que el código que ha introducido el usuario corresponde a un grupo válido */
        /* En caso afirmativo lo asignamos */
        for (Grupo grupo : grupos) {
            if (grupo.getCodigo() == codigo)
                return grupo;
        }
        return null;
    }

    /**
     * Obtiene el listado de alumnos como String
     * @return String con el listado de alumnos
     */
    public String listadoAlumnos() {
        StringBuilder sb = new StringBuilder();
        // Mostrar todos los alumnos
        for (int i = 0; i < contadorAlumnos; i++) {
            if (alumnos[i] != null) {
                sb.append(alumnos[i].toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Obtiene el listado de grupos como String
     * @return String con el listado de grupos
     */
    public String listadoGrupos() {
        StringBuilder sb = new StringBuilder();
        for (Grupo grupo : grupos) {
            sb.append(grupo.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof CentroEducativo that)) return false;

        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return "CentroEducativoA{" +
                "codigo='" + codigo + '\'' +
                ", alumnos=" + Arrays.toString(alumnos) +
                ", grupos=" + Arrays.toString(grupos) +
                '}';
    }
}
