package ru.job4j.dish.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dish.exeption.DishNotFoundException;
import ru.job4j.dish.model.Dish;
import ru.job4j.dish.model.Operation;
import ru.job4j.dish.service.DishService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.job4j.dish.service.DishService.DISH_NOT_FOUND_BY_ID_S;

/**
 * Oywayten 05.06.2023.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/dishes")
public class DishController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DishController.class.getSimpleName());
    private final ObjectMapper objectMapper;
    private final DishService dishService;

    @PostMapping("/registration")
    @Validated(Operation.OnRegistration.class)
    public ResponseEntity<Dish> registration(@Valid @RequestBody Dish dish) {
        return new ResponseEntity<>(
                dishService.save(dish),
                new MultiValueMapAdapter<>(Map.of("Title", List.of("registration"))),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Dish>> findAll() {
        List<Dish> dishList = dishService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .header("Title", "dishList")
                .body(dishList);
    }

    @GetMapping("/{id}")
    @Validated(Operation.OnFind.class)
    public ResponseEntity<Dish> findById(@Valid @PathVariable int id) {
        Optional<Dish> dishOptional = dishService.findById(id);
        if (dishOptional.isEmpty()) {
            throw new DishNotFoundException(DISH_NOT_FOUND_BY_ID_S.formatted(id));
        }
        return new ResponseEntity<>(
                dishOptional.orElseThrow(() -> new DishNotFoundException(DISH_NOT_FOUND_BY_ID_S.formatted(id))),
                HttpStatus.OK
        );
    }

    @PatchMapping
    @Validated(Operation.OnPatch.class)
    public ResponseEntity<Dish> patch(@Valid @RequestBody Dish dish) {
        long id = dish.getId();
        String description = dish.getDescription();
        Optional<Dish> dishOptional = dishService.findById(id);
        if (dishOptional.isEmpty()) {
            throw new DishNotFoundException(DISH_NOT_FOUND_BY_ID_S.formatted(id));
        }
        dish = dishOptional.get();
        dish.setDescription(description);
        boolean isUpdated = dishService.update(dish);
        return isUpdated
                ? ResponseEntity.ok(dish)
                : ResponseEntity.internalServerError().build();
    }

    @PutMapping
    @Validated(Operation.OnUpdate.class)
    public ResponseEntity<Dish> update(@Valid @RequestBody Dish dish) {
        return dishService.update(dish)
                ? ResponseEntity.ok(dish)
                : ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Dish dish = new Dish();
        dish.setId(id);
        return dishService.delete(dish)
                ? ResponseEntity.ok().header("Title", "delete").build()
                : ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(value = {DishNotFoundException.class})
    public void exceptionHandler(Exception e, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        LOGGER.error(e.getLocalizedMessage());
    }


}
