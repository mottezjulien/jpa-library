package fr.greta.java.library.persistence.entity;


import javax.persistence.*;

@Entity
@Table(name = "book_physic_return")
@DiscriminatorValue("RETURNED")
public class LibraryReturnedBookPhysicEntity extends LibraryAbstractBookPhysicEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id")
    private LibraryEntity library;

    public LibraryEntity getLibrary() {
        return library;
    }

    public void setLibrary(LibraryEntity library) {
        this.library = library;
    }

}
