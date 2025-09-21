package sn.bacomputer.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sn.bacomputer.DTO.OrderItemDTO;
import sn.bacomputer.entity.OrderItem;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T18:46:02+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDTO toDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();

        if ( orderItem.getQuantity() != null ) {
            orderItemDTO.setQuantity( orderItem.getQuantity() );
        }
        if ( orderItem.getPrice() != null ) {
            orderItemDTO.setPrice( orderItem.getPrice() );
        }

        return orderItemDTO;
    }

    @Override
    public OrderItem toEntity(OrderItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setQuantity( dto.getQuantity() );
        orderItem.setPrice( dto.getPrice() );

        return orderItem;
    }
}
