import com.sun.corba.se.impl.orb.ParserTable;

import javax.swing.*;
import javax.xml.bind.annotation.XmlType;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class board extends JPanel implements ActionListener {
    player player;
    enemy enemy;
    ArrayList<bullet> bullets = new ArrayList<bullet>();
    enemy[][] enemies = new enemy[5][10];
    Timer timer;
    Long timeDelay, bulletDelay;
    game game;

    public board(game game) {
        this.game = game;
        setPreferredSize(new Dimension(1024, 800));

        setBackground(Color.BLUE);
        timer = new Timer(1000 / 60, this);
        timer.start();
    }

    //Gives objects starting position
    public void setup() {
        player = new player(this);
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                enemies[row][col] = new enemy(getWidth() / 4 + col * 50, row * 50);
            }
        }

        timeDelay = System.currentTimeMillis();
        bulletDelay = System.currentTimeMillis();
    }

    public void checkCollisions() {
        for (int i = bullets.size() - 1; i >= 0; i--) {
            for (int j = 0; j < enemies.length; j++) {
                for (int k = 0; k < enemies[0].length; k++) {
                    if (enemies[j][k] != null) {
                        if (bullets.get(i).getBounds().intersects(enemies[j][k].getBounds())) {
                            bullets.get(i).setRemove();
                            enemies[j][k] = null;
                            break;
                        }
                    }
                }
            }

            if (bullets.get(i).getRemove()) {
                bullets.remove(bullets.get(i));
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        long currentTime = System.currentTimeMillis();


        if (gamestates.isPLAY()) {
            checkCollisions();
            if (game.isSpacePressed() && currentTime - bulletDelay >= 250) {

                bullets.add(new bullet(player));
                bulletDelay = System.currentTimeMillis();
            }


            for (int i = bullets.size() - 1; i >= 0; i--) {
                if (bullets.get(i).getY() < 0) {
                    bullets.remove(bullets.get(i));
                } else {
                    bullets.get(i).move();
                }
            }


            if (currentTime - timeDelay >= 1000) {
                for (int row = 0; row < 5; row++) {
                    for (int col = 0; col < 10; col++) {
                        if (enemies[row][col] != null) {
                            enemies[row][col].move();
                        }

                    }
                }
                timeDelay = System.currentTimeMillis();
            }

            if (game.isLeftPressed() && player.getX() > 0) {
                player.moveLeft();
            }

            if (game.isRightPressed() && (player.getX() + player.getWidth()) < getWidth()) {
                player.moveRight();
            }


        }
        if (game.isEnterPressed()) {
            gamestates.setPLAY(true);
            gamestates.setMENU(false);
        }


        if (game.ispPressed()) {
            if (gamestates.isPAUSE()) {
                gamestates.setPAUSE(false);
            } else
                gamestates.setPAUSE(true);
        }

        repaint();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (gamestates.isMENU()) {
            //Paint the Menu
            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans", Font.BOLD, 72));
            printSimpleString("Brick Breaker??", getWidth(), 0, 150, g);
            g.setFont(new Font("Comic Sans", Font.BOLD, 38));
            printSimpleString("Press ENTER", getWidth(), 0, 300, g);
        }


        if (gamestates.isPLAY()) {
            player.paint(g);
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 10; col++) {
                    if (enemies[row][col] != null) {
                        enemies[row][col].paint(g);
                    }

                }
            }
            for (bullet bullet : bullets) {
                bullet.paint(g);
            }
        }
    }

    private void printSimpleString(String s, int WIDTH, int Xpos, int Ypos, Graphics g) {
        int StringLen = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        int start = WIDTH / 2 - StringLen / 2;
        g.drawString(s, start + Xpos, Ypos);
    }
}


