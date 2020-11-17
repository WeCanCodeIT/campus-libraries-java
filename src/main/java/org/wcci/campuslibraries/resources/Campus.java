package org.wcci.campuslibraries.resources;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Campus {
    private String location;
    private String techStack;
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "campus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;

    protected Campus() {
    }

    public Campus(String location, String techStack) {
        this.location = location;
        this.techStack = techStack;
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void changeLocation(String newLocation) {
        location = newLocation;
    }

    public String getTechStack() {
        return techStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campus campus = (Campus) o;
        return Objects.equals(location, campus.location) &&
                Objects.equals(techStack, campus.techStack) &&
                Objects.equals(id, campus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, techStack, id);
    }

    public List<Book> getBooks() {
        return books;
    }

}
