package org.restapi.repo;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.restapi.entity.ItemEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemRepository implements PanacheRepositoryBase<ItemEntity, Long> {

    public PanacheQuery<ItemEntity> findOpenItem(String status){
        return find("SELECT i FROM Item i WHERE i.status = ?1", status).page(0,2);
    }

}
