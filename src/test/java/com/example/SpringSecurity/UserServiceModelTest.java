package com.example.SpringSecurity;


import com.example.SpringSecurity.domain.Note;
import com.example.SpringSecurity.repository.NoteRepository;
import com.example.SpringSecurity.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceModelTest {

    @Mock
    NoteRepository noteRepository;
    @InjectMocks
    UserService userService;

    @Test
    public void getAllNotesTest(){

        // блок предусловия
        List<Note> expectedNotes = new ArrayList<>();
        Note note1 = new Note();
        Note note2 = new Note();
        note1.setHeader("h1");
        note1.setBody("b1");
        note2.setHeader("h2");
        note2.setBody("b2");
        expectedNotes.add(note1);
        expectedNotes.add(note2);

        given(noteRepository.findAll()).willReturn(expectedNotes);

        //Блок действия
        List<Note> actualNotes = userService.getAllNotes();

        // Блок проверки
        assertEquals(expectedNotes, actualNotes);

    }

    @Test
    public void getByIdTest(){
        //блок предусловия
        List<Note> notes = new ArrayList<>();
        Note note1 = new Note();
        Note note2 = new Note();
        note1.setHeader("h1");
        note1.setBody("b1");
        note2.setHeader("h2");
        note2.setBody("b2");
        notes.add(note1);
        notes.add(note2);

        given(noteRepository.findById(1L)).willReturn(Optional.of(note1));

        //блок действия
        Note note3 = userService.getById(1L);

        //блок проверки
        assertEquals(note3,note1);
    }

    @Test
    public void createNoteTest(){
        //блок предусловия
        Note note = new Note();
        note.setHeader("h1");
        note.setBody("b1");

        //блок действия
        Note createdNote = userService.createNote(note);

        //блок проверки
        verify(noteRepository).save(note);
    }

    @Test
    public void deleteByIdNoteTest(){
        Long id = 1L;
        userService.deleteById(id);
        verify(noteRepository, times(1)).deleteById(id);
    }

    @Test
    public void editNoteTest(){
        Long id = 1L;
        Note note = new Note();
        note.setHeader("h2");
        note.setBody("b2");

        Note oldNote = new Note();
        oldNote.setHeader("h1");
        oldNote.setBody("b1");

        when(noteRepository.findById(id)).thenReturn(java.util.Optional.of(oldNote));
        when(noteRepository.save(oldNote)).thenReturn(oldNote);

        // Act
        Note result = userService.editNote(id, note);

        // Assert
        assertEquals("h2", result.getHeader());
        assertEquals("b2", result.getBody());
    }
}
