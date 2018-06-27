/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Kenny Camba
 */
public class Video {
    
    private MediaPlayer media;
    private long duration;

    public Video(MediaPlayer media, long duration) {
        this.media = media;
        this.duration = duration;
    }

    public MediaPlayer getMedia() {
        return media;
    }

    public void setMedia(MediaPlayer media) {
        this.media = media;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
    
    
}
