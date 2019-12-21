package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notebook")
    public String getNotebook(Model model)
    {
        model.addAttribute("notes",noteService.getNotes());
        model.addAttribute("newNote",new Note());
        return "main-view";
    }

    @PostMapping("/notebook/add-note")
    public String addNote(Model model, @ModelAttribute Note note)
    {
        if(note.getTitle()==null || note.getTitle() == null)
        {
            model.addAttribute("errorMessage","You have to fill in the title and text, to save the note");
        }
        else {
            noteService.saveNote(note.getTitle(), note.getText());
        }
        return "redirect:/notebook";
    }

    @GetMapping("/notebook/{id}")
    public String getNoteById(Model model, @PathVariable Long id)
    {
        Note note = null;
        try {
            note = noteService.findNoteById(id);
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage","Note not found");
        }
        model.addAttribute("note", note);
        return "show-note";
    }

    @RequestMapping(value = "/notebook/{id}/edit", method = {RequestMethod.POST,RequestMethod.GET})
    public String editNoteById(Model model, @PathVariable Long id,@ModelAttribute Note updatedNode)
    {
        if(updatedNode!=null)
        {
            noteService.update(updatedNode);
        }
        Note note = null;
        try {
            note = noteService.findNoteById(id);
        }
        catch (Exception e)
        {
            model.addAttribute("errorMessage","Note not found");
        }
        model.addAttribute("edit",true);
        model.addAttribute("note", note);

        return "show-note";
    }

    @PostMapping("/notebook/{id}/delete")
    public String deleteNote(@PathVariable Long id)
    {
        noteService.deleteNote(id);
        return "redirect:/notebook";
    }

}
