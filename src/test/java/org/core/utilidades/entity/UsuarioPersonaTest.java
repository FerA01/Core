package org.core.utilidades.entity;

import org.core.utilidades.dao.PersonaDao;
import org.core.utilidades.dao.UsuarioPersonaDao;
import org.core.utilidades.dependencia.PersonaDependencia;
import org.core.utilidades.dependencia.UsuarioPersonaDependencia;
import org.core.utilidades.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class UsuarioPersonaTest {
    private UsuarioPersonaDao dao;
    private UsuarioPersonaDependencia upd;
    private PersonaDao personaDao;
    private PersonaDependencia pd;

    @BeforeEach
    @Test
    public void iniciarDao(){
        setUpd(UsuarioPersonaDependencia.getInstance());
        setDao(getUpd().getUsuarioPersonaDao());
        setPd(PersonaDependencia.getInstance());
        setPersonaDao(getPd().getPersonaDao());
    }

    @Test
    public void deberiaPersistirEntidad(){
        Persona persona = getPersonaDao().buscarPorId(1L);
        if (persona != null){
            UsuarioPersona usuario = new UsuarioPersona();
            usuario.setUsuario("prueba1");
            usuario.setContrasena("contraseña");
            usuario.setPersona(persona);
            usuario.setLogueado(true);
            usuario.setFechaAlta(Util.getFechaHoy());


            //UsuarioPersona up = getDao().guardar(usuario);
            persona.setUsuario(usuario);
            Persona p = getPersonaDao().actualizar(persona);

            assertNotNull(p, "Error al actualizar la persona");
           // assertNotNull(up, "Error al guardar el usuario");
        }
    }

    public UsuarioPersonaDao getDao() {
        return dao;
    }
    public void setDao(UsuarioPersonaDao dao) {
        this.dao = dao;
    }
    public UsuarioPersonaDependencia getUpd() {
        return upd;
    }
    public void setUpd(UsuarioPersonaDependencia upd) {
        this.upd = upd;
    }
    public PersonaDao getPersonaDao() {
        return personaDao;
    }
    public void setPersonaDao(PersonaDao personaDao) {
        this.personaDao = personaDao;
    }
    public PersonaDependencia getPd() {
        return pd;
    }
    public void setPd(PersonaDependencia pd) {
        this.pd = pd;
    }
}