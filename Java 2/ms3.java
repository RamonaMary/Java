package mmms;

import java.util.Scanner;

class Song {
    protected String title, composer;
    protected int duration;

    public Song(String title, String composer, int duration) {
        this.title = title;
        this.composer = composer;
        this.duration = duration;
    }

    public void display() {
        System.out.println(title + " by " + composer + " (" + duration + " seconds)");
    }
}

public class ms3 extends Song {
    private String playlistName;
    private Song[] songs;
    private int songCount = 0;

    public ms3(String playlistName, int maxSongs) {
        super("Playlist", "System", 2);
        this.playlistName = playlistName;
        this.songs = new Song[maxSongs];
    }

    public boolean addSong(String title, String composer, int duration) {
        if (songCount < songs.length) {
            songs[songCount] = new Song(title, composer, duration);
            songCount++;
            System.out.println(title + " added to playlist: " + playlistName);
            return true; // Successfully added
        } else {
            System.out.println("Playlist is full!");
            return false; // Playlist full, stop further input
        }
    }

    public void displayPlaylist() {
        System.out.println("\nPlaylist: " + playlistName);
        if (songCount == 0) {
            System.out.println("No songs in the playlist.");
            return;
        }
        for (int i = 0; i < songCount; i++) {
            System.out.print((i + 1) + ". ");
            songs[i].display();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();

        int maxSongs;
        while (true) {
            System.out.print("Enter maximum number of songs: ");
            if (scanner.hasNextInt()) {
                maxSongs = scanner.nextInt();
                scanner.nextLine();
                if (maxSongs > 0) break;
                else System.out.println("Please enter a positive number.");
            } else {
                System.out.println("Invalid input! Enter a valid number.");
                scanner.next();
            }
        }

        ms3 playlist = new ms3(playlistName, maxSongs);

        boolean continueAdding = true;
        while (continueAdding) {
            System.out.print("\nEnter song title: ");
            String title = scanner.nextLine();
            System.out.print("Enter composer: ");
            String composer = scanner.nextLine();

            int duration;
            while (true) {
                System.out.print("Enter duration (in seconds): ");
                if (scanner.hasNextInt()) {
                    duration = scanner.nextInt();
                    scanner.nextLine();
                    if (duration > 0) break;
                    else System.out.println("Duration must be positive.");
                } else {
                    System.out.println("Invalid input! Enter a valid number.");
                    scanner.next();
                }
            }

            if (!playlist.addSong(title, composer, duration)) {
                break;
            }

            System.out.print("Do you want to add another song? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                continueAdding = false; 
            }
        }

        playlist.displayPlaylist();
        System.out.println("\nThank you for using the music playlist program!");

        scanner.close();
    }
}
