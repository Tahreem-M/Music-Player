import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;

class MusicPlayerGUI {

    LinkedList<String> playlist = new LinkedList<>();
    int currentSongIndex = -1;
    boolean isPaused = false;

    JFrame frame;
    JButton playButton, viewPlaylistButton;
    JLabel nowPlayingLabel;

    MusicPlayerGUI() {
        initializeGUI();
    }

    void initializeGUI() {
        frame = new JFrame("Music Player");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon logo = new ImageIcon("music.jpg");
        frame.setIconImage(logo.getImage());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 228, 196));
        titlePanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("My Music Player", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(139, 69, 19));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        frame.add(titlePanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255, 228, 196));
        centerPanel.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel(new ImageIcon("music.jpg"));
        centerPanel.add(imageLabel, BorderLayout.CENTER);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBackground(new Color(255, 228, 196));

        playButton = createStyledButton("Play", new Color(255, 228, 196), new Color(139, 69, 19), 180, 40);
        viewPlaylistButton = createStyledButton("View Playlist", new Color(255, 228, 196), new Color(139, 69, 19), 180, 40);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBackground(new Color(255, 228, 196));
        controlsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        controlsPanel.add(playButton);
        controlsPanel.add(viewPlaylistButton);

        leftPanel.add(controlsPanel, BorderLayout.CENTER);

        playButton.addActionListener(e -> playSong());
        viewPlaylistButton.addActionListener(e -> viewPlaylist());

        frame.add(leftPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    JButton createStyledButton(String text, Color bgColor, Color fontColor, int width, int height) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(fontColor);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(width, height));
        button.setBorder(BorderFactory.createLineBorder(fontColor));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(139, 69, 19));
                button.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
                button.setForeground(fontColor);
            }
        });

        return button;
    }

    void playSong() {
        if (playlist.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Playlist is empty. Add songs to play.");
            return;
        }

        if (currentSongIndex == -1) {
            String song = JOptionPane.showInputDialog(frame, "Enter the song name to play:");
            if (song != null && !song.trim().isEmpty() && playlist.contains(song)) {
                currentSongIndex = playlist.indexOf(song);
                showPlayWindow();
            } else {
                JOptionPane.showMessageDialog(frame, "Song not found in the playlist.");
            }
        } else {
            showPlayWindow();
        }
    }

    void showPlayWindow() {
        if (playlist.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Playlist is empty. Add songs to play.");
            return;
        }

        JFrame playFrame = new JFrame("Play Window");
        playFrame.setSize(400, 250);
        playFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel playPanel = new JPanel();
        playPanel.setLayout(new BorderLayout());
        playPanel.setBackground(new Color(255, 228, 196));

        JLabel playLabel = new JLabel("Control playback", SwingConstants.CENTER);
        playLabel.setFont(new Font("Arial", Font.BOLD, 16));
        playPanel.add(playLabel, BorderLayout.NORTH);

        nowPlayingLabel = new JLabel("Playing: " + playlist.get(currentSongIndex), SwingConstants.CENTER);
        nowPlayingLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        playPanel.add(nowPlayingLabel, BorderLayout.CENTER);

        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout());

        JButton previousButton = createStyledButton("Previous", new Color(255, 228, 196), new Color(139, 69, 19), 80, 40);
        JButton nextButton = createStyledButton("Next", new Color(255, 228, 196), new Color(139, 69, 19), 80, 40);
        JButton pauseButton = createStyledButton("Pause", new Color(255, 228, 196), new Color(139, 69, 19), 80, 40);

        previousButton.addActionListener(e -> {
            previousSong();
            updateNowPlaying();
        });
        nextButton.addActionListener(e -> {
            nextSong();
            updateNowPlaying();
        });
        pauseButton.addActionListener(e -> pauseSong());

        controlsPanel.add(previousButton);
        controlsPanel.add(pauseButton);
        controlsPanel.add(nextButton);

        playPanel.add(controlsPanel, BorderLayout.SOUTH);
        playFrame.add(playPanel);
        playFrame.setVisible(true);
    }

    void previousSong() {
        if (currentSongIndex > 0) {
            currentSongIndex--;
        } else {
            JOptionPane.showMessageDialog(frame, "You are at the first song.");
        }
    }

    void nextSong() {
        if (currentSongIndex < playlist.size() - 1) {
            currentSongIndex++;
        } else {
            JOptionPane.showMessageDialog(frame, "You are at the last song.");
        }
    }

    void pauseSong() {
        isPaused = !isPaused;
        JOptionPane.showMessageDialog(frame, isPaused ? "Song paused" : "Song resumed");
    }

    void updateNowPlaying() {
        nowPlayingLabel.setText("Playing: " + playlist.get(currentSongIndex));
    }

    void viewPlaylist() {
        JFrame playlistFrame = new JFrame("View Playlist");
        playlistFrame.setSize(400, 300);
        playlistFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        DefaultListModel<String> playlistModel = new DefaultListModel<>();
        playlist.forEach(playlistModel::addElement);
        JList<String> playlistList = new JList<>(playlistModel);

        JScrollPane scrollPane = new JScrollPane(playlistList);
        playlistFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = createStyledButton("Add Song", new Color(255, 228, 196), new Color(139, 69, 19), 60, 40);
        JButton deleteButton = createStyledButton("Delete Song", new Color(255, 228, 196), new Color(139, 69, 19), 60, 40);
        JButton saveButton = createStyledButton("Save Playlist", new Color(255, 228, 196), new Color(139, 69, 19), 60, 40);
        JButton loadButton = createStyledButton("Load Playlist", new Color(255, 228, 196), new Color(139, 69, 19), 60, 40);

        addButton.addActionListener(e -> addSong(playlistModel));
        deleteButton.addActionListener(e -> {
            String song = JOptionPane.showInputDialog(frame, "Enter the song name to delete:");
            if (song != null && playlist.remove(song)) {
                playlistModel.removeElement(song);
            } else {
                JOptionPane.showMessageDialog(frame, "Song not found.");
            }
        });
        saveButton.addActionListener(e -> savePlaylist());
        loadButton.addActionListener(e -> loadPlaylist(playlistModel));

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        playlistFrame.add(buttonPanel, BorderLayout.SOUTH);
        playlistFrame.setVisible(true);
    }

    void addSong(DefaultListModel<String> playlistModel) {
        String song = JOptionPane.showInputDialog(frame, "Enter song name:");
        if (song != null && !song.trim().isEmpty()) {
            playlist.add(song);
            playlistModel.addElement(song);
        } else {
            JOptionPane.showMessageDialog(frame, "Song name cannot be empty.");
        }
    }

    void savePlaylist() {
        try (FileWriter writer = new FileWriter("playlist.txt")) {
            for (String song : playlist) {
                writer.write(song + "\n");
            }
            JOptionPane.showMessageDialog(frame, "Playlist saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving playlist: " + e.getMessage());
        }
    }

    void loadPlaylist(DefaultListModel<String> playlistModel) {
        try (BufferedReader reader = new BufferedReader(new FileReader("playlist.txt"))) {
            playlist.clear();
            playlistModel.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                playlist.add(line);
                playlistModel.addElement(line);
            }
            JOptionPane.showMessageDialog(frame, "Playlist loaded successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading playlist: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MusicPlayerGUI::new);
    }
}
