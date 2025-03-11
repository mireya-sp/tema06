package com.mireyaserrano.tema06.pilasycolas.ejercicio04;

import com.mireyaserrano.tema06.pilasycolas.ejercicio03.PilaString;

public class HtmlParser {
    public enum Estado {
        TEXTO, DECLARACION, PROCESANDO_ETIQUETA, BUSCANDO_FIN_DECLARACION, CIERRE_ETIQUETA
    }
    private final String texto;
    /** Estructura interna pila para determinar almacenar el orden de las etiquetas de apertura */
    private final PilaString pila;
    /** Estado del procesado del documento */
    private Estado estado;
    /** Contiene el mensaje de error en caso que el documento no esté bien formado */
    private String errorMessage;

    /**
     * Crea un objeto HtmlParser para determinar si el código html está bien formado
     * @param texto Cadena de texto que contiene el código html a evaluar
     */
    public HtmlParser(String texto) {
        // Eliminamos todos los espacios en blanco
        this.texto = texto.replaceAll("\\s+", " ");
        this.pila = new PilaString();
        this.errorMessage = "";
    }

    /**
     * Procesa el texto html y determina si el orden de apertura y cierre de etiquetas es correcto
     * @return true si el documento html está bien formado, false en caso contrario
     */
    public boolean parse() {
        // En este caso implementamos el parseado de forma totalmente manual.
        // Una implementación más sencilla podría hacerse utilizando StringTokenizer
        // que ofrece métodos para obtener partes del documento (tokens) a partir de patrones.
        // De esta forma, se simplificaría el procesado de etiquetas
        // Otra opción incluso más sencilla es utilizar clases como Jsoup que ya hacen el parseado automáticamente
        int i = 0;
        estado = Estado.TEXTO;
        boolean procesandoAtributo = false;
        StringBuilder aperturaEtiqueta = new StringBuilder();
        StringBuilder cierreEtiqueta = new StringBuilder();
        while (i < texto.length()) {
            char c = texto.charAt(i);
            switch (estado) {
                case TEXTO:
                    if (c == '<') {
                        estado = Estado.DECLARACION;
                    }
                    break;
                case DECLARACION:
                    if (Character.isLetter(c)) {
                        estado = Estado.PROCESANDO_ETIQUETA;
                        aperturaEtiqueta.append(c);
                    } else if (c == '/') {
                        estado = Estado.CIERRE_ETIQUETA;
                    } else if (c == '<') {
                        declaracionInesperada(i);
                        return false;
                    } else if (c == '>') {
                        cierreDeclaracionInesperada(i);
                    }
                    break;
                case PROCESANDO_ETIQUETA:
                    if (Character.isLetter(c)) {
                        aperturaEtiqueta.append(c);
                    } else if (c == '/') { // Etiqueta con autocierre
                        aperturaEtiqueta.setLength(0); // borramos la etiqueta
                        estado = Estado.CIERRE_ETIQUETA;
                    } else if (c == '>') { // Fin definición de la etiqueta
                        // Procesar etiqueta
                        procesarEtiqueta(aperturaEtiqueta);
                    } else if (Character.isSpaceChar(c)) {
                        // Aquí acaba el nombre de la etiqueta
                        estado = Estado.BUSCANDO_FIN_DECLARACION;
                    } else if (c == '<') {
                        declaracionInesperada(i);
                        return false;
                    }
                    break;
                case BUSCANDO_FIN_DECLARACION:
                    if (c == '/') {
                        if (!procesandoAtributo) { // Etiqueta con autocierre
                            aperturaEtiqueta.setLength(0); // borramos la etiqueta
                            estado = Estado.CIERRE_ETIQUETA;
                        }
                    } else if (c == '<') {
                        declaracionInesperada(i);
                        return false;
                    } else if (c == '>') { // Fin declaración encontrado
                        // Procesar etiqueta
                        procesarEtiqueta(aperturaEtiqueta);
                    } else if (c == '"') {
                        procesandoAtributo = !procesandoAtributo;
                    }
                    break;
                case CIERRE_ETIQUETA:
                    if (Character.isLetter(c)) {
                        cierreEtiqueta.append(c);
                    } else if (c == '>') { // Fin declaración
                        if (cierreEtiqueta.length() > 0) {
                            String aperturaAnterior = pila.pop();
                            if (!aperturaAnterior.equals(cierreEtiqueta.toString())) {
                                errorMessage = "Se esperaba </" + aperturaAnterior + "> pero </" +
                                        cierreEtiqueta + "> fue encontrado en posición " + i + "\n";
                                // errorMessage += texto + "\n\n";
                                errorMessage += pila;
                                return false;

                            }
                        }
                        cierreEtiqueta.setLength(0);
                        estado = Estado.TEXTO;
                    }
                    break;
            }
            i++;
        }
        return pila.isEmpty();
    }

    private void procesarEtiqueta(StringBuilder etiqueta) {
        if (!etiqueta.toString().equalsIgnoreCase("doctype")) {
            pila.push(etiqueta.toString());
        }
        etiqueta.setLength(0);
        estado = Estado.TEXTO;
    }

    private void declaracionInesperada(int pos) {
        errorMessage = "Se esperaba una definición de etiqueta y se encontró otra apertura '<' en posición " + pos + "\n";
        errorMessage += texto;
    }

    private void cierreDeclaracionInesperada(int pos) {
        errorMessage = "Se esperaba una definición de etiqueta y se encontró un cierre '<' inesperado en posición " + pos + "\n";
        errorMessage += texto;
    }

    /**
     * Obtiene los mensajes de error
     * @return Cadena de texto con los mensajes de error
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}
