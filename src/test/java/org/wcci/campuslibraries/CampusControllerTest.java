package org.wcci.campuslibraries;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.wcci.campuslibraries.controllers.CampusController;
import org.wcci.campuslibraries.resources.Campus;
import org.wcci.campuslibraries.storage.BookRepository;
import org.wcci.campuslibraries.storage.CampusStorage;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CampusControllerTest {


    private CampusStorage campusStorage;
    private CampusController underTest;

    @BeforeEach
    void setUp() {
        campusStorage = mock(CampusStorage.class);
        BookRepository bookRepo = mock(BookRepository.class);
        underTest = new CampusController(campusStorage, bookRepo);
        when(campusStorage.retrieveAllCampuses()).thenReturn(Collections.singletonList(new Campus("Test Town", "FORTRAN")));
    }

    @Test
    public void shouldRetrieveAllCampuses(){
        //Arrangement
        //Action
        Iterable<Campus> campuses = underTest.retrieveAllCampuses();
        //Assertion
        assertThat(campuses).contains(new Campus("Test Town", "FORTRAN"));

    }
    @Test
    public void retrieveAllCampusesShouldBeMappedCorrectly() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        mockMvc.perform(get("/api/campuses"))
                .andExpect(status().isOk());
    }


}
