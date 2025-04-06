import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class MusicPlayer extends Thread {
    private ArrayList<String> playlist;
    private JTextArea displayArea;

    MusicPlayer(ArrayList<String> playlist, JTextArea displayArea) {
        this.playlist = playlist;
        this.displayArea = displayArea;
    }

    public void run() {
        try {
            for (String song : playlist) {
                displayArea.append("\nPlaying: " + song);
                Thread.sleep(2000); // Simulate song duration
            }
            displayArea.append("\nPlaylist finished!");
        } catch (InterruptedException e) {
            displayArea.append("\nMusic Stopped!");
        }
    }
}

public class SwingMusicPlayer {
    private JFrame frame;
    private DefaultListModel<String> songModel;
    private JList<String> songList;
    private JTextArea displayArea;
    private ArrayList<String> playlist;
    private MusicPlayer playerThread;

    public SwingMusicPlayer() {
        playlist = new ArrayList<>();

        // Frame setup
        frame = new JFrame("Swing Music Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Playlist display (JList)
        songModel = new DefaultListModel<>();
        songList = new JList<>(songModel);
        JScrollPane scrollPane = new JScrollPane(songList);

        // Text area for playback messages
        displayArea = new JTextArea();
        displayArea.setEditable(false);

        // Buttons
        JButton addButton = new JButton("Add Song");
        JButton playButton = new JButton("Play");

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.add(addButton);
        panel.add(playButton);

        // Add components to frame
        frame.add(scrollPane, BorderLayout.WEST);
        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        // Button Actions
        addButton.addActionListener(e -> addSong());
        playButton.addActionListener(e -> playSongs());

        frame.setVisible(true);
    }

    private void addSong() {
        String song = JOptionPane.showInputDialog(frame, "Enter song name:");
        if (song != null && !song.trim().isEmpty()) {
            playlist.add(song);
            songModel.addElement(song);
        }
    }

    private void playSongs() {
        if (playlist.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Playlist is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        displayArea.setText("Starting playback...");
        if (playerThread == null || !playerThread.isAlive()) {
            playerThread = new MusicPlayer(playlist, displayArea);
            playerThread.start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SwingMusicPlayer::new);
    }
}
