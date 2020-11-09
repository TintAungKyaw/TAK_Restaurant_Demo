package com.tak.restaurant.daos;

import com.tak.restaurant.models.Food_Table;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTableDAO extends JpaRepository<Food_Table, Integer> {
}
