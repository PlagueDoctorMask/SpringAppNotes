package com.example.SpringSecurity.service;

import com.example.SpringSecurity.domain.Note;
import com.example.SpringSecurity.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

//    public String getText() {
//        return "User";
//    }
    private final NoteRepository noteRepository;

    /**
     * вывод всеx заметок
     * @return - список заметок
     */
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }


    /**
     * Получение заметки по Id
     * @param id - Id ребуемой заметки
     * @return - заметка с соответствующим id, либо null
     */
    public Note getById(Long id){
        return noteRepository.findById(id).orElse(null);
    }

    /**
     * Создание заметки
     * @param note - данные заметки, которую надо сохранить
     * @return - созданная заметка
     */
    public Note createNote(Note note){
        note.setCreationDate(LocalDateTime.now());
        return noteRepository.save(note);
    }


    /**
     * Удаление заметки по Id
     * @param id - id заметки, которую необходимо удалить
     */
    public void deleteById(Long id){
        noteRepository.deleteById(id);
    }


    /**
     * Редактирование заметки по id
     * @param id - id заметки, которую надо редактировать
     * @param note - объект с данными, которые надо вставить в выбранную заметку
     * @return - отредактированная заметка, либо исключение с текстом об отстутствии заметки с выбранным id
     */
    public Note editNote(Long id, Note note){
        Note oldNote = noteRepository.findById(id).orElse(null);
        if(oldNote != null){
            oldNote.setHeader(note.getHeader());
            oldNote.setBody(note.getBody());
            noteRepository.save(oldNote);
            return oldNote;
        }else{
            throw new RuntimeException("No such note");
        }

    }

}
