package com.ravig.todo.resource;

import com.ravig.todo.model.ToDoList;
import com.ravig.todo.service.ToDoListService;
import com.ravig.todo.service.ToDoListServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/items")
public class ToDoListResource {

    private ToDoListService toDoListService;

    public ToDoListResource() {
        this.toDoListService = new ToDoListServiceImp(new ArrayList<>());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoList> getItems(@RequestParam(required = true) long listId) {

        try {
            ToDoList items = toDoListService.getItems(listId);
            if (items == null)
                return ResponseEntity.notFound().build();
            return new ResponseEntity<ToDoList>(items, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoList> deleteItem(@RequestParam long listId,
                                               @RequestParam int itemId) {
        try {
            ToDoList items = toDoListService.deleteItem(listId, itemId);
            if (items == null)
                return ResponseEntity.notFound().build();
            return new ResponseEntity<ToDoList>(items, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoList> addItem(@RequestParam long listId,
                                            @RequestBody ItemRequest itemRequest) {
        try {
            ToDoList items = toDoListService.addItem(listId, itemRequest);
            if (items == null)
                return ResponseEntity.notFound().build();
            return new ResponseEntity<ToDoList>(items, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoList> updateItem(@RequestParam long listId,
                                               @RequestParam int itemId,
                                               @RequestBody ItemRequest itemRequest) {
        try {
            ToDoList items = toDoListService.updateItem(listId, itemId, itemRequest);
            if (items == null)
                return ResponseEntity.notFound().build();
            return new ResponseEntity<ToDoList>(items, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
