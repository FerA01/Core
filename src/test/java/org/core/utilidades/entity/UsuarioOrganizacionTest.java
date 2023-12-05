package org.core.utilidades.entity;

import org.core.utilidades.dao.OrganizacionDao;
import org.core.utilidades.dao.UsuarioOrganizacionDao;
import org.core.utilidades.dependencia.OrganizacionDependencia;
import org.core.utilidades.dependencia.UsuarioOrganizacionDependencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsuarioOrganizacionTest {
    private UsuarioOrganizacionDao dao;
    private UsuarioOrganizacionDependencia uod;
    private OrganizacionDao organizacionDao;
    private OrganizacionDependencia od;


    @BeforeEach
    @Test
    public void iniciarDaos(){
        setOd(OrganizacionDependencia.getInstance());
        setOrganizacionDao(getOd().getOrganizacionDao());
        setUod(UsuarioOrganizacionDependencia.getInstance());
        setDao(getUod().getUsuarioOrganizacionDao());
    }

    @Test
    public void deberiaBuscarUsuarioPorOrganizacion(){
        Organizacion organizacion = getOrganizacionDao().buscarPorId(1L);
        assertNotNull(organizacion);
        UsuarioOrganizacion usuario = getDao().obtenerPorOrganizacion(organizacion);
        assertNotNull(usuario);
    }

    @Test
    public void deberiaBuscarUsuarioPorNombre(){
        UsuarioOrganizacion usuario = getDao().buscarPorUsuario("ficticiaSA");
        assertNotNull(usuario);
        assertEquals("ficticiaSA", usuario.getUsuario());
    }

    @Test
    public void deberiaActualizarContrasena(){
        UsuarioOrganizacion usuario = getDao().buscarPorUsuario("ficticiaSA");
        assertNotNull(usuario);
        usuario.setContrasena("12345");
        getDao().actualizar(usuario);
        assertEquals("12345", usuario.getContrasena());
    }


    public UsuarioOrganizacionDao getDao() {
        return dao;
    }

    public void setDao(UsuarioOrganizacionDao dao) {
        this.dao = dao;
    }

    public UsuarioOrganizacionDependencia getUod() {
        return uod;
    }

    public void setUod(UsuarioOrganizacionDependencia uod) {
        this.uod = uod;
    }

    public OrganizacionDao getOrganizacionDao() {
        return organizacionDao;
    }

    public void setOrganizacionDao(OrganizacionDao organizacionDao) {
        this.organizacionDao = organizacionDao;
    }

    public OrganizacionDependencia getOd() {
        return od;
    }

    public void setOd(OrganizacionDependencia od) {
        this.od = od;
    }
}
