package mmms;

public class stringbuffer {

    public static void SongsString() {
        System.out.println("Using String Class:");
        String songTitle = "Shape of You";
        String artist = "Ed Sheeran";

        // Concatenation 
        String songDetails = songTitle + " by " + artist;
        System.out.println("Song Details: " + songDetails);

        String updatedSongTitle = songTitle.replace("Shape", "Heart");
        System.out.println("Updated Song Title: " + updatedSongTitle);

        System.out.println("Original Song Title: " + songTitle);
        System.out.println();
    }

    public static void PlaylistStringBuffer() {
        System.out.println("Using StringBuffer Class:");
        StringBuffer playlist = new StringBuffer("Playlist: ");

        // appendAdd songs dynami
        playlist.append("Blinding Lights - The Weeknd, ");
        playlist.append("Levitating - Dua Lipa, ");
        playlist.append("Stay - Justin Bieber");
        System.out.println("After append: " + playlist);
        System.out.println("--------------------");

        System.out.println("Playlist Length: " + playlist.length());
        System.out.println("--------------------");

        int lastSongStartIndex = playlist.lastIndexOf(", Stay - Justin Bieber");
        playlist.replace(lastSongStartIndex, playlist.length(), ", Peaches - Justin Bieber");
        System.out.println("After replace: " + playlist);
        System.out.println("--------------------");

        playlist.reverse();
        System.out.println("After reverse: " + playlist);
        System.out.println("--------------------");
        playlist.reverse();
        System.out.println("Restored Playlist: " + playlist);
        System.out.println("--------------------");

        // insert at a specific pos
        playlist.insert(10, "Sunflower - Post Malone, ");
        System.out.println("After insert: " + playlist);
        System.out.println("--------------------");

        playlist.delete(10, 34); // Removes Sunflower
        System.out.println("After delete: " + playlist);
        System.out.println("--------------------");

        String extractedSong = playlist.substring(10, 25);
        System.out.println("Extracted Song: " + extractedSong);
        System.out.println("--------------------");

        System.out.println("Buffer Capacity: " + playlist.capacity());
        System.out.println("--------------------");

        // 10. ensureCapacity min capacity
        playlist.ensureCapacity(100);
        System.out.println("Updated Buffer Capacity: " + playlist.capacity());
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        SongsString();
        PlaylistStringBuffer();
    }
}