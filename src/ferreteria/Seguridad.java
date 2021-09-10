package ferreteria;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static javax.crypto.Cipher.ENCRYPT_MODE;

public class Seguridad {
    String msjError;

    private static String ENCRYPTKEY = "a_encriptar"; //Crear clave interna para encriptar y desencriptar
    //metodo encriptar
    public static String encriptar(String a_encriptar) {
        try {
            SecretKeySpec miClave = new SecretKeySpec(ENCRYPTKEY.getBytes(), 0, 16, "AES");//genera clave incriptado
            //ins AES
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCSS5Padding");
            //pasar la llave de encript
            cipher.init(ENCRYPT_MODE, miClave);
            byte[] encriptadoAES = cipher.doFinal(a_encriptar.getBytes());
            String encriptado = Base64.getMimeEncoder().encodeToString(encriptadoAES);
            return encriptado;
        } catch (Exception e) {
            System.out.println("ocurrio un error: " + e.getMessage());
        }
        return a_encriptar;
    }

    //metodo desencriptar
    public static String desencriptar(String a_desencriptar){
        try{
            return a_desencriptar;
        }catch (Exception e){
            System.out.println("Error al desencriptar: "+e.getMessage());
        }
        return a_desencriptar;
    }




}