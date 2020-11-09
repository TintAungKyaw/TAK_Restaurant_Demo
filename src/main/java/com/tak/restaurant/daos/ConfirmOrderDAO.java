package com.tak.restaurant.daos;

import com.tak.restaurant.models.ConfirmOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmOrderDAO extends JpaRepository<ConfirmOrder, Integer> {
}
