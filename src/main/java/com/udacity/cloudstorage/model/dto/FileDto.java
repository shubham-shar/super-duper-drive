package com.udacity.cloudstorage.model.dto;

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
public class FileDto {

    private int id;
    private String name;
    private String dataURL;
}
