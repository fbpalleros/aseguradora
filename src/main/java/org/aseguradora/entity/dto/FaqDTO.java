package org.aseguradora.entity.dto;



public class FaqDTO {
    private String question;
    private String answer;

    public FaqDTO(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // getters y setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}