import java.util.ArrayList;
import java.util.Iterator;

class MusicPlayer extends Thread {
    ArrayList<String> playlist;

    MusicPlayer(ArrayList<String> playlist) {
        this.playlist = playlist;
    }

    public void run() {
        try {
            Iterator<String> iterator = playlist.iterator();
            while (iterator.hasNext()) {
                String song = iterator.next();
                System.out.println("\nPlaying: " + song);
                Thread.sleep(300); // Simulating song playback
            }
        } catch (InterruptedException e) {
            System.out.println("Music Stopped!");
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("Error: Playlist modified while playing!");
        }
    }
}

public class MusicManager {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<String> playlist = new ArrayList<>();
        playlist.add("Wildflower");
        playlist.add("Cry");
        
        MusicPlayer player = new MusicPlayer(playlist);
        player.start(); // Start music player

        // Simulating song addition while playing
        Thread.sleep(5000);
        System.out.println("\nAdding new song: Blues");
        playlist.add("Blues"); // This will cause ConcurrentModificationException
    }
}
