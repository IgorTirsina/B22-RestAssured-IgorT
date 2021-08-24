package com.cybertek.pojo.cbTrainingAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class Students {

    private String firstName;
    private String lastName;
    private Integer batch;
    private String section;
    private String gender;
    private Contact contact;
    private Company company;


}
