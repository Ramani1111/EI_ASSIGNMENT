package adapter;

// Adapter pattern: make a MediaPlayer compatible with AdvancedMediaPlayer
interface MediaPlayer { void play(String audioType, String fileName); }
class AudioPlayer implements MediaPlayer {
    private final MediaAdapter adapter;
    AudioPlayer(MediaAdapter adapter) { this.adapter = adapter; }
    public void play(String audioType, String fileName) {
        if ("mp3".equalsIgnoreCase(audioType)) System.out.println("Playing mp3: " + fileName);
        else adapter.play(audioType, fileName);
    }
}
interface AdvancedMediaPlayer { void playVlc(String fileName); void playMp4(String fileName); }
class VlcPlayer implements AdvancedMediaPlayer {
    public void playVlc(String fileName){ System.out.println("Playing vlc: " + fileName); }
    public void playMp4(String fileName){}
}
class Mp4Player implements AdvancedMediaPlayer {
    public void playVlc(String fileName){} public void playMp4(String fileName){ System.out.println("Playing mp4: " + fileName); }
}
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) advancedPlayer = new VlcPlayer();
        else if (audioType.equalsIgnoreCase("mp4")) advancedPlayer = new Mp4Player();
    }
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) advancedPlayer.playVlc(fileName);
        else if (audioType.equalsIgnoreCase("mp4")) advancedPlayer.playMp4(fileName);
    }
}
public class Main {
    public static void main(String[] args) {
        MediaAdapter adapter = new MediaAdapter("vlc");
        AudioPlayer player = new AudioPlayer(adapter);
        player.play("mp3", "song.mp3"); // direct
        player.play("vlc", "movie.vlc"); // via adapter
    }
}
