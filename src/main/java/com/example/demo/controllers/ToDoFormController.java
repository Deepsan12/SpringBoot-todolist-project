package com.example.demo.controllers;


import com.example.demo.models.ToDoItem;
import com.example.demo.services.ToDoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ToDoFormController {

    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/create-todo")
    public String showCreateForm(ToDoItem toDoItem){
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createToDoItem(@Valid ToDoItem todoItem, BindingResult result, Model model){
        ToDoItem item= new ToDoItem();
        item.setDescription(todoItem.getDescription());
        item.setComplete(todoItem.isComplete());
        toDoItemService.save(todoItem);
        return "redirect:/";
    }

    // @PathVariable annotation handles template variables, ie the id variable in index.html
    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") Long id){
        ToDoItem todoItem = toDoItemService.getById(id).orElseThrow(()->
                new IllegalArgumentException("ToDoItem id" + id + "not found"));
        toDoItemService.delete(todoItem);
        return "redirect:/";

    }

    // Model object contains data from controller method to pass onto view
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        ToDoItem todoItem = toDoItemService.getById(id).orElseThrow(()->
                new IllegalArgumentException("ToDoItem id" + id + "not found"));
        model.addAttribute("todo", todoItem);
        return "edit-todo-item";

    }

    @PostMapping("/todo/{id}")
    public String updateToDoItem(@PathVariable("id") Long id,
                                 @Valid ToDoItem item, BindingResult result, Model model){
        ToDoItem todoItem = toDoItemService.getById(id).orElseThrow(()->
                new IllegalArgumentException("ToDoItem id" + id + "not found"));
        todoItem.setDescription(item.getDescription());
        todoItem.setUpdatedAt(item.getUpdatedAt());
        todoItem.setComplete(item.isComplete());

        toDoItemService.save(todoItem);
        return "redirect:/";
    }
}
