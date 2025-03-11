package com.mireyaserrano.tema06.Ejercicio7;

import com.mireyaserrano.tema06.Config;
import com.mireyaserrano.tema06.utils.Lib;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Locale;

public class CentroSalud {

    private final String codigo;
    private final Paciente[] pacientes;
    private int numeroPacientes;
    private final AtencionPaciente[] historial;
    private int numeroRegistros;
    private int lastSIP = 10103001;
    private static final String[] arraySintomatologia = {"Dolor abdominal", "Mareo generalizado", "Dolor fuerte en el pecho", "Dolor en el brazo", "Dolor en la pierna", "Gripe", "Resfriado común", "Escalofríos", "Migraña intensa", "Fuerte contusión"};

    public CentroSalud (String codigo, int maxPacientes, int maxHistorial) {
        this.codigo = codigo;
        this.pacientes = new Paciente[maxPacientes];
        this.historial = new AtencionPaciente[][maxHistorial];
        this.numeroPacientes = 0;
        this.numeroRegistros = 0;
        if (Config.DEBUG) {
            generarDatosAleatorios(80, 100);
        }
    }

    /**
     * Genera datos para realizar pruebas
     * @param numeroPacientes Cantidad de pacientes a generar
     * @param numeroRegistrosHistorial Cantidad de registros de atenciones
     */
    private void generarDatosAleatorios(int numeroPacientes, int numeroRegistrosHistorial) {
        generarPacientesAleatorios(numeroPacientes);
        generarRegistrosHistorialAleatorios(numeroRegistrosHistorial);
        ordenarHistorial();
    }

    /**
     * Genera la cantidad indicada de pacientes aleatorios
     * @param nPacientes Cantidad de pacientes a generar
     */
    private void generarPacientesAleatorios(int nPacientes) {
        Faker faker = new Faker(new Locale("es"));
        for (int i = 0; i < nPacientes; i++) {
            String sip = String.valueOf(++lastSIP);
            String nombre = faker.name().firstName();
            String apellido1 = faker.name().lastName();
            String apellido2 = faker.name().lastName();
            Paciente.Sexo sexo = Lib.random(0, 1) == 0 ? Paciente.Sexo.M : Paciente.Sexo.V;
            LocalDate fechaNacimiento = faker.date().birthdayLocalDate(1, 99);
            pacientes[i] = new Paciente(sip, nombre, apellido1, apellido2, sexo, fechaNacimiento);
            this.numeroPacientes++;
        }
    }

    /**
     * Genera registros de atenciones médicas aleatorias
     * @param nRegistrosHistorial Cantidad de registros a generar
     */
    private void generarRegistrosHistorialAleatorios(int nRegistrosHistorial) {
        int restantes = nRegistrosHistorial;
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        int anyoActual = fechaHoraActual.getYear();
        int mesActual = fechaHoraActual.getMonthValue();
        int diaActual = fechaHoraActual.getDayOfMonth();
        while (restantes > 0) {
            int i = 0;
            while (i < numeroPacientes && restantes > 0) {
                int dia = Lib.random(1, diaActual);
                int hora = Lib.random(0,23);
                int minutos = Lib.random(0,59);
                LocalDateTime fechaEntrada = LocalDateTime.of(anyoActual, mesActual, diaActual, hora, minutos);
                String sintomatologia = arraySintomatologia[Lib.random(0,arraySintomatologia.length-1)];
                float temperatura = Lib.random(36.5f, 41);
                float ppm = Lib.random(50, 120);
                float tenSis = Lib.random(105, 180);
                float tenDias = Lib.random(45, 105);
                // Si el paciente no ha entrado hoy suponemos que se le ha dado el alta
                if (dia != diaActual) {
                    LocalDateTime fechaAlta = LocalDateTime.of(fechaEntrada.toLocalDate(), fechaEntrada.toLocalTime());
                    fechaAlta = fechaAlta.plusHours(Lib.random(1, 12));
                    fechaAlta = fechaAlta.plusMinutes(Lib.random(2, 59));

                    int mAlta = Lib.random(1,100);
                    AtencionPaciente.MotivoAlta motivoAlta;
                    if (mAlta <= 75) { // El 75% mejoran
                        motivoAlta = AtencionPaciente.MotivoAlta.MEJORA;
                    } else if (mAlta <= 99) { // El 24% (99 - 75) se les deriva al hospital
                        motivoAlta = AtencionPaciente.MotivoAlta.DERIVACION_HOSPITAL;
                    } else { // El 1% restante muere
                        motivoAlta = AtencionPaciente.MotivoAlta.DEFUNCION;
                    }
                    historial[numeroRegistros] = new AtencionPaciente(pacientes[i], sintomatologia, fechaEntrada, temperatura, ppm, tenSis, tenDias, fechaAlta, motivoAlta);
                } else { // El paciente continua en el centro de salud
                    historial[numeroRegistros] = new AtencionPaciente(pacientes[i], sintomatologia);
                    boolean atendido = Lib.random(0, 1) != 0;
                    if (atendido) {
                        historial[numeroRegistros].atender(temperatura, ppm, tenSis, tenDias);
                    }
                }
                i++;
                numeroRegistros++;
                restantes--;
            }
        }
    }

    public String getCodigo() {
        return codigo;
    }

    /**
     * Busca las atenciones realizadas al paciente con el sip indicado
     * @param sip SIP del paciente
     * @return Array con las atenciones del paciente o null
     */
    public AtencionPaciente[] buscarHistoricoPorSip(String sip) {
        int registrosPaciente = 0;
        AtencionPaciente[] historicoPaciente = null;
        for (int i = 0; i < numeroRegistros; i++) {
            if (historial[i].getPaciente().getSip().equals(sip)) {
                registrosPaciente++;
            }
        }
        if (registrosPaciente > 0) {
            historicoPaciente = new AtencionPaciente[][registrosPaciente];
            int cont = 0;
            // Una vez sabemos el número de registros del paciente los asignamos al array
            for (int i = 0; i < numeroRegistros; i++) {
                if (historial[i].getPaciente().getSip().equals(sip)) {
                    historicoPaciente[cont] = historial[i];
                    cont++;
                }
            }
        }
        return historicoPaciente;
    }

    /**
     * Busca el paciente con el sip indicado
     * @param sip SIP del paciente a buscar
     * @return Posición del paciente si lo encuentra ó -1
     */
    public int buscarPacientePorSip(String sip) {
        for (int i = 0; i < numeroPacientes; i++) {
            if (pacientes[i].getSip().equals(sip)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca las atenciones médicas realizadas entre la fecha de inicio y fecha fin indicadas
     * @param fechaInicio Fecha a partir de la que se desea realizar la búsqueda
     * @param fechaFin Fecha hasta la cual se desea realizar la búsqueda
     * @return Array de atenciones existentes entre las fechas indicadas o null
     */
    public AtencionPaciente[] buscarHistoricoPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        int registrosFechas = 0;
        AtencionPaciente[] historicoFechas = null;
        if (fechaFin == null) {
            fechaFin = LocalDateTime.now();
        }
        LocalDateTime fechaEntrada;
        for (int i = 0; i < numeroRegistros; i++) {
            fechaEntrada = historial[i].getFechaEntrada();
            if (fechaEntrada.isEqual(fechaInicio) || fechaEntrada.isAfter(fechaInicio) &&
                    fechaEntrada.isEqual(fechaFin) || fechaEntrada.isBefore(fechaFin)) {
                registrosFechas++;
            }
        }
        if (registrosFechas > 0) {
            historicoFechas = new AtencionPaciente[][registrosFechas];
            int cont = 0;
            // Una vez sabemos el número de registros del paciente los asignamos al array
            for (int i = 0; i < numeroRegistros; i++) {
                fechaEntrada = historial[i].getFechaEntrada();
                if (fechaEntrada.isEqual(fechaInicio) || fechaEntrada.isAfter(fechaInicio) &&
                        fechaEntrada.isEqual(fechaFin) || fechaEntrada.isBefore(fechaFin)) {
                    historicoFechas[cont] = historial[i];
                    cont++;
                }
            }
        }
        return historicoFechas;
    }

    /**
     * Ordena el historial de atenciones médicas por fecha
     */
    private void ordenarHistorial() {
        boolean hayCambios = true;
        while (hayCambios) {
            hayCambios = false;
            for (int i = 0; i < numeroRegistros - 1; i++) {
                // Si el elemento actual es mayor que el elemento siguiente los intercambiamos
                // if (historial[i].getFechaEntrada().getTimeInMillis() > historial[i+1].getFechaEntrada().getTimeInMillis()) {
                if (historial[i].getFechaEntrada().isAfter(historial[i+1].getFechaEntrada())) {
                    intercambio(historial, i, i + 1);
                    hayCambios = true;
                }
            }
        }
    }

    /**
     * Intercambia dos posiciones del array
     * @param array Array de Atenciones médicas
     * @param i Índice del primer elemento a intercambiar
     * @param j Índice del segundo elemento a intercambiar
     */
    private void intercambio(AtencionPaciente[] array, int i, int j) {
        AtencionPaciente aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }

    /**
     * Crea un paciente con los datos indicados
     * @param sip SIP del paciente
     * @param nombre Nombre del paciente
     * @param apellido1 Primer apellido
     * @param apellido2 Segundo apellido
     * @param sexo M (Mujer) ó V (Varón)
     * @param fechaNacimiento Fecha de nacimiento del paciente
     * @return La posición donde se ha insertado el paciente creado
     */
    public int nuevoPaciente(String sip, String nombre, String apellido1, String apellido2, Paciente.Sexo sexo, LocalDate fechaNacimiento) {
        this.pacientes[numeroPacientes] = new Paciente(sip, nombre, apellido1, apellido2, sexo, fechaNacimiento);
        // Utilizamos post incremento para que el return devuelva la posición donde lo ha insertado
        return numeroPacientes++;
    }

    /**
     * Añade un nuevo registro de Atención médica con los datos indicados
     * @param paciente Paciente a registrar
     * @param sintomatologia Síntomas del paciente
     * @return La posición donde se ha insertado la atención médica creada.
     */
    public int registrarPaciente(Paciente paciente, String sintomatologia) {
        historial[numeroRegistros] = new AtencionPaciente(paciente, sintomatologia);
        // Utilizamos post incremento para que el return devuelva la posición donde lo ha insertado
        return numeroRegistros++;
    }

    /**
     * Obtiene el registro de atención médica actual de un paciente
     * El paciente aún permanece en el Centro de Salud ya que no se le ha dado el alta
     * @param sip SIP del paciente
     * @return AtencionMedica
     */
    public AtencionPaciente obtenerRegistroAtencionPaciente(String sip) {
        for (int i = 0; i < numeroRegistros; i++) {
            Paciente paciente = historial[i].getPaciente();
            if (paciente.getSip().equals(sip) && historial[i].getFechaAlta() == null) {
                return historial[i];
            }
        }
        return null;
    }

    /**
     * Atiende al paciente indicado registrando sus constantes vitales
     * @param sip SIP del paciente
     * @param temperatura Temperatura en grados centígrados
     * @param ppm Pulsaciones por minuto
     * @param tenSis Tensión sistólica
     * @param tenDias Tensión diastólica
     * @return true si el paciente ha podido ser atendido o false en caso contrario
     */
    public boolean atenderPaciente(String sip, float temperatura, float ppm, float tenSis, float tenDias) {
        AtencionPaciente atencionMedica = obtenerRegistroAtencionPaciente(sip);
        if (atencionMedica != null) {
            return atencionMedica.atender(temperatura, ppm, tenSis, tenDias);
        }
        return false;
    }

    /**
     * Obtiene la media de todas las temperaturas existentes en el historial
     * @return float con la media total
     */
    public float mediaTemperatura() {
        float sumaTemperaturas = 0;
        for (int i = 0; i < numeroRegistros; i++) {
            sumaTemperaturas += historial[i].getTemperatura();
        }
        return sumaTemperaturas / numeroRegistros;
    }

    /**
     * Obtiene la media de pulsaciones por minuto existentes en el historial
     * @return float con la media
     */
    public float mediaPpm() {
        float sumaPpm = 0;
        for (int i = 0; i < numeroRegistros; i++) {
            sumaPpm += historial[i].getPpm();
        }
        return sumaPpm / numeroRegistros;
    }

    /**
     * Obtiene la media de tensiones sistólicas existentes en el historial
     * @return float con la media
     */
    public float mediaTenSis() {
        float sumaTenSis = 0;
        for (int i = 0; i < numeroRegistros; i++) {
            sumaTenSis += historial[i].getTensionSistolica();
        }
        return sumaTenSis / numeroRegistros;
    }

    /**
     * Obtiene la media de tensiones diastólicas existentes en el historial
     * @return float con la media
     */
    public float mediaTenDias() {
        float sumaTenDias = 0;
        for (int i = 0; i < numeroRegistros; i++) {
            sumaTenDias += historial[i].getTensionDiastolica();
        }
        return sumaTenDias / numeroRegistros;
    }

    /**
     * Obtiene la media de las constantes vitales existentes en el historial
     * @return Array con las medias de las constantes vitales
     */
    public float[] mediaPreRev() {
        float[] mediasPreRev = new float[4];
        for (int i = 0; i < numeroRegistros; i++) {
            for (int j = 0; j < mediasPreRev.length; j++) {
                mediasPreRev[j] += historial[i].getPreRev()[j];
            }

        }
        for (int j = 0; j < mediasPreRev.length; j++) {
            mediasPreRev[j] /= numeroRegistros;
        }
        return mediasPreRev;
    }

    /**
     * Obtiene la edad media de los pacientes que han sido o están siendo atendidos en el último mes
     * @return float con la media
     */
    public float mediaEdadPacientes() {
        float sumaEdad = 0;
        for (int i = 0; i < numeroPacientes; i++) {
            sumaEdad += pacientes[i].getEdad();
        }
        return sumaEdad / numeroPacientes;
    }

    /**
     * Obtiene el porcentaje de mujeres que han sido o están siendo atendidas en el último mes
     * @return float con el porcentaje de mujeres
     */
    public float porcentajeMujeres() {
        float cantidadMujeres = 0;
        for (int i = 0; i < numeroPacientes; i++) {
            if (pacientes[i].getSexo() == Paciente.Sexo.M) {
                cantidadMujeres++;
            }
        }
        return (cantidadMujeres / numeroPacientes) * 100;
    }

    /**
     * Obtiene el porcentaje de hombres que han sido o están siendo atendidos en el último mes
     * @return float con el porcentaje de hombres
     */
    public float porcentajeHombres() {
        float cantidadHombres = 0;
        for (int i = 0; i < numeroPacientes; i++) {
            if (pacientes[i].getSexo() == Paciente.Sexo.V) {
                cantidadHombres++;
            }
        }
        return (cantidadHombres / numeroPacientes) * 100;
    }

    public Paciente[] getPacientes() {
        return this.pacientes;
    }

    public AtencionPaciente[] getHistorial() {
        return this.historial;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof CentroSalud that)) return false;

        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    @Override
    public String toString() {
        return "CentroSalud{" +
                "codigo='" + codigo + '\'' +
                ", pacientes=" + Arrays.toString(pacientes) +
                ", historial=" + Arrays.toString(historial) +
                '}';
    }


}
