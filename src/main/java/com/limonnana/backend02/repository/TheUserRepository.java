package com.limonnana.backend02.repository;

import com.limonnana.backend02.entity.TheUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface TheUserRepository  extends JpaRepository<TheUser, Long> {


    @Transactional
    @Modifying
    @Query(value = "UPDATE the_user u set name =:name , email =:email, phone =:phone, password =:password where u.id = :id",
          nativeQuery = true)
    void updateUser(@Param("name") String name, @Param("email") String email, @Param("phone") String phone, @Param("password") String password,  @Param("id") Long id);

    TheUser findByEmail(String email);
}
