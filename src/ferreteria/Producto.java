package ferreteria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Producto {

    private Integer productoID;
    private String nombre;
    private String descripcion;
    private float precioLista;
    private float precioVenta;
    private Integer stock;
    private String categoria;

    //construct
    public Producto(Integer productoID,  String descripcion, float precioLista, float precioVenta, Integer stock, String categoria, String nombre) {
        this.productoID = productoID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioLista = precioLista;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.categoria=categoria;
    }
    public Producto(String descripcion, float precio) {
        //this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precio;
    }
    public Producto() {

    }

    //conexion a base
    bd base = new bd();

    //observable de productos
    public ObservableList<Producto> getProducto(){
        ObservableList <Producto> obProducto = FXCollections.observableArrayList();
        try{
           // String nombre;
            String descripcion;
            float precio;
            ResultSet rs;
            String query = " select Descripcion, PrecioVenta from productos where Stock >= 1";
            rs = base.Consultar(query);
            while(rs.next()){
              //  nombre = rs.getString("nombre");
                descripcion = rs.getString("Descripcion");
                precio = rs.getFloat("PrecioVenta");
                Producto prod = new Producto(descripcion,precio);
                obProducto.add(prod);
            }

        }catch (Exception e){
            System.out.println("Error en retornar observable producto "+e.getMessage());
        }
        return obProducto;
    }
//setter and getter
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

    @Override
    public String toString(){
        return descripcion+ " "+ precioVenta;
    }

}
