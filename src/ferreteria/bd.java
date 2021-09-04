package ferreteria;
import java.sql.*;
import java.sql.SQLException;
public class bd {

    static String conbd="jdbc:mysql://127.00.1:3306/ferreteria?user=root&password&serverTimezone=UTC";
    private ResultSet rs;

    public ResultSet Consultar(String stringsql) {
        try{
            Connection conn = DriverManager.getConnection(conbd);
            Statement statement = conn.createStatement();
            rs = statement.executeQuery(stringsql);
            //statement.close();
            //conn.close();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return rs;
    }

    //INSERT INTO `usuarios` (`id`, `nombre`, `correo`, `clave`) VALUES (NULL, 'ana', 'sample@mail.com', '1234');
    public void Guardar(String stringsql) {//guardar registo en bd
        try {
            Connection conn = DriverManager.getConnection(conbd);
            Statement statement = conn.createStatement();
            statement.execute(stringsql);
           // statement.close();
          //  conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //UPDATE `usuarios` SET `clave` = 'lalo123a4' WHERE `usuarios`.`id` = 2
    public void modificardatos(String stringsql) {
        try {
            Connection conn = DriverManager.getConnection(conbd);
            Statement ps = conn.createStatement();
            ps.executeUpdate(stringsql);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //delete
    public static int eliminar(int item)
    {
        try {// "DELETE FROM `usuarios` WHERE `usuarios`.`usuarioID` = 3"?
            Connection conn = DriverManager.getConnection(conbd);
            String sentencia= "DELETE FROM `usuarios` WHERE `usuarios`.`usuarioID` = ? ";
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ps.setString(1,String.valueOf(item));
            return ps.executeUpdate();//retorna un valor entero
        }catch (SQLException e){
            System.out.println("Error"+e.getMessage());
            return 0;
        }
    }
}