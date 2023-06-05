package ru.job4j.dish.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * Oywayten 05.06.2023.
 */
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Positive(message = "Id must be greater than 0",
            groups = {Operation.OnUpdate.class, Operation.OnFind.class, Operation.OnPatch.class})
    private long id;

    @NotBlank(message = "name must be not empty", groups = {Operation.OnUpdate.class, Operation.OnRegistration.class})
    private String name;

    @NotBlank(message = "name must be not empty", groups = {Operation.OnPatch.class})
    private String description;
}
