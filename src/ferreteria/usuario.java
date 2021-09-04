package ferreteria;

public class usuario {
    private Integer usuarioID;
    private String tipoDocumento;
    private String documento;
    private String nombre;
    private String apellido;
    private String correo;
    private String clave;
    private String cargo;
    private String telefono;
    private String estado;
    private String domicilio;

    public usuario(Integer usuarioID, String tipoDocumento, String documento, String nombre,
                   String apellido, String correo, String clave, String cargo, String telefono,
                   String estado, String domicilio)
    {
        this.usuarioID=usuarioID;
        this.tipoDocumento=tipoDocumento;
        this.documento=documento;
        this.nombre=nombre;
        this.apellido=apellido;
        this.correo=correo;
        this.clave=clave;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
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
}
