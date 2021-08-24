package com.cybertek.pojo.cbTrainingAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class Address {

    private String street;
    private String city;
    private String state;
    private Integer zipCode;

}
