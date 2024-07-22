package com.library.library1.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.library.library1.entity.BookAllotment;
import com.library.library1.serviece.BookAllotmentService;
import com.library.library1.serviece.BookService;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BookAllotmentControllTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookAllotmentService bookAllotmentService;
    @MockBean
    private BookService bookService;
    @InjectMocks
    private BookAllotmentController bookAllotmentController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mvc = MockMvcBuilders.standaloneSetup(bookAllotmentController).build();
    }

    @Test
    public void getBookAllotmentTest() throws Exception {
        BookAllotment mockBookAllotment = mock(BookAllotment.class);
        System.out.println(mockBookAllotment);
        when(bookAllotmentService.getBookAllotmentByBa_id(mockBookAllotment.getBa_id())).thenReturn(mockBookAllotment);
        mvc.perform(get("/bookallotment/get/ba_id/{ba_id}", mockBookAllotment.getBa_id()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getBookAllotmentExceptionTest() throws Exception {
        when(bookAllotmentService.getBookAllotmentByBa_id(50001L)).thenThrow(new RuntimeException());
        mvc.perform(get("/bookallotment/get/ba_id/{ba_id}", 50001))
                .andExpect(status().isOk())
                .andExpect(content().string("not found"));
    }
}
