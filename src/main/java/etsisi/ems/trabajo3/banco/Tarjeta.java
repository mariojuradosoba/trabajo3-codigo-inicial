package etsisi.ems.trabajo3.banco;

import java.util.Date;
import java.util.Vector;
import java.time.LocalDate;
import java.time.ZoneId;

public abstract class Tarjeta {
    public Cuenta mCuentaAsociada;
    String mNumero, mTitular;
    LocalDate mFechaDeCaducidad;

     public Tarjeta (Cuenta cuentaAsociada, LocalDate fechaCaducidad, String mNumero, String mTitular){
        this.mFechaDeCaducidad = fechaCaducidad;
        this.mNumero = mNumero;
        this.mTitular = mTitular;
        this.mCuentaAsociada = cuentaAsociada;
    }
}
