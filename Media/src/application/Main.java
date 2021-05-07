package application;
	
import java.io.File;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class Main extends Application {
	MediaPlayer mediaPlayer;
	MediaView mv;
	Slider slider;
	@Override
	public void start(Stage primaryStage) throws Exception{
		String path = "D://video.mp4"; //your file goes here
		Media media = new Media(new File(path).toURI().toString());
		int h = media.getHeight();
		int w = media.getWidth();
		mediaPlayer = new MediaPlayer(media);
		mv = new MediaView(mediaPlayer);
		mv.fitWidthProperty().bind(primaryStage.widthProperty());
		mv.fitHeightProperty().bind(primaryStage.heightProperty());
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setVolume(0);
		mediaPlayer.setAutoPlay(false);
		slider = new Slider();
	    slider.setOnMousePressed( click -> {
	        mediaPlayer.seek(Duration.seconds(slider.getValue()));
	    });
	    slider.setOnMouseDragged(drag ->{
	    	mediaPlayer.seek(Duration.seconds(slider.getValue()));
	    });
	    media.durationProperty().addListener( change -> {
	        slider.setMax(media.getDuration().toSeconds());
	    });
	    mediaPlayer.currentTimeProperty().addListener( c -> {
	        slider.setValue(mediaPlayer.getCurrentTime().toSeconds());
	    });
	    Button play = new Button("Play");
	    play.setOnMouseClicked(p->{
	    	if(mediaPlayer.getStatus()==MediaPlayer.Status.PLAYING)
	    	{
	    		mediaPlayer.pause();
	    	}else
	    	{
	    		mediaPlayer.play();
	    	}
	    });
	    Button next = new Button("next");
	    next.setOnMouseClicked(n->{
	    	mediaPlayer.seek(Duration.seconds(mediaPlayer.getCurrentTime().toSeconds()+10));
	    });
	    Button pre = new Button("Pre");
	    pre.setOnMouseClicked(p->{
	    	mediaPlayer.seek(Duration.seconds(mediaPlayer.getCurrentTime().toSeconds()-10));
	    });
	    Slider volume = new Slider();
	    volume.setMax(1);
	    volume.setMin(0);
	    volume.setOnMouseClicked(vClick->{
	    	mediaPlayer.setVolume(volume.getValue());
	    });
	    volume.setOnMouseDragged(vDrag->{
	    	mediaPlayer.setVolume(volume.getValue());
	    });
	    HBox btnCtrl = new HBox();
	    btnCtrl.getChildren().addAll(pre,play,next,volume);
	    btnCtrl.setSpacing(10);
		VBox root = new VBox();
		root.setSpacing(5);
		BorderPane videoView = new BorderPane();
		videoView.setCenter(mv);
		root.getChildren().addAll(videoView,slider,btnCtrl);
	    Scene scene = new Scene(root,720,480);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("HD video player ");  
	    primaryStage.show();
	    primaryStage.setResizable(false);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
