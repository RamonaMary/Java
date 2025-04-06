import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Song {
    String title;
    String artist;
    double duration; 

    public Song(String title, String artist, double duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "🎵 " + title + " - " + artist + " (" + duration + " min)";
    }
}

class MusicManager {
    ArrayList<Song> songList = new ArrayList<>();

    public void addSong(String title, String artist, double duration) {
        songList.add(new Song(title, artist, duration));
        System.out.println(" Song added successfully!");
    }

    public void removeSong(String title) {
        boolean found = false;
        for (Song song : songList) {
            if (song.title.equalsIgnoreCase(title)) {
                songList.remove(song);
                System.out.println(" Song removed successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(" Song not found!");
        }
    }

    public void displaySongs() {
        if (songList.isEmpty()) {
            System.out.println(" No songs in your collection!");
            return;
        }
        System.out.println("\n Your Music Collection:");
        for (Song song : songList) {
            System.out.println(song);
        }
    }

    public void searchSong(String title) {
        for (Song song : songList) {
            if (song.title.equalsIgnoreCase(title)) {
                System.out.println(" Song Found: " + song);
                return;
            }
        }
        System.out.println(" Song not found!");
    }

    public void sortSongs() {
        Collections.sort(songList, Comparator.comparing(s -> s.title));
        System.out.println(" Songs sorted alphabetically!");
    }
}

public class collection {
    public static void main(String[] args) {
        MusicManager manager = new MusicManager();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n Music Management System");
            System.out.println("1] Add Song");
            System.out.println("2] Remove Song");
            System.out.println("3] Display Songs");
            System.out.println("4] Search Song");
            System.out.println("5] Sort Songs");
            System.out.println("6] Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter duration (minutes): ");
                    double duration = scanner.nextDouble();
                    manager.addSong(title, artist, duration);
                    break;

                case 2:
                    System.out.print("Enter song title to remove: ");
                    String removeTitle = scanner.nextLine();
                    manager.removeSong(removeTitle);
                    break;

                case 3:
                    manager.displaySongs();
                    break;

                case 4:
                    System.out.print("Enter song title to search: ");
                    String searchTitle = scanner.nextLine();
                    manager.searchSong(searchTitle);
                    break;

                case 5:
                    manager.sortSongs();
                    break;

                case 6:
                    System.out.println(" Exiting Music Management System. Goodbye!");
                    break;

                default:
                    System.out.println(" Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
