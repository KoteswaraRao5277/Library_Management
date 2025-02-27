package com.Library.mgmt.Service;

import com.Library.mgmt.Enums.BookFilter;
import com.Library.mgmt.Service.Impl.BookNoFilterImpl;
import com.Library.mgmt.Service.Impl.BookTitleFilterImpl;
import com.Library.mgmt.Service.Impl.BookTypeFilterImpl;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BookFilterFactory {

    private final Map<BookFilter, BookFilterStratergy> stratergies = new HashMap<>();

    public BookFilterFactory(BookNoFilterImpl bookNoFilter, BookTitleFilterImpl bookTitleFilter, BookTypeFilterImpl bookTypeFilter){
        stratergies.put(BookFilter.BOOK_NO, bookNoFilter);
        stratergies.put(BookFilter.BOOK_TITLE, bookTitleFilter);
        stratergies.put(BookFilter.BOOK_TYPE, bookTypeFilter);
    }

    public BookFilterStratergy getStratergy(BookFilter filter){
        return stratergies.get(filter);
    }
}
