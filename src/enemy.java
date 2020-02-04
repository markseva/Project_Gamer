import java.awt.*;

public class enemy {

    int x,y= 30;
    int height = 40;
    int width = 20;
    int speed, dx, dy=5;
    int numEnemies = 50;

    public enemy(){
        x=50;
        y=50;
    }

    public enemy(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void move(){
        y+=dy;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,height,width);
    }

    public void paint(Graphics g ){
        g.setColor(Color.red);
        g.fillRect(x,y,height,width);
    }

    public int getNumEnemies(){
        return numEnemies;
    }

}
