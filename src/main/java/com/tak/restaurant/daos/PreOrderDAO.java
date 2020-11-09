package com.tak.restaurant.daos;

import com.tak.restaurant.models.PreOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreOrderDAO extends JpaRepository<PreOrder, Integer> {
}
