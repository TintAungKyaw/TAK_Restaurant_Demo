package com.tak.restaurant.daos;

import com.tak.restaurant.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterDAO extends JpaRepository<Register, Integer> {
}
