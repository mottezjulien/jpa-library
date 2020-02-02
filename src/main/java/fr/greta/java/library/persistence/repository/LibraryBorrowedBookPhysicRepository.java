package fr.greta.java.library.persistence.repository;

import fr.greta.java.generic.persistence.GenericRepository;
import fr.greta.java.library.persistence.entity.LibraryBorrowedBookPhysicEntity;
import fr.greta.java.library.persistence.entity.LibraryStoredBookPhysicEntity;
import fr.greta.java.user.persistence.entity.UserEntity;

import javax.persistence.EntityManager;

public class LibraryBorrowedBookPhysicRepository extends GenericRepository<LibraryBorrowedBookPhysicEntity> {

    public LibraryBorrowedBookPhysicRepository(EntityManager entityManager) {
        super(entityManager, LibraryBorrowedBookPhysicEntity.class);
    }

    public void update(LibraryStoredBookPhysicEntity book, UserEntity user) {
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("UPDATE book_physic SET state = 'BORROWED', user_id = ?, shelf_id = null WHERE id = ?")
                .setParameter(1,user.getId())
                .setParameter(2,book.getId())
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
