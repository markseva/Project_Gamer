import java.awt.*;

public class player {

    int x,y;
    double speed, dx = 4,dy;
    final int width=90,height=40;

    public player(board board){
        x = board.getWidth()/2;
        y=board.getHeight()- (height + 5);
    }
    public void moveRight(){
        x+=dx;
    }
    public void moveLeft(){
        x-=dx;
    }
    public void paint(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,width, height);
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
    public int getY(){
        return y;
    }
}
