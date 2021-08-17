package sample;
import java.sql.*;
import java.sql.SQLException;
public class bd {

    static String conbd="jdbc:mysql://127.00.1:3306/ferreteria?user=root&password&serverTimezone=UTC";

    //realizar consulta/SELECT
    public ResultSet Consultar(String stringsql) {
        ResultSet rs=null;
        try {
         //   String select="select * from ferreteria.usuarios where nombre=?";
           // PreparedStatement ps;
            Connection conn = DriverManager.getConnection(conbd);
            if(conbd!=null){
                Statement statement=conn.createStatement();
                ResultSet resultSet=statement.executeQuery(stringsql);
                return  resultSet;
            }
            //Statement statement = conn.createStatement();
            //ps=conn.prepareStatement(select);
           // ps.setString(1, stringsql);
         //   rs=ps.executeQuery();
           // ps.close();
           // conn.close();
            if(rs.next()){
                return rs;
            }
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return rs;
    }
    //guardar/INSERT
    //INSERT INTO `usuarios` (`id`, `nombre`, `correo`, `clave`) VALUES (NULL, 'ana', 'sample@mail.com', '1234');
    public void Guardar(String stringsql) {//guardar registo en bd
        try {
            Connection conn = DriverManager.getConnection(conbd);
            Statement statement = conn.createStatement();
            statement.execute(stringsql);
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //update
    public void modificardatos(String stringsql)
    {//modificar registos
        try {
            Connection conn = DriverManager.getConnection(conbd);
            Statement ps = conn.createStatement();
            ps.executeUpdate(stringsql);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //delete
    public static int eliminar(String sentencia)
    {
        try {
            Connection conn = DriverManager.getConnection(conbd);
            //String sentencia= "DELETE FROM beltran.Abmprofesor WHERE idprofesor = ? ";
            PreparedStatement ps = conn.prepareStatement(sentencia);
            ps.setString(1,sentencia);
            return ps.executeUpdate();//retorna un valor entero
        }catch (SQLException e){
            System.out.println("Error"+e.getMessage());
            return 0;
        }
    }
}