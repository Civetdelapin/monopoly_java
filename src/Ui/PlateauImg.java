/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;
import Data.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
/**
 *
 * @author ribotv
 */
public class PlateauImg extends Canvas {
    
    private int rotation=0;
    private BufferedImage image;
    private IHM ihm;
    public void ImagePanel(IHM ihm, int rotat) {
       this.ihm=ihm;
       rotation=rotat;
       rotation=getRotation()%4;
       try {
          switch(getRotation()){
              case 0:
                  image = ImageIO.read(new File("./images/plateau0.png"));
                  break;
              case 1:
                  image = ImageIO.read(new File("./images/plateau1.png"));
                  break;
              case 2:
                  image = ImageIO.read(new File("./images/plateau2.png"));
                  break;
              case 3:
                  image = ImageIO.read(new File("./images/plateau3.png"));
                  break;
              default:
                  image = ImageIO.read(new File("./images/plateau0.png"));
                  break;
          }
          
          repaint();
          
       } catch (IOException ex) {
            System.err.println("image Not found");
       }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int imgmin = Math.min(getSize().width,getSize().height); 
        g.drawImage(image, 0, 0,imgmin,imgmin,null); // see javadoc for more info on the parameters
        
        
        
        
        //-----------Position des pions
        ArrayList<Joueur> joueurs = new ArrayList();
        joueurs =this.ihm.getControleur().getMonopoly().getJoueurs();

        int numCarJoueur;
        int decal=0;
        
        int centreXY=imgmin/2;
        
        
        int x=0;
        int y=0;
        int place;
        int decalle;
        for(Joueur jou : joueurs){
            numCarJoueur=jou.getPositionCourante().getNumero();
            place = (100/(joueurs.size()+1))*(joueurs.indexOf(jou)+1); //Defini la position de chaque joueur su une meme case pour ne pas que les pion se chevauchent
            
            //Defini la position sur le plateau selon le num du carreau
            if(numCarJoueur==1){ //case depart
                x=centreXY-50;
                y=centreXY-place;
            } else if(numCarJoueur<11){  //case sans depart et prison
                x=centreXY-(int)(imgmin*0.129)-((int)(imgmin*0.744))/18-(int)((imgmin*0.744)/9)*(numCarJoueur-2);
                y=centreXY-place;
            }else if(numCarJoueur==11){
                x=50-centreXY;
                y=centreXY-place;
            }else if(numCarJoueur<21){
                x=place-centreXY;
                y=centreXY-(int)(imgmin*0.129)-((int)(imgmin*0.744))/18-(int)((imgmin*0.744)/9)*(numCarJoueur-12);
            } else if(numCarJoueur==21){
                x=50-centreXY;
                y=place-centreXY;
            }else if(numCarJoueur<31){
                x=(int)(imgmin*0.129)+((int)(imgmin*0.744))/18+(int)((imgmin*0.744)/9)*(numCarJoueur-22)-centreXY;
                y=place-centreXY;
            }else if(numCarJoueur==31){
                x=centreXY-50;
                y=place-centreXY;
            } else{
                x=centreXY-place;
                y=(int)(imgmin*0.129)+((int)(imgmin*0.744))/18+(int)((imgmin*0.744)/9)*(numCarJoueur-32)-centreXY;
            }
            
            //Gere la rotation du plateau
            if(getRotation()==1){
                int temp=x;
                x=y;
                y=-temp;
            } else if(getRotation()==2){
                x=-x;
                y=-y;
            } else if(getRotation()==3){
                int temp=x;
                x=-y;
                y=temp;
            }
            
            g.setColor(jou.getColor());
            g.fillOval(x+centreXY-10, y+centreXY-10, 20, 20);
            
        }
    }

    /**
     * @return the rotation
     */
    public int getRotation() {
        return rotation;
    }
}
