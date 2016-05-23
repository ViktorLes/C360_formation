package com.viseo.c360.formation.dto.training;


import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TrainingDTO {

    @Id
    long id;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9+#'-. áàâäãåçéèêëíìîïñóòôöõúùûüýÿæ\u0153ÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝ\u0178Æ\u0152]")
    @Valid
    String trainingTitle;

    @NotNull
    @Pattern(regexp = "[0-9]")
    @Valid
    int numberHalfDays;

    public TrainingDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrainingTitle() {
        return trainingTitle;
    }

    public void setTrainingTitle(String trainingTitle) {
        this.trainingTitle = trainingTitle;
    }

    public int getNumberHalfDays() {
        return numberHalfDays;
    }

    public void setNumberHalfDays(int numberHalfDays) {
        this.numberHalfDays = numberHalfDays;
    }
}
