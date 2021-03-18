package com.myszor.beer.inventory.service.services;

import com.myszor.beer.inventory.service.config.JmsConfig;
import com.myszor.beer.inventory.service.domain.BeerInventory;
import com.myszor.beer.inventory.service.repositories.BeerInventoryRepository;
import com.myszor.brewery.model.BeerDto;
import com.myszor.brewery.model.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {
        BeerDto beerDto = event.getBeerDto();

        log.debug("Got inventory.");

        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(beerDto.getId())
                .upc(beerDto.getUpc())
                .quantityOnHand(beerDto.getQuantityOnHand())
                .build());
    }

}
