package sn.bacomputer.DTO;


import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}

