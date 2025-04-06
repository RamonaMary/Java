package mmms;

import java.util.Scanner;

public class ms {

    private String title, composer; //dm
    private int duration;
    private String playlistName;

    public ms(String playlistName) { 
        this.playlistName = playlistName;
    }
  
    public void addSongToPlaylist(String title, String composer, int duration) {//mf
        this.title = title;
        this.composer = composer;
        this.duration = duration;
        System.out.println(title + " by " + composer + " (" + duration + " seconds) added to the playlist " + playlistName);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        ms playlist = new ms(playlistName); 

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

            System.out.println("....................");
            		
            System.out.print("Do you want to add another song? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("no")) break;
        }
        System.out.println(".........................");
        System.out.println("Thank you!!");

        scanner.close();
    }
}
