package com.ravig.todo.service;

import com.ravig.todo.model.Item;
import com.ravig.todo.model.ToDoList;
import com.ravig.todo.resource.ItemRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ToDoListServiceImp implements ToDoListService {
    private List<ToDoList> toDoLists;
    private int itemId;

    public ToDoListServiceImp(List<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }

    @Override
    public ToDoList getItems(long listId) {
        ToDoList toDoList = returnTodoList(listId);
        return toDoList;
    }

    @Override
    public ToDoList deleteItem(long listId, int itemId) {
        ToDoList toDoList = returnTodoList(listId);
        Item item = returnItem(toDoList, itemId);
        toDoList.getItems().remove(item);
        return toDoList;
    }

    @Override
    public ToDoList addItem(long listId, ItemRequest item) {
        ToDoList toDoList = toDoLists.stream()
                .filter(list -> list.getListId() == listId)
                .findAny().orElse(ToDoList.ToDoListBuilder
                        .aToDoList()
                        .withItems(new ArrayList<>())
                        .withListId(listId)
                        .build());

        Item itemDAO = Item.ItemBuilder.anItem()
                .withDate(item.getDate())
                .withDescription(item.getDescription())
                .withStatus(item.getStatus())
                .withItemId(itemId++).build();

        toDoLists.remove(toDoList);
        List<Item> items = toDoList.getItems();
        items.add(itemDAO);
        toDoList.setItems(items);
        toDoLists.add(toDoList);
        return toDoList;
    }

    @Override
    public ToDoList updateItem(long listId, int itemId, ItemRequest updatedItem) {
        ToDoList toDoList = returnTodoList(listId);
        Item item = returnItem(toDoList, itemId);

        Item itemDAO = Item.ItemBuilder.anItem().withDate(updatedItem.getDate())
                .withDescription(updatedItem.getDescription())
                .withStatus(updatedItem.getStatus())
                .withItemId(item.getItemId()).build();
        toDoList.getItems().remove(item);
        toDoList.getItems().add(itemDAO);
        return toDoList;
    }

    private Item findItem(long listId, int itemId) {
        return toDoLists.stream().filter(list -> list.getListId() == listId)
                .flatMap(todoList -> todoList.getItems().stream())
                .filter(it -> it.getItemId() == itemId).findAny().orElseThrow(NoSuchElementException::new);
    }

    private ToDoList returnTodoList(long listId) {
        return toDoLists.stream()
                .filter(list -> list.getListId() == listId)
                .findAny().orElseThrow(NoSuchElementException::new);
    }

    private Item returnItem(ToDoList toDoList, int itemId) {
        return toDoList.getItems().stream().filter(it -> it.getItemId() == itemId).findAny()
                .orElseThrow(NoSuchElementException::new);
    }
}
