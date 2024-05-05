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
    @GetMapping("/admin/notes")
    public ResponseEntity<List<Note>> AdminNotes() {
        return new ResponseEntity<>(adminService.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<Note> AdminGetById(@PathVariable Long id){
        return new ResponseEntity<>(adminService.getById(id),HttpStatus.FOUND);
    }

    @PostMapping("/admin/create")
    public ResponseEntity<Note> AdminCreateNewNote(@RequestBody Note note){
        return new ResponseEntity<>(adminService.createNote(note), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Note> AdminDeleteNote(@PathVariable Long id){
        adminService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/admin/edit/{id}")
    public ResponseEntity<Note> AdminEditNote(@PathVariable Long id, @RequestBody Note note){
        return new ResponseEntity<>(adminService.editNote(id,note), HttpStatus.I_AM_A_TEAPOT);
    }

    @GetMapping("/user/notes")
    public ResponseEntity<List<Note>> UserNotes() {
        return new ResponseEntity<>(userService.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Note> UserGetById(@PathVariable Long id){
        return new ResponseEntity<>(userService.getById(id),HttpStatus.FOUND);
    }

    @PostMapping("/user/create")
    public ResponseEntity<Note> UserCreateNewNote(@RequestBody Note note){
        return new ResponseEntity<>(userService.createNote(note), HttpStatus.CREATED);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Note> UserDeleteNote(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/edit/{id}")
    public ResponseEntity<Note> UserEditNote(@PathVariable Long id, @RequestBody Note note){
        return new ResponseEntity<>(userService.editNote(id,note), HttpStatus.I_AM_A_TEAPOT);
    }

}
