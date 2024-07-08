package org.core.utilidades.entity;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
@MappedSuperclass
public class AbstractEntity implements Serializable{
    @Transient
    protected final Logger logger = Logger.getLogger(getClass().getSimpleName());
//    @Transient
//    @PersistenceContext(unitName = "persistence_core")
//    EntityManager entityManager;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    public AbstractEntity(){
        getLogger().log(Level.FINER, "Creando entidad " + this.getClass().getName());
    }

    public Logger getLogger() { return logger; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
