package com.udacity.cloudstorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.cloudstorage.model.User;
import com.udacity.cloudstorage.services.CredentialService;
import com.udacity.cloudstorage.services.FilesService;
import com.udacity.cloudstorage.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Shubham Sharma
 * @date 8/4/20
 */
@Controller
public class HomeController {

    @Autowired
    private NotesService notesService;

    @Autowired
    private CredentialService credentialsService;

    @Autowired
    private FilesService filesService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value = { "/", "/home" })
    public String getHomePage(Authentication authentication, Model model) throws Exception {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("credentials", credentialsService.getAllCredentials(user.getUserid()));
        model.addAttribute("files", filesService.getAllFiles(user.getUserid()));
        model.addAttribute("notes", notesService.getAllNotes(user.getUserid()));
        return "home.html";
    }
}
