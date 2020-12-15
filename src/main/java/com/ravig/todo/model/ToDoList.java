package com.ravig.todo.model;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private long listId;
    private String owner;
    private List<Item> items;

    public long getListId() {
        return listId;
    }

    public void setListId(long listId) {
        this.listId = listId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Item> getItems() {
        return items != null ? items : new ArrayList<>();
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    public static final class ToDoListBuilder {
        private long listId;
        private String owner;
        private List<Item> items;

        private ToDoListBuilder() {
        }

        public static ToDoListBuilder aToDoList() {
            return new ToDoListBuilder();
        }

        public ToDoListBuilder withListId(long listId) {
            this.listId = listId;
            return this;
        }

        public ToDoListBuilder withOwner(String owner) {
            this.owner = owner;
            return this;
        }

        public ToDoListBuilder withItems(List<Item> items) {
            this.items = items;
            return this;
        }

        public ToDoList build() {
            ToDoList toDoList = new ToDoList();
            toDoList.setListId(listId);
            toDoList.setOwner(owner);
            toDoList.setItems(items);
            return toDoList;
        }
    }
}
