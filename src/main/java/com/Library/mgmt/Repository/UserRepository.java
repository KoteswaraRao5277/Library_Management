package com.Library.mgmt.Repository;

import com.Library.mgmt.Model.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByName(String name);  //public is not required , as its a interface, so every method is public
    List<User> findByNameLike(String name);
	List<User> findByEmail(String email);
	List<User> findByEmailLike(String email);
	List<User> findByPhoneNo(String phoneNo);
	List<User> findByPhoneNoLike(String phoneNo);

//	User findUserByEmail(@NotBlank(message = "userEmail must not be blank") String email);
}
