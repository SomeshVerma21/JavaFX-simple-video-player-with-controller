package application;

import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class AudioPlayer extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception{
		String path = "your file path ";
		Media media = new Media(new File(path).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		mediaPlayer.setVolume(1);

	    primaryStage.setTitle("Audio Player");  
	    primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
