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
public class Files {
    private int fileid;
    private String filename;
    private String contenttype;
    private String filesize;
    private byte[] filedata;
}
