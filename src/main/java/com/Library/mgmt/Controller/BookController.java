package com.Library.mgmt.Controller;

import com.Library.mgmt.Enums.BookFilter;
import com.Library.mgmt.Enums.Operator;
import com.Library.mgmt.Model.User;
import com.Library.mgmt.Service.Impl.BookService;
import com.Library.mgmt.dto.BookCreationRequest;
import com.Library.mgmt.dto.BookCreationResponse;
import com.Library.mgmt.dto.BookFilterResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.extern.java.Log;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/addBook")
    public BookCreationResponse addStudent(@RequestBody BookCreationRequest request){
        LOGGER.info("request landing here in Book Controller is "+ request);
        return bookService.addBook(request);
    }

    @GetMapping("/filter")
    public List<BookFilterResponse> filteredUser(@NotNull(message = "filterBy must not be null") @RequestParam("filterBy") BookFilter filterBy,
                                                 @NotNull(message = "operator must not be null") @RequestParam("operator") Operator operator,
                                                 @NotBlank(message = "value must not be blank") @RequestParam("value") String value){
        return bookService.filter(filterBy,operator,value);

    }
}
