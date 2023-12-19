package org.core.utilidades.entity;
import org.core.utilidades.dao.PersonaDao;
import org.core.utilidades.dao.UsuarioPersonaDao;
import org.core.utilidades.dependencia.PersonaDependencia;
import org.core.utilidades.dependencia.UsuarioPersonaDependencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void deberiaDevolverUsuarioIdUno(){
        Persona persona = getPersonaDao().buscarPorId(1L);
        assertNotNull(persona);
        UsuarioPersona usuario = getDao().buscarPorPersona(persona);
        assertNotNull(usuario);
    }

    @Test
    public void deberiaDevolverUsuarioNombreUsuario(){
        UsuarioPersona usuario = getDao().buscarPorUsuario("Loco09");
        assertNotNull(usuario);
        assertEquals("Loco09", usuario.getUsuario(), "No son el mismo usuario");
    }

    @Test
    public void deberiaActualizarContrasena(){
        UsuarioPersona usuario = getDao().buscarPorUsuario("Loco09");
        assertNotNull(usuario);
        usuario.setContrasena("1234");
        getDao().actualizar(usuario);
        assertEquals("1234", usuario.getContrasena());
    }

    @Test
    public void deberiaFallarPersistirEntidadExistente(){
        UsuarioPersona usuario = getDao().buscarPorId(1L);
        UsuarioPersona entidadCreada = getDao().guardar(usuario);
        assertNull(entidadCreada);
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