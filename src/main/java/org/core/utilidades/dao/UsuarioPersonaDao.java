package org.core.utilidades.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.core.utilidades.entity.Persona;
import org.core.utilidades.entity.UsuarioPersona;

import java.util.List;

public class UsuarioPersonaDao extends AbstractDao<UsuarioPersona>{
    public UsuarioPersonaDao(){ super(); }
    public UsuarioPersonaDao(EntityManager em){ super(em); }

    public UsuarioPersona buscarPorId(Long id){
        return super.buscarPorId(UsuarioPersona.class, id);
    }
    public List<UsuarioPersona> obtenerTodo(){
        return super.obtenerTodo(UsuarioPersona.class);
    }
    public UsuarioPersona buscarPorPersona(Persona persona){
        TypedQuery<UsuarioPersona> query = entityManager.createNamedQuery("UsuarioPersona.findByPersona", UsuarioPersona.class);
        query.setParameter("persona", persona);
        return query.getSingleResult();
    }
    public UsuarioPersona buscarPorUsuario(String usuario){
        TypedQuery<UsuarioPersona> query = entityManager.createNamedQuery("UsuarioPersona.findByUsuario", UsuarioPersona.class);
        query.setParameter("usuario", usuario);
        return query.getSingleResult();
    }

}
