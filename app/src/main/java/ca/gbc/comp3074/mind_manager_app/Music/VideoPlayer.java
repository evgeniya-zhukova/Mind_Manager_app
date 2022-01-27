package ca.gbc.comp3074.mind_manager_app.Music;

public class VideoPlayer {

    String videoUrl;

    public VideoPlayer() {
    }

    public VideoPlayer(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

}
