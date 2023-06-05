package ru.job4j.dish.repository;

import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.dish.model.Dish;

import java.util.List;

/**
 * Oywayten 05.06.2023.
 */
public interface DishRepository extends CrudRepository<Dish, Long> {

    @NonNull
    List<Dish> findAll();

}
