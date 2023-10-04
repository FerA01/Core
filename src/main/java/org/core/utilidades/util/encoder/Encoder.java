package org.core.utilidades.util.encoder;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

public class Encoder {
    private static final String CLAVE_SECRETA = "IngenieriaEnInformatica";

    public static byte[] encode(String contrasena){
        byte[] encriptado = null;
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] llaveContrasena = md5.digest(CLAVE_SECRETA.getBytes(StandardCharsets.UTF_8));
            byte[] bytesKey = Arrays.copyOf(llaveContrasena, 24);
            SecretKey key = new SecretKeySpec(bytesKey, "DESede");
            Cipher cifrado = Cipher.getInstance("DESede");
            cifrado.init(Cipher.ENCRYPT_MODE, key);

            byte[] bytesTextoPlano = contrasena.getBytes(StandardCharsets.UTF_8);
            byte[] buffer = cifrado.doFinal(bytesTextoPlano);
            byte[] bytesBase64 = Base64.encodeBase64(buffer);
            encriptado = bytesBase64;
            return encriptado;
        }catch (Exception excepcion){
            System.out.println("Error al cifrar: " + excepcion.getMessage());
        }
        return encriptado;
    }
    public static byte[] decode(String contrasena){
        try {
            byte[] mensaje = Base64.decodeBase64(contrasena.getBytes(StandardCharsets.UTF_8));
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digestContrasena = md5.digest(CLAVE_SECRETA.getBytes(StandardCharsets.UTF_8));
            byte[] bytesKey = Arrays.copyOf(digestContrasena, 24);
            SecretKey key = new SecretKeySpec(bytesKey, "DESede");
            Cipher descifrar = Cipher.getInstance("DESede");
            descifrar.init(Cipher.DECRYPT_MODE, key);
            return descifrar.doFinal(mensaje);
        }catch (Exception excepcion){
            System.out.println("Error al desencriptar: " + excepcion.getMessage());
            return null;
        }
    }
    public static boolean contrasenaValida(String contrasena, String obtenido){ return contrasena.equals(obtenido); }
}
