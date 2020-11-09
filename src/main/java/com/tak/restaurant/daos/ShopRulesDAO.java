package com.tak.restaurant.daos;

import com.tak.restaurant.models.ShopRules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRulesDAO extends JpaRepository<ShopRules, Integer> {
}