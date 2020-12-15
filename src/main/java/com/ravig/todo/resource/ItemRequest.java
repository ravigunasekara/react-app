package com.ravig.todo.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ravig.todo.model.ItemStatus;

import java.util.Date;


public class ItemRequest {
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private ItemStatus status;

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


    public static final class ItemRequestBuilder {
        private String description;
        private Date date;
        private ItemStatus status;

        private ItemRequestBuilder() {
        }

        public static ItemRequestBuilder anItemRequest() {
            return new ItemRequestBuilder();
        }

        public ItemRequestBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemRequestBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public ItemRequestBuilder withStatus(ItemStatus status) {
            this.status = status;
            return this;
        }

        public ItemRequest build() {
            ItemRequest itemRequest = new ItemRequest();
            itemRequest.setDescription(description);
            itemRequest.setDate(date);
            itemRequest.setStatus(status);
            return itemRequest;
        }
    }
}
