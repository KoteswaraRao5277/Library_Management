package com.Library.mgmt.Controller;

import com.Library.mgmt.Enums.Operator;
import com.Library.mgmt.Enums.UserFilter;
import com.Library.mgmt.Model.User;
import com.Library.mgmt.Service.Impl.UserService;
import com.Library.mgmt.dto.UserCreationRequest;
import com.Library.mgmt.dto.UserCreationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addStudent")
    public UserCreationResponse addStudent(@RequestBody @Validated UserCreationRequest request){
        return userService.addStudent(request);
    }

    @GetMapping("/filter")
    public List<User> filteredUser(@RequestParam("filterBy") UserFilter filterBy,
                               @RequestParam("operator") Operator operator,
                               @RequestParam("value") String value){
        return userService.filter(filterBy,operator,value);

    }


}
