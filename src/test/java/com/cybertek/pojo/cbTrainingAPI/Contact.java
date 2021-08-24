package com.cybertek.pojo.cbTrainingAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {

    private Integer contactId;
    private String phone;
    private String emailAddress;
    @JsonProperty("premanentAddress")
    private String permanentAddress;


}
