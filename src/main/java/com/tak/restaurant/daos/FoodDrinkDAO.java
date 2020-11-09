package com.tak.restaurant.daos;

import com.tak.restaurant.models.Food_Drink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDrinkDAO extends JpaRepository<Food_Drink, Integer> {
}
