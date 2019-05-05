package etsisi.ems.trabajo3.banco;

public class TipoTarjeta {

    private String tipo;
    private int credito;

    public TipoTarjeta(String tipo, int credito ) {
        this.tipo = tipo;
        this.credito=credito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }
}
