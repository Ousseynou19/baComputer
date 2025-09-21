package sn.bacomputer.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigDecimal;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link sn.bacomputer.entity.Product} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private String imageUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;



    // Lombok génère automatiquement les getters, setters, equals, hashCode et toString
}
