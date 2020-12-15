package com.ravig.todo.service;

import com.ravig.todo.model.ItemStatus;
import com.ravig.todo.model.ToDoList;
import com.ravig.todo.resource.ItemRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class ToDoListServiceImpTest {

    private static ToDoListService toDoListService;

    @Before
    public void setup() {
        ToDoList toDoList = ToDoList.ToDoListBuilder.aToDoList()
                .withListId(1)
                .withOwner("Sam")
                .build();
        List<ToDoList> toDoLists = new ArrayList<>();
        toDoLists.add(toDoList);
        toDoListService = new ToDoListServiceImp(toDoLists);
    }

    @Test
    public void shouldAddItemsToUser() {
        ItemRequest itemRequest = generateItemRequest("Suncorp Interview", ItemStatus.ACTIVE);
        ToDoList response = toDoListService.addItem(1, itemRequest);
        assertEquals(1, response.getItems().size());
        assertEquals(ItemStatus.ACTIVE, response.getItems().get(0).getStatus());
        assertEquals("Suncorp Interview", response.getItems().get(0).getDescription());

        ItemRequest aptItemRequest = generateItemRequest("Afterpay Interview", ItemStatus.COMPLETED);
        ToDoList addMoreItems = toDoListService.addItem(1, aptItemRequest);

        assertEquals(2, addMoreItems.getItems().size());
        assertEquals("Afterpay Interview", addMoreItems.getItems().get(1).getDescription());
        assertEquals(ItemStatus.COMPLETED, addMoreItems.getItems().get(1).getStatus());
    }

    @Test
    public void shouldReturnUserItemsWhenGet() {
        ToDoList beforeItems = toDoListService.getItems(1);
        assertEquals(0, beforeItems.getItems().size());

        ItemRequest itemRequest = generateItemRequest("Suncorp Interview", ItemStatus.ACTIVE);
        toDoListService.addItem(1, itemRequest);

        ToDoList afterItems = toDoListService.getItems(1);
        assertEquals(1, afterItems.getItems().size());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnErrorIfUserNotExistWhenGet() {
        toDoListService.getItems(2);
    }

    @Test
    public void shouldDeleteItem() {
        ItemRequest itemRequest = generateItemRequest("Suncorp Interview", ItemStatus.ACTIVE);
        ToDoList itemsBeforeDelete = toDoListService.addItem(1, itemRequest);
        assertEquals(1, itemsBeforeDelete.getItems().size());

        ToDoList itemsAfterDelete = toDoListService.deleteItem(1, itemsBeforeDelete.getItems().get(0).getItemId());
        assertEquals(0, itemsAfterDelete.getItems().size());
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnErrorIfDeleteItemNotFound() {
        toDoListService.deleteItem(1, 1);
    }

    @Test
    public void shouldUpdateItem() {
        ItemRequest item1 = generateItemRequest("Suncorp Interview", ItemStatus.ACTIVE);
        ItemRequest item2 = generateItemRequest("Afterpay Interview", ItemStatus.ACTIVE);
        toDoListService.addItem(1, item1);
        ToDoList itemsBeforeUpdate = toDoListService.addItem(1, item2);

        assertEquals(2, itemsBeforeUpdate.getItems().size());
        assertEquals("Afterpay Interview", itemsBeforeUpdate.getItems().get(1).getDescription());
        assertEquals(ItemStatus.ACTIVE, itemsBeforeUpdate.getItems().get(1).getStatus());

        ItemRequest updateRequest = generateItemRequest("Afterpay Interview", ItemStatus.COMPLETED);
        ToDoList itemsAfterUpdate = toDoListService.updateItem(1, itemsBeforeUpdate.getItems().get(1).getItemId(), updateRequest);

        assertEquals(2, itemsAfterUpdate.getItems().size());
        assertEquals("Afterpay Interview", itemsAfterUpdate.getItems().get(1).getDescription());
        assertEquals(ItemStatus.COMPLETED, itemsAfterUpdate.getItems().get(1).getStatus());
    }


    @Test(expected = NoSuchElementException.class)
    public void shouldReturnErrorIfUpdatedItemNotFound() {
        toDoListService.updateItem(1, 1, new ItemRequest());
    }

    private ItemRequest generateItemRequest(String description, ItemStatus status) {
        return ItemRequest.ItemRequestBuilder.anItemRequest()
                .withDate(new Date())
                .withDescription(description)
                .withStatus(status)
                .build();
    }
}