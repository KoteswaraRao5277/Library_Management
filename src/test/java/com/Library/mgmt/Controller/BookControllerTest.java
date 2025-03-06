package com.Library.mgmt.Controller;

import com.Library.mgmt.Service.Impl.BookService;
import org.springframework.http.MediaType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void testAddBook() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bookTitle", "title");
        jsonObject.put("securityAmount", 100.0);
        jsonObject.put("bookNo", "bookNo");
        jsonObject.put("bookType", "EDUCATIONAL");
        jsonObject.put("authorEmail", "authorEmail");
        jsonObject.put("authorName", "authorName");

        mockMvc.perform(post("/book/addBook").
                contentType(MediaType.APPLICATION_JSON_VALUE).
                content(jsonObject.toString())).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
