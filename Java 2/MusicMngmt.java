import java.util.Scanner;

// Custom exception for invalid song duration
class InvalidDurationException extends Exception {
    InvalidDurationException(String message) {
        super(message);
    }
}

// Song class representing a music track
class Song {
    private String title;
    private String artist;
    private int duration; // Duration in seconds

    public Song(String title, String artist, int duration) throws InvalidDurationException {
        if (duration <= 0) {
            throw new InvalidDurationException("Duration must be greater than 0 seconds.");
        }
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    // Display song details
    public void displaySong() {
        System.out.println("Song: " + title + " | Artist: " + artist + " | Duration: " + duration + " sec");
    }

    public String getTitle() {
        return title;
    }
}

public class MusicMngmt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Song song1 = null, song2 = null, song3 = null;

        while (true) {
            System.out.println("\n MUSIC MANAGEMENT SYSTEM ");
            System.out.println("1 Add a Song");
            System.out.println("2 View All Songs");
            System.out.println("3 Choose a Song by Title");
            System.out.println("4 Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Adding a song manually into one of the slots
                    try {
                        System.out.print("Enter Song Title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter Artist Name: ");
                        String artist = scanner.nextLine();

                        System.out.print("Enter Duration (in seconds): ");
                        int duration = Integer.parseInt(scanner.nextLine());

                        Song newSong = new Song(title, artist, duration);

                        if (song1 == null) {
                            song1 = newSong;
                        } else if (song2 == null) {
                            song2 = newSong;
                        } else if (song3 == null) {
                            song3 = newSong;
                        } else {
                            System.out.println("Memory Full! Only 3 songs can be stored.");
                        }

                        System.out.println(" Song added successfully!");

                    } catch (InvalidDurationException e) {
                        System.out.println(" Error: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println(" Invalid duration! Please enter a valid number.");
                    }
                    break;

                case 2:
                    // Viewing all songs
                    System.out.println("\n LIST OF SONGS ");
                    if (song1 != null) song1.displaySong();
                    if (song2 != null) song2.displaySong();
                    if (song3 != null) song3.displaySong();
                    if (song1 == null && song2 == null && song3 == null) {
                        System.out.println(" No songs available.");
                    }
                    break;

                case 3:
                    // Choosing a song by title
                    if (song1 == null && song2 == null && song3 == null) {
                        System.out.println(" No songs available.");
                        break;
                    }
                    System.out.print("Enter the song title you want to choose: ");
                    String searchTitle = scanner.nextLine();
                    boolean found = false;

                    if (song1 != null && song1.getTitle().equalsIgnoreCase(searchTitle)) {
                        System.out.println("\n Song Found:");
                        song1.displaySong();
                        found = true;
                    } else if (song2 != null && song2.getTitle().equalsIgnoreCase(searchTitle)) {
                        System.out.println("\n Song Found:");
                        song2.displaySong();
                        found = true;
                    } else if (song3 != null && song3.getTitle().equalsIgnoreCase(searchTitle)) {
                        System.out.println("\n Song Found:");
                        song3.displaySong();
                        found = true;
                    }

                    if (!found) {
                        System.out.println("Song not found!");
                    }
                    break;

                case 4:
                    // Exiting the program
                    System.out.println("Exiting Music Management System. Have a great day!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}
