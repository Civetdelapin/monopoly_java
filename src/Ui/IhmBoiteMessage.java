/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Eric
 */
public class IhmBoiteMessage {
    /**
     * Affiche un popup avec un message passé en paramètre
     * et deux boutons : Oui et Non
     * @param message Question à poser à l'utilisateur
     * @param mode type de dialogue : 0=bouton OK uniquement, 1=bouton Oui/Non
     * @return Renvoie vrai si l'utilisateur chosit Oui, faux sinon
     */
    public static boolean afficherBoiteDialogue(String message, Integer mode) {
        int response ;
        switch(mode) {
            case 0 : 
                JOptionPane.showConfirmDialog(   null, 
                                                message,   
                                                "Confirmation",   
                                                JOptionPane.OK_OPTION,
                                                JOptionPane.INFORMATION_MESSAGE); 
                return true ; 
           case 1 :
                response = JOptionPane.showConfirmDialog(   null, 
                                                            message, 
                                                            "Veuillez confirmer l'opération",
                                                            JOptionPane.YES_NO_OPTION, 
                                                            JOptionPane.QUESTION_MESSAGE);
                return response == JOptionPane.YES_OPTION ;
           case 2 :
                JOptionPane.showMessageDialog(null, 
                                                message, 
                                                "Attention",
                                                JOptionPane.WARNING_MESSAGE, 
                                                null);
                return true ;
          case 3 :
                JOptionPane.showMessageDialog(null, 
                                                message, 
                                                "Info",
                                                JOptionPane.INFORMATION_MESSAGE, 
                                                null);
                return true ;      
           
        }
        return false;
    }
    
    public static boolean afficherBoiteDialogueCarte(JPanel panel) {
        int response ;
  
                response = JOptionPane.showConfirmDialog(   null, 
                                                            panel, 
                                                            "Information carte",
                                                            JOptionPane.YES_NO_OPTION, 
                                                            JOptionPane.PLAIN_MESSAGE);
                return response == JOptionPane.YES_OPTION ;
           
        }
        
    
}
