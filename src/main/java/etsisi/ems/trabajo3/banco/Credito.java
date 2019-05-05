package etsisi.ems.trabajo3.banco;

import java.util.Date;
import java.util.Vector;
import java.time.LocalDate;
import java.time.ZoneId;

public class Credito extends Tarjeta {

    private double mCredito;
    private Vector<Movimiento> mMovimientos;
    private MarcaInternacional mMarcaInternacional; //mastercard, maestro, visa ...
    private TipoTarjeta mTipo; //oro platino clásica
    private int mCCV;
    private String mNombreEntidad;


    public Credito(InfoBancaria infoBancaria, Cuenta cuentaAsociada, TipoTarjeta tipo, MarcaInternacional marcainternacional) {
        super(cuentaAsociada, infoBancaria.getFechaCaducidad(), infoBancaria.getNumero(), infoBancaria.getTitular());
        mCredito = tipo.getCredito();
        mMovimientos = new Vector<Movimiento>();
        mMarcaInternacional = marcainternacional;
        mNombreEntidad = infoBancaria.getNombreEntidad();
        mCCV = infoBancaria.getCCV();

    }

    public Credito(Cuenta cuentaAsociada, TipoTarjeta tipo, MarcaInternacional marcaInternacional, InfoBancaria infoBancaria) {
        super(cuentaAsociada, infoBancaria.getFechaCaducidad(), infoBancaria.getNumero(), infoBancaria.getTitular());
        mTipo = tipo;
        mCredito = mTipo.getCredito();
        mMovimientos = new Vector<Movimiento>();
        mMarcaInternacional = marcaInternacional;
        mNombreEntidad = infoBancaria.getNombreEntidad() d;
        mCCV = infoBancaria.getCCV();
    }


    public void setCuenta(Cuenta c) {
        mCuentaAsociada = c;
    }

    public void retirar(double x) throws Exception {

        // Añadimos una comisión de un 5% o 3% o 2%, mínimo de 3 euros.
        double comision = (x * this.mMarcaInternacional.getComisionTarifa() < 3.0 ? 3 : x * this.mMarcaInternacional.getComisionTarifa());
        if (x > getCreditoDisponible())
            throw new Exception("Crédito insuficiente");
        mMovimientos.addElement(new Movimiento("Retirada en cuenta asociada (cajero automático)", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), x + comision));

    }

    //traspaso tarjeta a cuenta
    public void ingresar(double x) throws Exception {

        double comision = (x * 0.05 < 3.0 ? 3 : x * 0.05); // Añadimos una comisión de un 5%, mínimo de 3 euros.
        if (x > getCreditoDisponible())
            throw new Exception("Crédito insuficiente");
        Movimiento m = new Movimiento("Traspaso desde tarjeta a cuenta", new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), x);
        mMovimientos.addElement(m);

        mCuentaAsociada.ingresar("Traspaso desde tarjeta a cuenta", x);
        mCuentaAsociada.retirar("Comision Traspaso desde tarjeta a cuenta", comision);
    }

    public void pagoEnEstablecimiento(String datos, double x) throws Exception {

        Movimiento m = new Movimiento("Compra a crédito en: " + datos, new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), x);
        mMovimientos.addElement(m);
    }

    public double getSaldo() {
        double r = 0.0;
        for (int i = 0; i < this.mMovimientos.size(); i++) {
            Movimiento m = (Movimiento) mMovimientos.elementAt(i);
            r += m.getImporte();
        }
        return r;
    }

    public double getCreditoDisponible() {
        return mCredito - getSaldo();
    }

    public void liquidar(int mes, int anyo) throws Exception {

        double totalLiquidacion = liqudarMovimientos(mes, anyo);

        if (totalLiquidacion != 0) {

            Movimiento liq = new Movimiento("Liquidación de operaciones tarj. crédito, " + (mes) + " de " + (anyo), new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), -totalLiquidacion);
            mCuentaAsociada.addMovimiento(liq);
        }
    }

    private double liqudarMovimientos(int mes, int anyo){
        double r = 0.0;
        for (int i = 0; i < this.mMovimientos.size(); i++) {
            Movimiento m = (Movimiento) mMovimientos.elementAt(i);
            if (m.getFecha().getMonthValue() == mes && m.getFecha().getYear() == anyo && !m.isLiquidado())
                r += m.getImporte();
            m.setLiquidado(true);
        }
        return r;


    }

    //liquidación parcial sobre el total de los gastos realizados con esa tarjeta durante el mes/año  de liquidación que consiste en lo siguiente:
    //los gastos totales, incluida una comisión de 12%, se dividen en 3 cuotas a pagar en los 3 meses siguientes
    public void liquidarPlazos(int mes, int anyo) throws Exception {
        //TODO
    }
}
