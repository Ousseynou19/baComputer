package sn.bacomputer.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.validation.constraints.*;

/**
 * A DTO for the {@link sn.bacomputer.entity.Service} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nom;



    // Lombok génère automatiquement les getters, setters, equals, hashCode et toString
}
