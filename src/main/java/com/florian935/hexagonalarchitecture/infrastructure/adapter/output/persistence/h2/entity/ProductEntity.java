package com.florian935.hexagonalarchitecture.infrastructure.adapter.output.persistence.h2.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class ProductEntity {
    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    Long productId;

    @Column(name = "label")
    String label;
}
