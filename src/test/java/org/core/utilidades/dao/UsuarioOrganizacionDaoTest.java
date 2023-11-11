package org.core.utilidades.dao;

import org.core.utilidades.dependencia.UsuarioOrganizacionDependencia;
import org.core.utilidades.entity.Organizacion;
import org.core.utilidades.entity.UsuarioOrganizacion;
import org.core.utilidades.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioOrganizacionDaoTest {
    private UsuarioOrganizacionDependencia dep;
    private OrganizacionDao organizacionDao;
    private UsuarioOrganizacionDao uod;

    @BeforeEach
    public void iniciar(){
        setDep(UsuarioOrganizacionDependencia.getInstance());
        setUod(dep.getUsuarioOrganizacionDao());
        setOrganizacionDao(new OrganizacionDao(dep.getUsuarioOrganizacionDao().entityManager));

    }

    @Test
    public void deberiaPersistirOrganizacion(){
        Organizacion organizacion = new Organizacion();
        organizacion.setCuit(30123456789L);
        organizacion.setFechaCreacion(Util.cambiarFecha(Util.getFechaHoy(),25, 5, 1998));
        organizacion.setRazonSocial("Empresa Ficticia S.A");

        Organizacion persiste =  organizacionDao.guardar(organizacion);

        assertNotNull(persiste);
    }

    @Test
    public void deberiaObtenerOrganizacionPorCuit(){
        Long cuitEsperado = 30123456789L;

        Organizacion organizacion = organizacionDao.obtenerTitularCuit(30123456789L);
        assertNotNull(organizacion, "No existe organizacion con tal CUIT");
        assertEquals(cuitEsperado, organizacion.getCuit(), "Los valores no coinciden");
    }

    @Test
    public void deberiaPersistirUsuarioOrganizacion(){
        Organizacion organizacion = organizacionDao.obtenerTitularCuit(30123456789L);
        UsuarioOrganizacion usuario = new UsuarioOrganizacion();
        usuario.setUsuario("pruebaUsuarioOrganizacion");
        usuario.setContrasena("contraseña");
        usuario.setFechaAlta(Util.getFechaHoy());
        usuario.setLogueado(false);
        usuario.setOrganizacion(organizacion);

        UsuarioOrganizacion resultado = uod.guardar(usuario);
        assertNotNull(resultado);
    }


    public UsuarioOrganizacionDependencia getDep() {
        return dep;
    }

    public void setDep(UsuarioOrganizacionDependencia dep) {
        this.dep = dep;
    }

    public OrganizacionDao getOrganizacionDao() {
        return organizacionDao;
    }

    public void setOrganizacionDao(OrganizacionDao organizacionDao) {
        this.organizacionDao = organizacionDao;
    }

    public UsuarioOrganizacionDao getUod() {
        return uod;
    }

    public void setUod(UsuarioOrganizacionDao uod) {
        this.uod = uod;
    }
}