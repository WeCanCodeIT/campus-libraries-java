package org.wcci.campuslibraries;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.wcci.campuslibraries.resources.Author;
import org.wcci.campuslibraries.resources.Book;
import org.wcci.campuslibraries.resources.Campus;
import org.wcci.campuslibraries.storage.AuthorRepository;
import org.wcci.campuslibraries.storage.BookRepository;
import org.wcci.campuslibraries.storage.CampusRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaWiringTest {

    @Autowired
    private CampusRepository campusRepo;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private AuthorRepository authorRepo;
    @Autowired
    private BookRepository bookRepo;

    private void flushAndClear() {
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    public void campusRepoShouldSaveAndRetrieveCampusObjects() {
        Campus testCampus = new Campus("location", "techStack");
        campusRepo.save(testCampus);
        flushAndClear();
        Campus retrievedCampus = campusRepo.findById(testCampus.getId()).get();
        assertThat(retrievedCampus).isEqualTo(testCampus);
    }

    @Test
    public void authorRepoShouldSaveAndRetrieveAuthorObjects(){
        Author testAuthor = new Author("authorName");
        authorRepo.save(testAuthor);
        flushAndClear();
        Author retrievedAuthor = authorRepo.findById(testAuthor.getId()).get();
        assertThat(retrievedAuthor).isEqualTo(testAuthor);
    }

    @Test
    public void booksHaveManyAuthorsAndACampus(){
        Author testAuthor1 = new Author("authorName");
        authorRepo.save(testAuthor1);
        Author testAuthor2 = new Author("another authorName");
        authorRepo.save(testAuthor2);
        Campus testCampus = new Campus("location", "techStack");
        campusRepo.save(testCampus);
        Book testBook = new Book(null, "title", "summary", true, testAuthor1, testAuthor2);
        bookRepo.save(testBook);
        flushAndClear();
        Book retrievedBook = bookRepo.findById(testBook.getId()).get();
        assertThat(retrievedBook.getAuthors()).contains(testAuthor1,testAuthor2);
//        assertThat(retrievedBook.getCampus()).isEqualTo(testCampus);
        assertThat(retrievedBook).isEqualTo(testBook);
    }
    @Test
    public void campusShouldHaveBooks(){
        Author testAuthor1 = new Author("authorName");
        authorRepo.save(testAuthor1);
        Author testAuthor2 = new Author("another authorName");
        authorRepo.save(testAuthor2);
        Campus testCampus = new Campus("location", "techStack");
        campusRepo.save(testCampus);
        Book testBook = new Book(testCampus, "title", "summary", true, testAuthor1, testAuthor2);
        bookRepo.save(testBook);
        flushAndClear();
        Campus retrievedCampus = campusRepo.findById(testCampus.getId()).get();
        assertThat(retrievedCampus.getBooks()).contains(testBook);
    }
    @Test
    public void authorShouldHaveBooks(){
        Author testAuthor1 = new Author("authorName");
        authorRepo.save(testAuthor1);
        Author testAuthor2 = new Author("another authorName");
        authorRepo.save(testAuthor2);
        Campus testCampus = new Campus("location", "techStack");
        campusRepo.save(testCampus);
        Book testBook = new Book(testCampus, "title", "summary", true, testAuthor1, testAuthor2);
        bookRepo.save(testBook);
        flushAndClear();
        Author retrievedAuthor = authorRepo.findById(testAuthor1.getId()).get();
        assertThat(retrievedAuthor.getBooks()).contains(testBook);
    }
}
