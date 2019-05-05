package etsisi.ems.trabajo3.banco;

import java.time.LocalDate;

public class InfoBancaria {

    private String titular;
    private int numero;
    private LocalDate fechaCaducidad;
    private String nombreEntidad;
    private int CCV;


    public InfoBancaria(String titular, int numero, LocalDate fechaCaducidad, String nombreEntidad, int CCV) {
        this.titular = titular;
        this.numero = numero;
        this.fechaCaducidad = fechaCaducidad;
        this.nombreEntidad = nombreEntidad;
        this.CCV = CCV;
    }

    public InfoBancaria(String titular, int numero, LocalDate fechaCaducidad) {
        this.titular = titular;
        this.numero = numero;
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }

    public int getCCV() {
        return CCV;
    }

    public void setCCV(int CCV) {
        this.CCV = CCV;
    }
}
