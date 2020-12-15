package com.ravig.todo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Item {
    private int itemId;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private ItemStatus status;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }


    public static final class ItemBuilder {
        private int itemId;
        private String description;
        private Date date;
        private ItemStatus status;

        private ItemBuilder() {
        }

        public static ItemBuilder anItem() {
            return new ItemBuilder();
        }

        public ItemBuilder withItemId(int itemId) {
            this.itemId = itemId;
            return this;
        }

        public ItemBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public ItemBuilder withStatus(ItemStatus status) {
            this.status = status;
            return this;
        }

        public Item build() {
            Item item = new Item();
            item.setItemId(itemId);
            item.setDescription(description);
            item.setDate(date);
            item.setStatus(status);
            return item;
        }
    }
}
