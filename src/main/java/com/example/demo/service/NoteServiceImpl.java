package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {


    private NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void saveNote(@RequestParam String title ,@RequestParam String text) {
        String currentDate = getCurrentDate();
        noteRepository.save(new Note(currentDate,title,text));
    }

    private String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public void deleteNote(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        noteRepository.delete(optionalNote.get());
    }

    @Override
    public Note findNoteById(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        return optionalNote.get();
    }

    @Override
    public void update(Note note) {
        if(note.getText()!=null && note.getTitle()!=null) {
            note.setDate(getCurrentDate());
            noteRepository.save(note);
        }
    }
}
