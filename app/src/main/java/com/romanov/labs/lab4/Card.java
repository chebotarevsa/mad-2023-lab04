package com.romanov.labs.lab4;

import java.io.Serializable;

public class Card implements Serializable{
    private long id;
    private String question;
    private String example;
    private String answer;
    private String translate;
    private String imageURI;

    public Card(long id, String question, String example, String answer, String translate, String imageURI) {
        this.id = id;
        this.question = question;
        this.example = example;
        this.answer = answer;
        this.translate = translate;
        this.imageURI = imageURI;
    }

    public Card() {
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", example='" + example + '\'' +
                ", answer='" + answer + '\'' +
                ", translate='" + translate + '\'' +
                ", imageURI=" + imageURI +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }
}
