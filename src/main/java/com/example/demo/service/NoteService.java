package com.example.demo.service;

import com.example.demo.model.Note;

import java.util.List;

public interface NoteService {
    void saveNote(String title, String text);
    List<Note> getNotes();
    void deleteNote(Long id);
    Note findNoteById(Long id);
    void update(Note note);
}
