package ca.gbc.comp3074.mind_manager_app.Models;

import java.util.ArrayList;
import java.util.List;

public class Question{
    private int id;
    private String questionText;
    private ArrayList<Answer> answers;

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public Question(int id, String questionText) {
        this.id = id;
        this.questionText = questionText;
    }

    public Question(int id, String questionText, ArrayList<Answer> answers) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", answers=" + answers +
                '}';
    }
}
