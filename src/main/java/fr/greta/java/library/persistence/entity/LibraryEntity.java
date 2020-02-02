package fr.greta.java.library.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "library")
public class LibraryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "library")
    private Set<LibraryShelfEntity> shelves;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LibraryShelfEntity> getShelves() {
        return shelves;
    }

    public void setShelves(Set<LibraryShelfEntity> shelves) {
        this.shelves = shelves;
    }
}
