/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author sylvestre
 */
public class IHMdes extends Canvas {
    
    
    private BufferedImage image;

    public void afficheDeNum(int score) {
       this.setPreferredSize(new Dimension(100,100));
       try {
          switch(score){
              case 1:
                  image = ImageIO.read(new File("./images/des/un11.png"));
                  break;
              case 2:
                  image = ImageIO.read(new File("./images/des/deux22.png"));
                  break;
              case 3:
                  image = ImageIO.read(new File("./images/des/trois33.png"));
                  break;
              case 4:
                  image = ImageIO.read(new File("./images/des/quatre44.png"));
                  break;
              case 5:
                  image = ImageIO.read(new File("./images/des/cinq55.png"));
                  break; 
              case 6:
                  image = ImageIO.read(new File("./images/des/six66.png"));
                  break;    
              default:
                  image = ImageIO.read(new File("./images/des/unknow.png"));
                  break;
          }
          repaint();
       } catch (IOException ex) {
            System.err.println("Dice picture Not found");
       }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
       
        g.drawImage(image, 0, 0,90,90, null); 
    }

}
    

