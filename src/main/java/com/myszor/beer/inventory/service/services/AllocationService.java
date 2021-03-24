package com.myszor.beer.inventory.service.services;

import com.myszor.brewery.model.BeerOrderDto;

public interface AllocationService {

    boolean allocateOrder(BeerOrderDto beerOrderDto);

}
