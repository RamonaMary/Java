package mmms;

import java.util.Scanner;

public class ms2 {

    private String title, composer;
    private int duration;
    private String playlistName;
    private ms2[] songs; 
    private int songCount = 0; 
    public ms2(String playlistName, int maxSongs) {
        this.playlistName = playlistName;
        this.songs = new ms2[maxSongs]; 
    }

    public void addSongToPlaylist(String title, String composer, int duration) {
        if (songCount < songs.length) {
            songs[songCount] = new ms2(title, composer, duration); 
            songCount++;
            System.out.println(title + " by " + composer + " (" + duration + " seconds) added to the playlist " + playlistName);
        } else {
            System.out.println("Playlist is full!.");
        }
    }
    public void addSongToPlaylist(ms2[] newSongs) {
        for (ms2 song : newSongs) {
            if (songCount < songs.length) {
                songs[songCount] = song;
                songCount++;
                System.out.println(song.title + " by " + song.composer + " (" + song.duration + " seconds) added to the playlist " + playlistName);
            } else {
                System.out.println("Playlist is full!");
                break;
            }
        }
    }
    public ms2(String title, String composer, int duration) {
        this.title = title;
        this.composer = composer;
        this.duration = duration;
    }

    public void displayPlaylist() {
        System.out.println("\nPlaylist: " + playlistName);
        for (int i = 0; i < songCount; i++) {
            System.out.println((i + 1) + ". " + songs[i].title + " by " + songs[i].composer + " (" + songs[i].duration + " seconds)");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        System.out.print("Enter maximum number of songs for the playlist: ");
        int maxSongs = scanner.nextInt();
        scanner.nextLine(); 

        ms2 playlist = new ms2(playlistName, maxSongs);

        while (true) {
            System.out.println("\nEnter details for a new song:");
            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Composer: ");
            String composer = scanner.nextLine();
            System.out.print("Duration: ");
            int duration = scanner.nextInt();
            scanner.nextLine(); 

            playlist.addSongToPlaylist(title, composer, duration);

            System.out.print("Do you want to add another song? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("no")) break;
        }

        playlist.displayPlaylist();
        System.out.println(".........................");
        System.out.println("Thank you!!");

        scanner.close();
    }
}
