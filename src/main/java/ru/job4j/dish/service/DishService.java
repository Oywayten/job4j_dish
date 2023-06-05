package ru.job4j.dish.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.dish.exeption.DishNotFoundException;
import ru.job4j.dish.model.Dish;
import ru.job4j.dish.repository.DishRepository;

import java.util.List;
import java.util.Optional;

/**
 * Oywayten 05.06.2023.
 */
@Service
@AllArgsConstructor
@Slf4j
public class DishService {

    public static final String DISH_NOT_FOUND_BY_ID_S = "Dish not found by id = %s";
    private final DishRepository dishRepository;

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public Optional<Dish> findById(long id) {
        return dishRepository.findById(id);
    }

    public Dish save(Dish dish) {
        try {
            dish.setId(0);
            dish = dishRepository.save(dish);
        } catch (DataIntegrityViolationException e) {
            log.error("Error save dish", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not sign-up");
        }
        return dish;
    }

    public boolean update(Dish dish) {
        Dish savedDish = null;
        if (dishRepository.existsById(dish.getId())) {
            savedDish = dishRepository.save(dish);
        }
        return dish.equals(savedDish);
    }

    public boolean delete(Dish dish) {
        long id = dish.getId();
        if (!dishRepository.existsById(id)) {
            throw new DishNotFoundException("Dish is not found by id = %s".formatted(id));
        }
        dishRepository.delete(dish);
        return true;
    }
}
