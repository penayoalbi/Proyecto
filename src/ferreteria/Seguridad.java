package ferreteria;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class Seguridad {
    public static String hash(String clave) throws NoSuchAlgorithmException {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-1");
        byte[] array = md.digest(clave.getBytes());
        StringBuilder cadena_a_hash = new StringBuilder();
        for (int i = 0; i < array.length; ++i) {
            cadena_a_hash.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return cadena_a_hash.toString();
    }

    //método desencriptar
    public static String desencriptar(String a_desencriptar){
        try{
            return a_desencriptar;
        }catch (Exception e){
            System.out.println("Error al desencriptar: "+e.getMessage());
            String msjError ="ocurrio un error al encriptar: " + e.getMessage();
            return msjError;
        }
    }
}