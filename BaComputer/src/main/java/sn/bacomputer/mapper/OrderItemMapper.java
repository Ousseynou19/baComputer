package sn.bacomputer.mapper;

import org.mapstruct.Mapper;
import sn.bacomputer.DTO.OrderItemDTO;
import sn.bacomputer.entity.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemDTO toDTO(OrderItem orderItem);
    OrderItem toEntity(OrderItemDTO dto);
}
