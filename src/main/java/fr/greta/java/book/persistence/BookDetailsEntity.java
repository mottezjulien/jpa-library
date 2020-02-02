package fr.greta.java.book.persistence;

import javax.persistence.*;


@Entity
@Table(name = "book_details")
public class BookDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private BookAuthorEntity author;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookAuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(BookAuthorEntity author) {
        this.author = author;
    }
}
