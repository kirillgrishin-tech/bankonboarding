package ru.alfabank.practice.kagrishin.bankonboarding.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Discount {

    @EqualsAndHashCode.Include
    private final String id;
    private String name;
    private List<String> applicableProductIds;
    private Integer percent;
    private Boolean isEnable;
    private LocalDateTime created;
}
