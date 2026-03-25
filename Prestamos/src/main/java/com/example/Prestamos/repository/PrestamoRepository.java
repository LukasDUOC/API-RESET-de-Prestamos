package com.example.Prestamos.repository;

import com.example.Prestamos.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepository  extends JpaRepository<Prestamo,Long>{

    
}
