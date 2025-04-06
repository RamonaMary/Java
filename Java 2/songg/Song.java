package music;

public class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public void display() {
        System.out.println("Now Playing: " + title + " by " + artist);
    }
}
