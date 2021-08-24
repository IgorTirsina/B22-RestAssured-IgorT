package com.cybertek.pojo.cbTrainingAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class Company {

    private String companyName;
    private String title;
    private String startDate;
    private Address address;

}
