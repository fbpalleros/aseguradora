package org.aseguradora.entity.dto;

import java.util.List;


public class SectionDTO {
    private String title;
    private List<FaqDTO> questions;

    public SectionDTO(String title, List<FaqDTO> questions) {
        this.title = title;
        this.questions = questions;
    }

    // getters y setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<FaqDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<FaqDTO> questions) {
        this.questions = questions;
    }
}
