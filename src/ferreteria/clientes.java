package ferreteria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class clientes {
    private Integer clienteID;
    private String tipoDocumento;
    private Integer documento;
    private String nombre;
    private String apellido;
    private String correo;
    private Integer telefono;
    private String direccion;
    private String provincia;
    private String localidad;
    //private Integer estado; //agregar esto

    //conexion a bd
    bd base = new bd();

    public clientes(Integer clienteID, String tipoDocumento, Integer documento, String nombre, String apellido,
                     Integer telefono,String correo, String direccion, String provincia, String localidad)
    {
        this.clienteID = clienteID;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.provincia = provincia;
        this.localidad = localidad;
    }

    public clientes(Integer documento, String nombre, String apellido){
        this.documento=documento;
        this.nombre = nombre;
        this.apellido=apellido;
    };

    public clientes() {

    }
//retorna una lista observable de cliente
    public ObservableList<clientes>getCliente(){
        ObservableList <clientes> obsCliente = FXCollections.observableArrayList();
        try{
            ResultSet rs;
            String sql = "SELECT * FROM clientes";
            rs = base.Consultar(sql);
            Integer docu = 0;
            String nombre = "";
            String apellido = "";
            while(rs.next()){
                 docu = rs.getInt("documento");
                 nombre = rs.getString("nombre");
                 apellido = rs.getString("apellido");
                 clientes cl = new clientes(docu,nombre,apellido);//creo un const especial con los param que necesito
                 obsCliente.add(cl);
            }
        }catch (Exception e){
            System.out.println("error en getCliente "+e.getMessage());
        }
        return obsCliente;
    }

    public Integer getClienteID() {
        return clienteID;
    }

    public void setClienteID(Integer clienteID) {
        this.clienteID = clienteID;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    //para usarlos en el comboBox
    @Override
    public String toString() {
        return nombre +" "+ apellido+" "+ documento;
    }
}
