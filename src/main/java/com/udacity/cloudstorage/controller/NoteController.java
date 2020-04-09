package com.udacity.cloudstorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.cloudstorage.model.Notes;
import com.udacity.cloudstorage.model.User;
import com.udacity.cloudstorage.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

/**
 * @author Shubham Sharma
 * @date 6/4/20
 */
@Controller
public class NoteController {

    @Autowired
    private NotesService notesService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/notes")
    public String createOrUpdateNote(Authentication authentication, Notes note, Model model) {
        User user = (User) authentication.getPrincipal();
        if (Objects.nonNull(notesService.getNoteById(note.getNoteid()))) {
            notesService.updateNote(note);
        } else {
            notesService.addNote(note, user.getUserid());
        }
        model.addAttribute("success",true);
        return "result.html";
    }

    @GetMapping("/notes/delete")
    public String deleteNote(@RequestParam("id") int noteid, Model model) {
        if (Objects.nonNull(notesService.getNoteById(noteid))) {
            notesService.deleteNote(noteid);
            model.addAttribute("success",true);
            return "result.html";
        }
        model.addAttribute("error",true);
        return "result.html";
    }
}
