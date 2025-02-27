package com.Library.mgmt.Service;

import com.Library.mgmt.Enums.Operator;
import com.Library.mgmt.dto.BookFilterResponse;

import java.util.List;

public interface BookFilterStratergy {

    List<BookFilterResponse> getFilteredBook(Operator operator, String value);
}
