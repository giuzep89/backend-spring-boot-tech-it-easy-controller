package com.novi.techiteasycontroller.repositories;
import com.novi.techiteasycontroller.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
