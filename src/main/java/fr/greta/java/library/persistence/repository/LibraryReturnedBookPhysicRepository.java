package fr.greta.java.library.persistence.repository;

import fr.greta.java.generic.persistence.GenericRepository;
import fr.greta.java.library.persistence.entity.LibraryBorrowedBookPhysicEntity;
import fr.greta.java.library.persistence.entity.LibraryEntity;
import fr.greta.java.library.persistence.entity.LibraryReturnedBookPhysicEntity;

import javax.persistence.EntityManager;

public class LibraryReturnedBookPhysicRepository extends GenericRepository<LibraryReturnedBookPhysicEntity> {

    public LibraryReturnedBookPhysicRepository(EntityManager entityManager) {
        super(entityManager, LibraryReturnedBookPhysicEntity.class);
    }

    public void update(LibraryBorrowedBookPhysicEntity book, LibraryEntity library) {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("UPDATE book_physic SET state = 'RETURNED', library_id = ?, user_id = null WHERE id = ?")
                .setParameter(1,library.getId())
                .setParameter(2,book.getId())
                .executeUpdate();
        entityManager.getTransaction().commit();
    }


}
