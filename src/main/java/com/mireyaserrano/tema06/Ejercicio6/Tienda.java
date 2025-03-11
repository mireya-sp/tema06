package com.mireyaserrano.tema06.Ejercicio6;

import com.mireyaserrano.tema06.utils.Lib;

import java.time.LocalDate;
import java.util.GregorianCalendar;

public class Tienda {
    private final Bicicleta[] bicicletas;
    private int numeroReferencias;

    private static final String[] marcas = {"Orbea", "Merida", "Giant", "Cube", "BH", "Lapierre", "MTP", "Trek", "Scott", "BMC", "Canyon", "Look", "Megamo", "Pinarello", "Bianchi", "Factor"};
    private static final String[] modelos = {"Titán", "Obleus", "Morpheus", "Deep", "Runner", "CSC", "Antares", "Cepheid", "Monster"};
    /** ID se utiliza para asegurar que al generar bicicletas aleatorias la referencia sea única **/
    private static int ID = 0;

    public Tienda(int nBicicletas) {
        numeroReferencias = 0;
        bicicletas = new Bicicleta[Config.MAX_BICICLETAS];
        generarDatosAleatorios(nBicicletas);
    }

    private void generarDatosAleatorios(int nBicicletas) {
        for(int i = 0; i < nBicicletas; i++) {
            bicicletas[i] = bicicletaAleatoria();
            numeroReferencias++;
        }
    }

    private Bicicleta bicicletaAleatoria() {
        ID++;
        String referencia = "B-" + String.format("%04d", ID);
        String marca = marcas[Lib.random(0, marcas.length -1)];
        String modelo = modelos[Lib.random(0, modelos.length -1)];
        float peso = Lib.random(2,25f);
        float tamanyo = Lib.random(5,30f);
        boolean motor = Lib.random(0, 1) == 1;
        int currentYear = LocalDate.now().getYear();
        LocalDate fechaFabricacion = LocalDate.of(Lib.random(currentYear - 7, currentYear), Lib.random(1, 12), Lib.random(1,28));
        float precio = Lib.random(99,2500f);
        int stock = Lib.random(0, 7);
        return new Bicicleta(referencia, marca, modelo, peso, tamanyo, motor, fechaFabricacion, precio, stock);
    }

    public ResultadoOperacion anyadirStock(String referencia, int unidades) {
        Bicicleta bicicleta = buscarBicicletaPorReferencia(referencia);
        // Si la referencia ya existe, incrementamos su stock
        if (bicicleta != null) {
            bicicleta.comprar(unidades);
            return ResultadoOperacion.UPDATED;
        }
        return ResultadoOperacion.NOT_FOUND;
    }

    public ResultadoOperacion nuevaBicicleta(String referencia, String marca, String modelo, float peso, float tamanyo,
                                             boolean motor, LocalDate fechaFabricacion, float precio, int unidades) {
        Bicicleta bicicleta = buscarBicicletaPorReferencia(referencia);
        // Si la referencia ya existe, incrementamos su stock
        if (bicicleta != null) {
            return anyadirStock(referencia, unidades);
        } else if (numeroReferencias < Config.MAX_BICICLETAS) {
            bicicletas[numeroReferencias] = new Bicicleta(referencia, marca, modelo, peso, tamanyo, motor, fechaFabricacion, precio, unidades);
            numeroReferencias++;
            return ResultadoOperacion.ADDED;
        }
        return ResultadoOperacion.SPACE_LIMIT_EXCEEDED;
    }

    public ResultadoOperacion venderBicicleta(String referencia, int cantidad) {
        Bicicleta bicicleta = buscarBicicletaPorReferencia(referencia);
        if(bicicleta != null) {
            if (bicicleta.vender(cantidad)) {
                return ResultadoOperacion.UPDATED;
            } else {
                return ResultadoOperacion.INSUFFICIENT_STOCK;
            }
        } else {
            return ResultadoOperacion.NOT_FOUND;
        }
    }

    public Bicicleta buscarBicicletaPorReferencia(String referencia) {
        for (int i = 0; i < numeroReferencias; i++) {
            if (bicicletas[i].getReferencia().equalsIgnoreCase(referencia)) {
                return bicicletas[i];
            }
        }
        return null;
    }

    public Bicicleta[] buscarBicicletaPorMarca(String marca) {
        Bicicleta[] bicicletasMarca = null;
        // Lo ideal sería hacerlo con ArrayLists pero aún no los hemos visto
        // Calculamos primero cuantas bicicletas hay que coincidan con el criterio de búsqueda
        int nBicicletas = 0;
        // Convertimos a minúsculas
        marca = marca.toLowerCase();

        for (int i = 0; i < numeroReferencias; i++) {
            if (bicicletas[i].getMarca().toLowerCase().contains(marca)) {
                nBicicletas++;
            }
        }
        if (nBicicletas > 0) {
            bicicletasMarca = new Bicicleta[nBicicletas];
            int cont = 0;
            // Una vez sabemos el número de bicicletas las asignamos al array
            for (int i = 0; i < numeroReferencias; i++) {
                if (bicicletas[i].getMarca().toLowerCase().contains(marca)) {
                    bicicletasMarca[cont] = bicicletas[i];
                    cont++;
                }
            }
        }
        return bicicletasMarca;
    }

    public Bicicleta[] buscarBicicletaPorModelo(String modelo) {
        Bicicleta[] bicicletasModelo = null;
        // Lo ideal sería hacerlo con ArrayLists pero aún no los hemos visto
        // Calculamos primero cuantas bicicletas hay que coincidan con el criterio de búsqueda
        int nBicicletas = 0;
        // Convertimos a minúsculas
        modelo = modelo.toLowerCase();

        for (int i = 0; i < numeroReferencias; i++) {
            if (bicicletas[i].getModelo().toLowerCase().contains(modelo)) {
                nBicicletas++;
            }
        }
        if (nBicicletas > 0) {
            bicicletasModelo = new Bicicleta[nBicicletas];
            int cont = 0;
            // Una vez sabemos el número de bicicletas las asignamos al array
            for (int i = 0; i < numeroReferencias; i++) {
                if (bicicletas[i].getModelo().toLowerCase().contains(modelo)) {
                    bicicletasModelo[cont] = bicicletas[i];
                    cont++;
                }
            }
        }
        return bicicletasModelo;
    }

    public Bicicleta get(int pos) {
        return bicicletas[pos];
    }

    public String stockToString() {
        StringBuilder sbStock = new StringBuilder();
        for (int i = 0; i < numeroReferencias; i++) {
            sbStock.append(bicicletas[i].toString()).append("\n");
        }
        return sbStock.toString();
    }


}
