package com.viseo.c360.formation.dto.training;


import com.sun.istack.internal.NotNull;
import com.viseo.c360.formation.dto.BaseDTO;

import java.util.List;

public class TopicDescription extends BaseDTO {

    String name;

    public TopicDescription() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}