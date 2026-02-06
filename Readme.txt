Music Player Project
Project Overview
This Music Player project is a GUI-based application developed in Java using the Swing library. It provides a simple and intuitive interface for managing and playing music. Users can add songs to a playlist, view the playlist, control playback, and save/load playlists from a file.
Features
Add Songs: Add songs to the playlist dynamically.
View Playlist: Display the playlist in a separate window.
Delete Songs: Remove songs from the playlist.
Save/Load Playlist: Save the playlist to a file or load it back.
Playback Control: Includes Play, Pause, Next, and Previous buttons for controlling the playback of songs.
Dynamic Display: Updates the currently playing song in the playback window.
Technical Details
Language: Java
GUI Library: Swing
Data Structure: LinkedList
File Handling: Used for saving and loading playlists.
How to Run the Project
Pre-requisites:


Ensure you have Java Development Kit (JDK) installed (version 8 or higher).
A Java IDE such as IntelliJ IDEA, Eclipse, or NetBeans is recommended but optional.
Clone or Download the Project:


Download the source code files and place them in a single directory.
Compile and Run:


Open a terminal or command prompt.
Navigate to the directory containing the source code.
Compile the code using the following command:
 javac MusicPlayerGUI.java


Run the program using:
 java MusicPlayerGUI


Using the Application:


Adding Songs: Use the "View Playlist" button to open the playlist window and click "Add Song" to add songs.
Playing Songs: Use the "Play" button to start playback. You can select the song to play by name.
Playback Control: Use the "Previous", "Pause", and "Next" buttons in the playback window to control playback.
Save/Load Playlist: Save your playlist to playlist.txt and load it later using the respective buttons in the playlist window.

Project Structure
MusicPlayerGUI Class: The main class containing the GUI implementation and core functionalities.


Key Methods:
initializeGUI(): Sets up the main window and its components.
viewPlaylist(): Displays the playlist and provides options to add, delete, save, or load songs.
showPlayWindow(): Opens the playback control window.
addSong(): Adds a song to the playlist.
savePlaylist() / loadPlaylist(): Handles file operations.


Video Demonstration

Watch the full video demonstration of the project on YouTube: [https://youtu.be/SojCcgqSzyg]
The video explains the functionalities of the music player, demonstrates GUI operations, and showcases features like playlist management and playback control.
