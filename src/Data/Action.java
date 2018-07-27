/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Baptiste
 */
public class Action {
    
    private TypeAction type; //Permet de connaitre l'action a effectuer
    private int nb;          //Permet de connaitre le montant versé par le loueur

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
    public TypeAction getType() {
        return type;
    }

    public void setType(TypeAction type) {
        this.type = type;
    }
    
    
    public static enum TypeAction {
	aPayer, peutAcheter, autreCarreau, allerPrison, modiRichesse,saProprio,tirerCarte,parcGratuit;
        
    }
    
    
    public Action(){
        //initialisation
        setType(TypeAction.autreCarreau);
        setNb(0);
    }
    
    
}
