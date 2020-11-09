package org.wcci.campuslibraries.resources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Campus {
    private String location;
    private String techStack;
    @Id
    @GeneratedValue
    private Long id;

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
}
