package com.example.demo;

import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private NoteService noteService;

    @Autowired
    public Start(NoteService noteService) {
        this.noteService = noteService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get()
    {
      //  noteService.saveNote();
    }
}
