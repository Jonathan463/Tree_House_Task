package com.treehouseapp.payload;


import com.treehouseapp.dto.ViewOrderDTO;
import com.treehouseapp.model.users.ShippingAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewOrderDetailsResponse {
    private List<ViewOrderDTO> viewOrderDtoList;
    private Optional<ShippingAddress> shippingAddress;
}
