package etsisi.ems.trabajo3.banco;

public class MarcaInternacional {

    double comisionTarifa;
    String nombre;

    public MarcaInternacional(double comisionTarifa, String nombre) {
        this.mcomisionTarifa = comisionTarifa;
        this.mnombre = nombre;
    }

    public double getComisionTarifa() {
        return mcomisionTarifa;
    }

    public void setComisionTarifa(double mcomisionTarifa) {
        this.mcomisionTarifa = mcomisionTarifa;
    }

    public String getNombre() {
        return mnombre;
    }

    public void setNombre(String mnombre) {
        this.mnombre = mnombre;
    }
}
