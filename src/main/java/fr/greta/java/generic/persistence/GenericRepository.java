package fr.greta.java.generic.persistence;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenericRepository<Entity> {

    private final Class<Entity> clazz;
    protected EntityManager entityManager;

    public GenericRepository(EntityManager entityManager, Class<Entity> clazz) {
        this.entityManager = entityManager;
        this.clazz = clazz;
    }

    public List<Entity> findAll() {
        TypedQuery<Entity> query = entityManager.createQuery(
                "select e from " + clazz.getSimpleName() + "  e", clazz);
        return query.getResultList();
    }

    public Entity findById(int id) {
        return entityManager.find(clazz, id);
    }

    public Entity create(Entity obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
        return obj;
    }

    public Entity update(Entity obj) {
        entityManager.getTransaction().begin();
        entityManager.merge(obj);
        entityManager.getTransaction().commit();
        return obj;
    }

}
