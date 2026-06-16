package com.example.hkt1.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "watches")
@JacksonXmlRootElement(localName = "watch")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "không được để trống")
    @Column(name = "model_name", nullable = false)
    private String modelName;

    @NotBlank(message = "không dược để trống")
    @Column(nullable = false)
    private String brand;

    @NotNull
    @Positive(message = "price phải lớn hơn 0")
    @Column(nullable = false)
    private Double price;

    @NotNull
    @Pattern(
            regexp = "MECHANICAL|AUTOMATIC|QUARTZ",
            message = "phải thuộc các loại MECHANICAL|AUTOMATIC|QUARTZ"
    )
    @Column(name = "movement_type", nullable = false)
    private String movementType;

    @NotNull
    @Pattern(
            regexp = "IN_STOCK|SOLD|REPAIRING",
            message = "phải thuộc các loại IN_STOCK|SOLD|REPAIRING"
    )
    @Column(nullable = false)
    private String status;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
}
