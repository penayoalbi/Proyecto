package ferreteria;

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
/*
    public Integer getGeneradoPor() {
        return claveGeneradaPor;
    }

    public void setGeneradoPor(Integer claveGeneradaPor) {
        this.claveGeneradaPor = claveGeneradaPor;
    }

 */
}
