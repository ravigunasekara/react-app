package com.ravig.todo.service;

import com.ravig.todo.model.ToDoList;
import com.ravig.todo.resource.ItemRequest;

public interface ToDoListService {
    ToDoList getItems(long listId);
    ToDoList deleteItem(long listId, int itemId);
    ToDoList addItem(long listId, ItemRequest item);
    ToDoList updateItem(long listId, int itemId, ItemRequest updatedItem);


}
