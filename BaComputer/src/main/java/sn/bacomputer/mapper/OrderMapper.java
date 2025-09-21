package sn.bacomputer.mapper;

import org.mapstruct.Mapper;
import sn.bacomputer.DTO.OrderDTO;
import sn.bacomputer.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO dto);
}
