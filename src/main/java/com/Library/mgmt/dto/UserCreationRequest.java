package com.Library.mgmt.dto;

import com.Library.mgmt.Enums.UserType;
import com.Library.mgmt.Model.User;
import com.Library.mgmt.Enums.UserStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreationRequest {

    private String userName;

    @NotBlank(message = "user email must not be blank")
    private String userEmail;

    private String userAddress;

    private String userPhone;

    public User toUser() {
        return User.builder().
                name(this.userName).
                email(this.userEmail).
                phoneNo(this.userPhone).
                address(this.userAddress).
                userStatus(UserStatus.ACTIVE).
                build();
    }
}
