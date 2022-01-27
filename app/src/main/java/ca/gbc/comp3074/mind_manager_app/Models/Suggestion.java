package ca.gbc.comp3074.mind_manager_app.Models;

public class Suggestion {

    private int id;
    private String mood;
    private String categoryName;
    private String suggestionName;
    private String youtubeLink;
    private int quantity;

    public Suggestion(String categoryName) {
        this.categoryName = categoryName;
    }

    public Suggestion(String mood, int quantity) {
        this.mood = mood;
        this.quantity = quantity;
    }

    public Suggestion(String mood, String categoryName, String suggestionName, String youtubeLink) {
        this.mood = mood;
        this.categoryName = categoryName;
        this.suggestionName = suggestionName;
        this.youtubeLink = youtubeLink;
    }

    public Suggestion(int id, String mood, String categoryName, String suggestionName, String youtubeLink) {
        this.id = id;
        this.mood = mood;
        this.categoryName = categoryName;
        this.suggestionName = suggestionName;
        this.youtubeLink = youtubeLink;
     }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSuggestionName() {
        return suggestionName;
    }

    public void setSuggestionName(String suggestionName) {
        this.suggestionName = suggestionName;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Suggestion{" +
                "id=" + id +
                ", mood='" + mood + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", suggestionName='" + suggestionName + '\'' +
                ", youtubeLink='" + youtubeLink + '\'' +
                '}';
    }
}