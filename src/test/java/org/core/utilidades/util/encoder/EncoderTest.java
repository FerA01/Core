package org.core.utilidades.util.encoder;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;
import static org.junit.jupiter.api.Assertions.*;
class EncoderTest {
    private String contrasena;

    @Test
    public void deberiaEncriptarContrasena(){
        setContrasena("Contraseña");
        byte[] codificado = Encoder.encode(getContrasena());
        String obtenido = new String(codificado, StandardCharsets.UTF_8);
        assertNotEquals(getContrasena(), obtenido);
    }

    @Test
    public void deberiaDesencriptarContrasena(){
        setContrasena("Contraseña");
        byte[] encriptada = Encoder.encode(getContrasena());
        String encriptadaAux = new String(encriptada, StandardCharsets.UTF_8);
        String desencriptada = new String(Encoder.decode(encriptadaAux), StandardCharsets.UTF_8);
        assertEquals("Contraseña", desencriptada);
    }

    @Test
    public void deberianCoincidirContrasenas(){
        setContrasena("HolaMundo");

        byte[] encriptar = Encoder.encode(getContrasena());
        String encriptado = new String(encriptar, StandardCharsets.UTF_8);
        String desencriptado = new String(Encoder.decode(encriptado), StandardCharsets.UTF_8);
        assertTrue(Encoder.contrasenaValida("HolaMundo", desencriptado));
    }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}