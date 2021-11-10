package ferreteria;

import java.util.Date;

public class ModelVentas {
    private Integer ventaID;
    private Integer codigoVenta;
    private Integer detalleVenta;//coddetalleventa
    private Integer vendedorID;
    private Integer cantidad;
    private Double monto;
    private Date fecha;
    private Integer estadoVenta;


    //construct
    public ModelVentas(Integer ventaID, Integer codigoVenta,
                       Integer detalleVenta, Integer vendedorID,
                       Double monto, Date fecha, Integer estadoVenta)
    {
        this.ventaID = ventaID;
        this.codigoVenta = codigoVenta;
        this.detalleVenta = detalleVenta;
        this.vendedorID = vendedorID;
        this.monto = monto;
        this.fecha = fecha;
        this.estadoVenta = estadoVenta;
    }

    public ModelVentas(Integer codigoVenta, Integer detalleVenta, Integer cantidad, Double monto, Date fecha) {
        this.codigoVenta = codigoVenta;
        this.detalleVenta = detalleVenta;
        this.cantidad = cantidad;
        this.monto = monto;
        this.fecha = fecha;

    }

    //setter and getter
    public Integer getVentaID() {
        return ventaID;
    }

    public void setVentaID(Integer ventaID) {
        this.ventaID = ventaID;
    }

    public Integer getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(Integer codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public Integer getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(Integer detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public Integer getVendedorID() {
        return vendedorID;
    }

    public void setVendedorID(Integer vendedorID) {
        this.vendedorID = vendedorID;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(Integer estadoVenta) {
        this.estadoVenta = estadoVenta;
    }
}
