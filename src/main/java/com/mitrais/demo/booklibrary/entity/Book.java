package com.mitrais.demo.booklibrary.entity;

import javax.persistence.*;

@Entity
public class Book { // book

    public enum Status {
        AVAILABLE,
        NOT_AVAILABLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private Integer totalPages; // total_pages
    private String category;
    private String ISBN;
    private Status availability;

    @OneToOne
    private Shelf shelf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Status getAvailability() {
        return availability;
    }

    public void setAvailability(Status availability) {
        this.availability = availability;
    }

    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) {
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", totalPages=" + totalPages +
                ", category='" + category + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", availability=" + availability +
                ", shelf=" + shelf +
                '}';
    }
}


