package sn.bacomputer.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sn.bacomputer.DTO.OrderDTO;
import sn.bacomputer.DTO.OrderItemDTO;
import sn.bacomputer.entity.Order;
import sn.bacomputer.entity.OrderItem;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T18:46:02+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( order.getId() );
        orderDTO.setItems( orderItemListToOrderItemDTOList( order.getItems() ) );

        return orderDTO;
    }

    @Override
    public Order toEntity(OrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( dto.getId() );
        order.setItems( orderItemDTOListToOrderItemList( dto.getItems() ) );

        return order;
    }

    protected OrderItemDTO orderItemToOrderItemDTO(OrderItem orderItem) {
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

    protected List<OrderItemDTO> orderItemListToOrderItemDTOList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDTO> list1 = new ArrayList<OrderItemDTO>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemToOrderItemDTO( orderItem ) );
        }

        return list1;
    }

    protected OrderItem orderItemDTOToOrderItem(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setQuantity( orderItemDTO.getQuantity() );
        orderItem.setPrice( orderItemDTO.getPrice() );

        return orderItem;
    }

    protected List<OrderItem> orderItemDTOListToOrderItemList(List<OrderItemDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItem> list1 = new ArrayList<OrderItem>( list.size() );
        for ( OrderItemDTO orderItemDTO : list ) {
            list1.add( orderItemDTOToOrderItem( orderItemDTO ) );
        }

        return list1;
    }
}
