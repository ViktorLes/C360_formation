package com.viseo.c360.formation.domain.training;


import com.sun.istack.internal.NotNull;
import com.viseo.c360.formation.domain.BaseEntity;
import javax.persistence.OneToMany;
import java.util.List;


public class Topic extends BaseEntity {

    @NotNull
    String name;

    @NotNull
    @OneToMany
    List<Training>listTraining;

    public Topic(List<Training> listTraining) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Training> getListTraining() {
        return listTraining;
    }

    public void addListTraining (Training training) {
        this.listTraining.add(training);
    }

    public void removeListTraining(Training training) {
        this.listTraining.remove(training);
    }
}