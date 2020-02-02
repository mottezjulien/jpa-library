package fr.greta.java.library.persistence.entity;

import fr.greta.java.book.persistence.BookDetailsEntity;
import fr.greta.java.library.LibraryBookPhysicState;

import javax.persistence.*;

@Entity
@Table(name = "book_physic")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "state")
public abstract class LibraryAbstractBookPhysicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String reference;

	@Column(insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private LibraryBookPhysicState state;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "details_id")
	private BookDetailsEntity details;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public LibraryBookPhysicState getState() {
		return state;
	}

	public void setState(LibraryBookPhysicState state) {
		this.state = state;
	}

	public BookDetailsEntity getDetails() {
		return details;
	}

	public void setDetails(BookDetailsEntity details) {
		this.details = details;
	}
}