package etsisi.ems.trabajo3.banco;

import java.time.LocalDate;

public class Debito extends Tarjeta  {
	
	Debito(Cuenta cuentaAsociada, String numero, String titular, LocalDate fechaCaducidad) {
		super(cuentaAsociada, fechaCaducidad, numero, titular);
	}
	
	public void setCuenta(Cuenta c) {
		mCuentaAsociada = c;
	}

	public void retirar(double x) throws Exception {
		this.mCuentaAsociada.retirar("Retirada en cajero automático", x);
	}

	public void ingresar(double x) throws Exception {
		this.mCuentaAsociada.ingresar("Ingreso en cajero automático", x);
	}

	public void pagoEnEstablecimiento(String datos, double x) throws Exception {
		this.mCuentaAsociada.retirar("Compra en :" + datos, x);
	}

	public double getSaldo() {
		return mCuentaAsociada.getSaldo();
	}
}