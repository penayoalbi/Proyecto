package ferreteria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class usuario {
    private Integer usuarioID;
    private String tipoDocumento;
    private Integer documento;
    private String nombre;
    private String apellido;
    private String correo;
    private String usuario;
    private String clave;
    private String cargo;
    private Integer telefono;
    private String estado;
    private String domicilio;
  //  private  Integer claveGeneradaPor;

    //llamadas
    bd base = new bd();

    //construct
    public usuario(Integer usuarioID, String tipoDocumento, Integer documento, String nombre,
                   String apellido, String usuario, String correo, String clave, String cargo, Integer telefono,
                   String estado, String domicilio)
    {
        this.usuarioID=usuarioID;
        this.tipoDocumento=tipoDocumento;
        this.documento=documento;
        this.nombre=nombre;
        this.apellido=apellido;
        this.usuario=usuario;
        this.correo=correo;
        this.clave=clave;
        this.cargo=cargo;
        this.telefono=telefono;
        this.estado=estado;
        this.domicilio=domicilio;
       // this.claveGeneradaPor = claveGeneradaPor;
    }

    //construct para comboBox
    public usuario(Integer usuarioID, String nombre, String apellido) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    //construct cin parametros
    public usuario() {
    }

    public void listar(int usuarioID,
                   String tipoDocumento, Integer documento,
                   String nombre, String apellido, String correo,
                   String usuario, String cargo, Integer telefono,
                   String estado, String domicilio)
    {
        this.usuarioID=usuarioID;
        this.tipoDocumento=tipoDocumento;
        this.documento=documento;
        this.nombre=nombre;
        this.apellido=apellido;
        this.usuario=usuario;
        this.correo=correo;
        this.cargo=cargo;
        this.telefono=telefono;
        this.estado=estado;
        this.domicilio=domicilio;

    }

    //retorna una lista observable de usuario
    public ObservableList<usuario> getUsuarios() {
        ObservableList <usuario> obUsuario = FXCollections.observableArrayList();
        try{
            Integer usuarioID = 0;
            String nombre = "";
            String apellido = "";
            ResultSet rs;
            String sql = "select usuarioID, nombre, apellido from usuarios where cargo = 'vendedor' or cargo='Vendedor' ";
            rs = base.Consultar(sql);
            while(rs.next()){
                usuarioID = rs.getInt("usuarioID");
                nombre = rs.getString("nombre");
                apellido = rs.getString("apellido");
                usuario user = new usuario(usuarioID, nombre, apellido);
                obUsuario.add(user);
            }

        }catch (Exception e){
            System.out.println("Error en retornar lista observable");
        }
        return obUsuario;
    }

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDomicilio() {
        return domicilio;
    }


    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    //para retornarlo en el comboBox
    @Override
    public String toString() {
        return usuarioID+" "+ nombre+" "+ apellido;
    }
/*
    public Integer getGeneradoPor() {
        return claveGeneradaPor;
    }

    public void setGeneradoPor(Integer claveGeneradaPor) {
        this.claveGeneradaPor = claveGeneradaPor;
    }
 */
}
