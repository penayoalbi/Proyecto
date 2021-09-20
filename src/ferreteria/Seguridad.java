package ferreteria;
import com.mysql.cj.jdbc.Blob;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class Seguridad {
    //encode
    public String Encriptar(String secretKey, String cadena){
        String encriptado = "";
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] pass = md5.digest(secretKey.getBytes("utf-8"));
            byte[] byteskey = Arrays.copyOf(pass, 24);
            SecretKey secretKey1 = new SecretKeySpec(byteskey, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(ENCRYPT_MODE, secretKey1);
            byte[] textoPlano = cadena.getBytes("utf-8"); //transformar en cadena de bytes a lo que recibo por parametro
            byte[] bufer = cifrado.doFinal(textoPlano);//contiene el cifrado
            byte[] base64Bytes = Base64.getEncoder().encode(bufer);//paso al arreglo bufer
            encriptado =new String(base64Bytes);
        }catch(Exception e){
            System.out.println("Error en encriptar");
        }
        return encriptado;
    }

    //decode
    public static String Desencriptar(String mykey, String encriptado){
        String desencriptado = "";
        try{
            byte[] msj = Base64.getDecoder().decode(encriptado.getBytes("utf-8"));//arreglo de bytes
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] decPass = md5.digest(mykey.getBytes("utf-8"));//
            byte[] keyBytes = Arrays.copyOf(decPass,24);
            SecretKey newkey = new SecretKeySpec(keyBytes, "DESede");//
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(DECRYPT_MODE, newkey);
            byte[] textoPlano = decipher.doFinal(msj);
            desencriptado = new String(textoPlano, "UTF-8");
        }catch (Exception e){
            System.out.println("Error en desencriptar.");
        }
        return desencriptado;
    }
}