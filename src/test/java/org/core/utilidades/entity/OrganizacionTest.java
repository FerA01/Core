package org.core.utilidades.entity;
import jakarta.persistence.EntityExistsException;
import org.core.utilidades.dao.OrganizacionDao;
import org.core.utilidades.dependencia.OrganizacionDependencia;
import org.core.utilidades.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrganizacionTest {
    private OrganizacionDao organizacionDao;
    private OrganizacionDependencia od;

    @BeforeEach
    @Test
    public void iniciarDao(){
        setOd(OrganizacionDependencia.getInstance());
        setOrganizacionDao(getOd().getOrganizacionDao());
    }


    @Test
    public void deberiaDevolverDatosOrganizacion(){
        String datoEsperado = "Razón Social: Ferrari SA, Fecha de creación: 09/10/2023, Cuit: 30123456789";
        Organizacion organizacion = new Organizacion();
        organizacion.setRazonSocial("Ferrari SA");
        organizacion.setFechaCreacion(Util.cambiarFecha(Util.getFechaHoy(),9,10,2023));
        organizacion.setCuit(30123456789L);

        String datoObtenido = organizacion.toString();

        assertEquals(datoEsperado, datoObtenido);
    }

    @Test
    public void deberiaActualizarDatosOrganizacionRazonSocial(){
        String datoEsperado = "Mercedes SA";
        Organizacion organizacion = getOrganizacionDao().buscarPorId(1L);
        organizacion.setRazonSocial("Mercedes SA");
        getOrganizacionDao().actualizar(organizacion);
        assertEquals(datoEsperado, organizacion.getRazonSocial());
    }

    @Test
    public void deberiaVerificarExistenciaEntidadAntesPersistir(){
        Organizacion organizacion = getOrganizacionDao().buscarPorId(1L);

        getOrganizacionDao().guardar(organizacion);
    }

    public OrganizacionDao getOrganizacionDao() { return organizacionDao; }
    public void setOrganizacionDao(OrganizacionDao organizacionDao) { this.organizacionDao = organizacionDao; }
    public OrganizacionDependencia getOd() { return od; }
    public void setOd(OrganizacionDependencia od) { this.od = od; }
}