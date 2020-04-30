package app;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Poeng extends Point {
    private Random r = new Random();
    private GraphicsContext gc;
    
    Poeng(GraphicsContext gc) {
    	this.gc = gc;
    }
    
    private final int randX() {
    	return r.nextInt(SnakeController.Columns) * SnakeController.CellSize;
    }

    private final int randY() {
    	return r.nextInt(SnakeController.Rows) * SnakeController.CellSize;
    }
    
    public void move(Snake snake){
        do {
        	setX(randX());
            setY(randY());
        } while (snake.contains(this));

        gc.setFill(Color.RED);
        gc.fillOval(getX(), getY(), SnakeController.CellSize, SnakeController.CellSize);
    }
   

}
