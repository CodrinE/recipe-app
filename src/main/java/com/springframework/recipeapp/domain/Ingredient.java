package com.springframework.recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String description;

    @NonNull
    private BigDecimal amount;

    @OneToOne
    @NonNull
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne
    @NonNull
    private Recipe recipe;
}
