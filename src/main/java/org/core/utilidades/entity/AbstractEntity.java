package org.core.utilidades.entity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.logging.Logger;
@MappedSuperclass
public class AbstractEntity implements Serializable{
    @Transient
    protected final Logger logger = Logger.getLogger(AbstractEntity.class.getName());
    @Transient
    @PersistenceContext(unitName = "persistence_core")
    EntityManager entityManager;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    protected Long id;

    public AbstractEntity(){}

    public Logger getLogger() { return logger; }
    public EntityManager getEntityManager() { return entityManager; }
    public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }
    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }
}
