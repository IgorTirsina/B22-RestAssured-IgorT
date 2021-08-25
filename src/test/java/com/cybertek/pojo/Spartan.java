package com.cybertek.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//we use it for serialization, because ID will be added by the API automatically, so we dont have to setId(Object id);
@JsonIgnoreProperties(value = "id", allowSetters = true)
public class Spartan {
    private int id;
    private String name;
    private String gender;
    private long phone;


}
