import java.awt.*;

public class bullet {

    int x,y,diameter=10;
    int speed, dx, dy=-10;
    boolean remove;

    public bullet(player player){
        x = player.getX() + player.getWidth()/2 - diameter/2;
        y= player.getY()- diameter;
    }

    public void move(){
        y+=dy;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,diameter, diameter);
    }

    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillOval(x,y,diameter,diameter);
    }

    public int getY(){
        return y;
    }

    public void setRemove(){
        remove = true;
    }
    public boolean getRemove(){
        return  remove;
    }

}