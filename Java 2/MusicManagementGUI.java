import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class MusicManagementGUI {
    private JFrame frame;
    private JTextField songIDField, songNameField, artistField, albumField, releaseDateField, durationField;
    private JComboBox<String> genreComboBox;
    private JTextArea displayArea;
    private Connection conn;

    static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
    static final String USER = "root";
    static final String PASS = "wolf9";

    public MusicManagementGUI() {
        initializeDB();
        initializeUI();
    }

    private void initializeDB() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Songs ("
                    + "songID INT PRIMARY KEY, "
                    + "songName VARCHAR(100), "
                    + "artist VARCHAR(100), "
                    + "album VARCHAR(100), "
                    + "releaseDate VARCHAR(20), "
                    + "duration VARCHAR(10), "
                    + "genre VARCHAR(50))";
            stmt.executeUpdate(createTableSQL);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + e.getMessage());
        }
    }

    private void initializeUI() {
        frame = new JFrame("Music Management System");
        frame.setSize(500, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 2));

        frame.add(new JLabel("Song ID:"));
        songIDField = new JTextField();
        frame.add(songIDField);

        frame.add(new JLabel("Song Name:"));
        songNameField = new JTextField();
        frame.add(songNameField);

        frame.add(new JLabel("Artist:"));
        artistField = new JTextField();
        frame.add(artistField);

        frame.add(new JLabel("Album:"));
        albumField = new JTextField();
        frame.add(albumField);

        frame.add(new JLabel("Release Date (yyyy-MM-dd):"));
        releaseDateField = new JTextField();
        frame.add(releaseDateField);

        frame.add(new JLabel("Duration (mm:ss):"));
        durationField = new JTextField();
        frame.add(durationField);

        frame.add(new JLabel("Genre:"));
        genreComboBox = new JComboBox<>(new String[]{"Select", "Pop", "Rock", "Jazz", "Classical", "Hip-Hop"});
        frame.add(genreComboBox);

        JButton addButton = new JButton("Add Song");
        frame.add(addButton);

        JButton updateButton = new JButton("Update Song");
        frame.add(updateButton);

        JButton displayButton = new JButton("Show Song Details");
        frame.add(displayButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        frame.add(new JScrollPane(displayArea));

        addButton.addActionListener(e -> addSong());
        updateButton.addActionListener(e -> updateSong());
        displayButton.addActionListener(e -> displaySongs());

        frame.setVisible(true);
    }

    private void addSong() {
        try {
            int songID = Integer.parseInt(songIDField.getText().trim());
            String sql = "INSERT INTO Songs (songID, songName, artist, album, releaseDate, duration, genre) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, songID);
            pstmt.setString(2, songNameField.getText().trim());
            pstmt.setString(3, artistField.getText().trim());
            pstmt.setString(4, albumField.getText().trim());
            pstmt.setString(5, releaseDateField.getText().trim());
            pstmt.setString(6, durationField.getText().trim());
            pstmt.setString(7, (String) genreComboBox.getSelectedItem());

            pstmt.executeUpdate();
            pstmt.close();
            JOptionPane.showMessageDialog(frame, "Song added successfully!");
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    private void updateSong() {
        try {
            int songID = Integer.parseInt(songIDField.getText().trim());
            String sql = "UPDATE Songs SET songName=?, artist=?, album=?, releaseDate=?, duration=?, genre=? WHERE songID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, songNameField.getText().trim());
            pstmt.setString(2, artistField.getText().trim());
            pstmt.setString(3, albumField.getText().trim());
            pstmt.setString(4, releaseDateField.getText().trim());
            pstmt.setString(5, durationField.getText().trim());
            pstmt.setString(6, (String) genreComboBox.getSelectedItem());
            pstmt.setInt(7, songID);

            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Song updated successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Song with ID " + songID + " not found.");
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    private void displaySongs() {
        try {
            String sql = "SELECT * FROM Songs";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            StringBuilder details = new StringBuilder();
            while (rs.next()) {
                details.append("Song ID: ").append(rs.getInt("songID"))
                        .append("\nSong Name: ").append(rs.getString("songName"))
                        .append("\nArtist: ").append(rs.getString("artist"))
                        .append("\nAlbum: ").append(rs.getString("album"))
                        .append("\nRelease Date: ").append(rs.getString("releaseDate"))
                        .append("\nDuration: ").append(rs.getString("duration"))
                        .append("\nGenre: ").append(rs.getString("genre"))
                        .append("\n--------------------------\n");
            }
            rs.close();
            stmt.close();
            displayArea.setText(details.length() > 0 ? details.toString() : "No songs found.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "SQL Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new MusicManagementGUI();
    }
}
