package org.restapi.resource;

import lombok.extern.slf4j.Slf4j;
import org.restapi.dto.Item;
import org.restapi.entity.ItemEntity;
import org.restapi.service.ItemService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class ItemResource {

    @Inject
    ItemService itemService;

    @GET
    public List<ItemEntity> get(){
        return itemService.get();
    }

    @GET
    @Path("/open-items")
    public List<ItemEntity> getOpenItems(@QueryParam("status") String status){
        return itemService.findOpenItem(status);
    }

    @POST
    public Response create(Item item){
        ItemEntity itemEntity = itemService.create(item);
        return Response.ok(itemEntity).status(201).build();
    }

    @PUT
    public Response update(Item item){
        try{
            ItemEntity itemEntity = itemService.update(item);
            return Response.ok(itemEntity).status(201).build();
        } catch (Exception e){
            log.info(String.valueOf(e));
            return Response.status(500).build();
        }

    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        itemService.delete(id);
    }
}
