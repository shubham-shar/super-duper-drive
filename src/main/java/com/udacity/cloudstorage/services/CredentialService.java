package com.udacity.cloudstorage.services;

import com.udacity.cloudstorage.mapper.CredentialMapper;
import com.udacity.cloudstorage.model.Credentials;
import com.udacity.cloudstorage.model.dto.CredentialDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Shubham Sharma
 * @date 8/4/20
 */
@Service
public class CredentialService {

    @Autowired
    private CredentialMapper credentialMapper;

    @Autowired
    private EncryptionService encryptionService;

    public List<CredentialDto> getAllCredentials(int userId) throws Exception {
        List<Credentials> credentials = credentialMapper.findByUserId(userId);
        if (Objects.isNull(credentials)) {
            return Collections.emptyList();
        }
        return credentials.stream()
                          .map(this::buildCredentialDto)
                          .collect(Collectors.toList());
    }
    
    private CredentialDto buildCredentialDto(Credentials credentials) {
        CredentialDto credentialDto = new CredentialDto(decryptPassword(credentials),
                                                        credentials.getCredentialid(), credentials.getUrl(),
                                                        credentials.getUsername(), credentials.getKey(),
                                                        credentials.getPassword());
        return credentialDto;
    }
    
    public Credentials getCredentialById(int id){
        return credentialMapper.findById(id);
    }

    public void addCredential(Credentials credential, int userid) {
        credentialMapper.insertCredentials(encryptPassword(credential), userid);
    }

    public void updateCredential(Credentials credential) {
        credentialMapper.updateCredentials(encryptPassword(credential));
    }

    public void deleteCredential(int credentialid) {
        credentialMapper.deleteCredentials(credentialid);
    }

    private Credentials encryptPassword(Credentials credential) {
        String key = RandomStringUtils.random(16, true, true);
        credential.setKey(key);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), key));
        return credential;
    }

    private String decryptPassword(Credentials credential) {
        return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }
}
