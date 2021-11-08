package ferreteria;


import java.io.File;

public class factura {

    public factura(Integer codigo, String detalle) {
        this.codigo = codigo;
        this.detalle = detalle;
    }

    public factura(File file) {
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    private Integer codigo;
     private String detalle;
    //Image img = Image.getInstance("nombre.jpd);


}
