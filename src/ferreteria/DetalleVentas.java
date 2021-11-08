package ferreteria;

public class DetalleVentas {
    private Integer id;
    private Integer codVenta;
    private Integer codProducto;
    private String nombreProducto;
    private String descripcionProd;
    private float precioUnitario;
    private Integer cantidad;
    private float precioTotal;

    //constructor


    public DetalleVentas(Integer id, Integer codVenta, Integer codProducto,
                         String nombreProducto, String descripcionProd,
                         float precioUnitario, Integer cantidad, float precioTotal)
    {
        this.id = id;
        this.codVenta = codVenta;
        this.codProducto = codProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProd = descripcionProd;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    //setter and getter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodVenta() {
        return codVenta;
    }

    public void setCodVenta(Integer codVenta) {
        this.codVenta = codVenta;
    }

    public Integer getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(Integer codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProd() {
        return descripcionProd;
    }

    public void setDescripcionProd(String descripcionProd) {
        this.descripcionProd = descripcionProd;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }
}
