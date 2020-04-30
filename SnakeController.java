package app;
import java.net.URL;

import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
public class SnakeController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Canvas canvas;

    @FXML
    private Text score;
    
    @FXML
    private Text highScore;
    
    @FXML
    private Text seco;
    
    public static final double SPEED_MS = 100;
	public static int CellSize;
	public static int Rows;
	public static int Columns;
	public static Bounds GridBounds;

    private Snake snake;
    private Poeng poeng;
    
    @FXML
    void initialize() {

        CellSize = (int) Math.round(rootPane.getTop().getBoundsInParent().getHeight());
        GridBounds = rootPane.getCenter().getBoundsInParent();
        Rows = (int) Math.floor(GridBounds.getHeight() / CellSize);
        Columns = (int) Math.floor(GridBounds.getWidth() / CellSize);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
	    snake = new Snake(gc);
	    poeng = new Poeng(gc);
        poeng.move(snake);
        Sound sound=new Sound();
        sound.play("./src/app/Refreshing_Elevator_music-9v9-Nw4nAZg.wav",100);
	    Timeline timeline = new Timeline();
	    timeline.setCycleCount(Timeline.INDEFINITE);
	    timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(SPEED_MS),
                  new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                    	rootPane.requestFocus();
                    	highScore.setText(snake.getHighScore().get(0));
                    	seco.setText(snake.getHighScore().get(1));
                    	if (!snake.addHead() || snake.eats(poeng)) {
                    		if(snake.eats(poeng)) {
                    			sound.play("./src/app/pointSound.wav",0);
                    		}
                        	score.setText("Score: " + snake.getScore());
                            poeng.move(snake);
                    	}
                    }
                }));
        timeline.playFromStart();

    }
    @FXML
    void handleKeyPressed(KeyEvent event) {
		snake.keyPressed(event);    	
    }
    
}
