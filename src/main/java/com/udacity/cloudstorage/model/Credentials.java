package com.udacity.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shubham Sharma
 * @date 6/4/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

    private int credentialid;
    private String url;
    private String username;
    private String key;
    private String password;

}
