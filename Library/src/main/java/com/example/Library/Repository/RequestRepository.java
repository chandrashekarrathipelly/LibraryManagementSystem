package com.example.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library.Entities.Request;

public interface RequestRepository extends JpaRepository<Request,Long>{

    
}
