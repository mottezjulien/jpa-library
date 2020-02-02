package fr.greta.java.library.persistence.repository;

import fr.greta.java.generic.persistence.GenericRepository;
import fr.greta.java.library.persistence.entity.LibraryBorrowedBookPhysicEntity;
import fr.greta.java.library.persistence.entity.LibraryReturnedBookPhysicEntity;
import fr.greta.java.library.persistence.entity.LibraryShelfEntity;
import fr.greta.java.library.persistence.entity.LibraryStoredBookPhysicEntity;

import javax.persistence.EntityManager;

public class LibraryStoredBookPhysicRepository extends GenericRepository<LibraryStoredBookPhysicEntity> {

    public LibraryStoredBookPhysicRepository(EntityManager entityManager) {
        super(entityManager, LibraryStoredBookPhysicEntity.class);
    }

    public void update(LibraryReturnedBookPhysicEntity book, LibraryShelfEntity shelf) {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("UPDATE book_physic SET state = 'STORED', shelf_id = ?, library_id = null WHERE id = ?")
                .setParameter(1,shelf.getId())
                .setParameter(2,book.getId())
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
