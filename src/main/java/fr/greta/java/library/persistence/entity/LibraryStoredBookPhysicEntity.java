package fr.greta.java.library.persistence.entity;


import fr.greta.java.user.persistence.entity.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "book_physic_store")
@DiscriminatorValue("STORED")
public class LibraryStoredBookPhysicEntity extends LibraryAbstractBookPhysicEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelf_id")
    private LibraryShelfEntity shelf;

    public LibraryShelfEntity getShelf() {
        return shelf;
    }

    public void setShelf(LibraryShelfEntity shelf) {
        this.shelf = shelf;
    }
}
