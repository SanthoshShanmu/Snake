package app;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Snake extends LinkedList<Point> implements SnakeInterface{
	private static final long serialVersionUID = 1L;
	private Direction direction = Direction.UP;
    private GraphicsContext gc;
    private List<String> highscore=new ArrayList<>();

    Snake(GraphicsContext gc) {
    	this.gc = gc;
    	reset();
    }
    
    public void reset(){
    	gc.clearRect(0, 0,
    			SnakeController.GridBounds.getWidth(),
    			SnakeController.GridBounds.getHeight());
    	clear();
	    direction = Direction.UP;
	    addFirst(new Point(SnakeController.Columns/2 * SnakeController.CellSize,
      		  SnakeController.Rows/2 * SnakeController.CellSize));
	    addHead();
	    addHead();
	    addHead();
        return;
    }
    
    public int getScore(){
        return size()-3;
    }
    
    public void checkScore() {
    	highscore.add("0");
    	highscore.add("0");
    	String[] list=this.getHighScore().get(0).split(":");
    	String[] list2=this.getHighScore().get(1).split(":");
		if(this.getScore()>Integer.parseInt(list[1])) {
			String name=null;
			while(name==null) {
				name=JOptionPane.showInputDialog("Gralla du mekket ny highscore! Hva heter du?");
			}
    		highscore.set(0,name+":"+Integer.toString(this.getScore()));
    		highscore.set(1, this.getHighScore().get(0));
    		File scoreFile=new File("highscore.txt");
    		if(!(scoreFile.exists())) {
    			try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    		FileWriter filewriter=null;
    		BufferedWriter writer=null;
    		try {
    			filewriter=new FileWriter(scoreFile);
    			writer=new BufferedWriter(filewriter);
    			writer.write(this.highscore.get(0)+"\n"+this.highscore.get(1));
    		}
    		catch (Exception e){
    			e.printStackTrace();
    		}
    		
    		finally {
    			
    				try {
    					if(writer!=null) {
    						writer.close();
    					}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		}
    	else if(this.getScore()>Integer.parseInt(list2[1])) {
			String name=null;
			while(name==null) {
				name=JOptionPane.showInputDialog("Gralla du mekket ny andre plass! Hva heter du?");
			}
			highscore.set(0, this.getHighScore().get(0));
			highscore.set(1,name+":"+Integer.toString(this.getScore()));
    		File scoreFile=new File("highscore.txt");
    		if(!(scoreFile.exists())) {
    			try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    		FileWriter filewriter=null;
    		BufferedWriter writer=null;
    		try {
    			filewriter=new FileWriter(scoreFile);
    			writer=new BufferedWriter(filewriter);
    			writer.write(this.highscore.get(0)+"\n"+this.highscore.get(1));
    			writer.close();
    		}
    		catch (Exception e){
    			e.printStackTrace();
    		}
    		}		
    	}
    
    public List<String> getHighScore() {
    	FileReader fileread=null;
    	BufferedReader reader=null;
    	List<String> list=new ArrayList<>();
    	try {
    		fileread=new FileReader("highscore.txt");
    		reader=new BufferedReader(fileread);
    		for(int i=0;i<2;i++) {
    			list.add(reader.readLine());
    		}
    		reader.close();
    		return list;
    	}
    		
    	catch(Exception e) {
    		list.add("Ingen:0");
    		list.add("Ingen:0");
    		return list;
    	}
    	
    }
    
    
    public boolean addHead(){
    	Point head = new Point(getFirst());
    	head.translate(direction.x(), direction.y());
        if (!head.inBounds(SnakeController.GridBounds) || contains(head)) {
        	checkScore();
    	    Sound sound=new Sound();
    	    sound.play("./src/app/death.wav",0);
        	reset();
        	return false;
        }
        addFirst(head);    
        gc.setFill(Color.WHITE);
        gc.fillOval(head.getX(), head.getY(),
        		SnakeController.CellSize, SnakeController.CellSize);
        return true;
    }
    
    public boolean eats(Poeng apple){
        if (getFirst().equals(apple)) return true;
        
        Point tail = removeLast();
        gc.clearRect(tail.getX()+1, tail.getY()+1,
     			SnakeController.CellSize-1, SnakeController.CellSize-1);
        return false;
    }
    
public void keyPressed(KeyEvent event) {
    switch (event.getCode()) {
        case D:
        case RIGHT:
            if (direction != Direction.LEFT)
                direction = Direction.RIGHT;
            break;
        case A:
        case LEFT:
            if (direction != Direction.RIGHT)
                direction = Direction.LEFT;
            break;
        case W:
        case UP:
            if (direction != Direction.DOWN)
                direction = Direction.UP;
            break;
        case S:
        case DOWN:
            if (direction != Direction.UP)
                direction = Direction.DOWN;
            break;
        default:
    }
}

}
