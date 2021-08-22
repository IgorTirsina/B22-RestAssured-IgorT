package com.cybertek.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
//we add lombok annotations, to make our object class more readable, and less code lines
@Getter
@Setter
@ToString
public class Region {

    //in case we want to name our variables from json file, in case they have space in between
    //Json annotations above the  instance variable we want to
    @JsonProperty("region_id")
    private int regionId;
    private String region_name;
    private List<Links> links;

}
