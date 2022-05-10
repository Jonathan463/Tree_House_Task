

package com.treehouseapp.payload;

import com.treehouseapp.model.flowers.MenuItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFetchAllFlowersResponse {
    private String status;
    private List<MenuItem> menuItemList;
}
