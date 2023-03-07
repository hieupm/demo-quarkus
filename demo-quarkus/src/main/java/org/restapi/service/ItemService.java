package org.restapi.service;

import org.restapi.dto.Item;
import org.restapi.entity.ItemEntity;
import org.restapi.repo.ItemRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ItemService {

    @Inject
    ItemRepository itemRepository;
    public List<ItemEntity> get() {
        List<ItemEntity> listAll = ItemEntity.findAll().list();
        return listAll;
    }

    public List<ItemEntity> findOpenItem(String status){
        return itemRepository.findOpenItem(status).list();
    }

    @Transactional
    public ItemEntity create(Item item) {
        ItemEntity ie = new ItemEntity();
        ie.name = item.getName();
        ie.count = item.getCount();
        ie.status = item.getStatus();
        ie.persist();
        return ie;
    }

    @Transactional
    public ItemEntity update(Item item) {
        Long itemId = item.getId();
        ItemEntity ie = ItemEntity.findById(itemId);

        if(Objects.isNull(ie)){
            throw new NullPointerException("There is no item with ID: " + itemId);
        }

        ie.name = item.getName();
        ie.count = item.getCount();
        ie.status = item.getStatus();
        return ie;
    }

    @Transactional
    public void delete(Long id) {
        ItemEntity.deleteById(id);
    }

}

