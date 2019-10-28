package com.limonnana.backend02.repository;


import com.limonnana.backend02.entity.IpSecure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpSecureRepository extends JpaRepository<IpSecure, Integer> {


}
