package com.example.SpringSecurity.controller.rest;

import com.example.SpringSecurity.domain.Note;
import com.example.SpringSecurity.service.AdminService;
import com.example.SpringSecurity.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
public class HomeController {

    private final UserService userService;

    private final AdminService adminService;

    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return new ResponseEntity<>(adminService.AdminFeature(),HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Note>> user() {
        return new ResponseEntity<>(userService.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getById(id),HttpStatus.FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Note> createNewNote(@RequestBody Note note){
        return new ResponseEntity<>(userService.createNote(note), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Note> editNote(@PathVariable Long id, @RequestBody Note note){
        return new ResponseEntity<>(userService.editNote(id,note), HttpStatus.I_AM_A_TEAPOT);
    }

}
