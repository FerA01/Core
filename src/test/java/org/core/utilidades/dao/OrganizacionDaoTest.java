package org.core.utilidades.dao;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.core.utilidades.entity.Organizacion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrganizacionDaoTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence_core");
    private OrganizacionDao organizacionDao = new OrganizacionDao(emf.createEntityManager());

    @Test
    public void deberiaObtenerOrganizacionPorId(){
        Long idEsperado = 1L;
        Organizacion organizacion = organizacionDao.buscarPorId(1L);
        emf.close();
        assertEquals(idEsperado, organizacion.getId());
    }

}