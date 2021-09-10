package ferreteria;

public class Producto {

    private Integer productoID;
    private String nombre;
    private String descripcion;
    private float precioLista;
    private float precioVenta;
    private Integer stock;
    private String categoria;
    private Integer proveedor;

    //construct
    public Producto(Integer productoID, String nombre, String descripcion, float precioLista, float precioVenta, Integer stock, String categoria, Integer proveedor) {
        this.productoID = productoID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioLista = precioLista;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    public Integer getProductoID() {
        return productoID;
    }

    public void setProductoID(Integer productoID) {
        this.productoID = productoID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(float precioLista) {
        this.precioLista = precioLista;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public void setCategoria(String categoria){
        this.categoria=categoria;
    }
    public String getCategoria(){
        return categoria;
    }
    public void setProveedor(Integer proveedor){
        this.proveedor=proveedor;
    }
    public Integer getProveedor(){
        return proveedor;
    }
}
