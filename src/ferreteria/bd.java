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
        try {
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
    //buscar coincidencia
    public Boolean buscarIdex(String sql){
        try{
            Connection conn = DriverManager.getConnection(conbd);
            Statement statement = conn.createStatement();
            statement.execute(sql);
            return true;
        }catch (SQLException e){
            System.out.println("ERROR: "+e.getMessage());
            return false;
        }
    }
}