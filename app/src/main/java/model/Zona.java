package model;

/**
 * Created by josdan on 21/06/17.
 */

public class Zona {

    private int numeroDeZona;
    private int cantidadAguaRequerida;

    public Zona( int numeroDeZona, int cantidadAguaRequerida ) {
        this.numeroDeZona = numeroDeZona;
        this.cantidadAguaRequerida = cantidadAguaRequerida;
    }

    public int getNumeroDeZona() {
        return numeroDeZona;
    }

    public void setNumeroDeZona(int numeroDeZona) {
        this.numeroDeZona = numeroDeZona;
    }

    public int getCantidadAguaRequerida() {
        return cantidadAguaRequerida;
    }

    public void setCantidadAguaRequerida(int cantidadAguaRequerida) {
        this.cantidadAguaRequerida = cantidadAguaRequerida;
    }
}
