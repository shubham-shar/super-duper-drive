package com.udacity.cloudstorage.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udacity.cloudstorage.model.Credentials;
import com.udacity.cloudstorage.model.User;
import com.udacity.cloudstorage.services.CredentialService;
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
public class CredentialController {
    @Autowired
    private CredentialService credentialService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/credentials")
    public String saveOrUpdateCredentials(Authentication authentication, @ModelAttribute Credentials credential, Model model) {
        User user = (User) authentication.getPrincipal();

        if (Objects.nonNull(credentialService.getCredentialById(credential.getCredentialid()))) {
            credentialService.updateCredential(credential);
        } else {
            credentialService.addCredential(credential, user.getUserid());
        }
        model.addAttribute("success",true);
        return "result.html";
    }

    @GetMapping("/credentials/delete")
    public String deleteNote(@RequestParam("id") int credentialid, Model model) {
        if (Objects.nonNull(credentialService.getCredentialById(credentialid))) {
            credentialService.deleteCredential(credentialid);
            model.addAttribute("success",true);
            return "result.html";
        }
        model.addAttribute("error",true);
        return "result.html";
    }

}
