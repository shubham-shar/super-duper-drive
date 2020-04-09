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
public class Notes {
    private int noteid;
    private String notetitle;
    private String notedescription;
}
