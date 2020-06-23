package com.udacity.cloudstorage.model.dto;

import javax.swing.*;

import com.udacity.cloudstorage.model.Credentials;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shubham sharma
 *         <p>
 *         23/06/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialDto extends Credentials {
    private String decryptedPassword;
    
    public CredentialDto(String decryptedPassword, int credentialid, String url, String username,
                         String key, String password){
        super(credentialid, url, username, key, password);
        this.decryptedPassword = decryptedPassword;
    }
}
