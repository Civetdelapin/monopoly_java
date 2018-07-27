/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

/**
 *
 * @author Baptiste
 */


import Data.Carreau;
import Data.Joueur;

/**
 *
 * @author Baptiste
 */
public class MessageDemande {
    public Types type;
    public enum Types {
       achat,cartePrison
    };
    
    public Joueur joueurCourrant;
    public int nb;
    
    public Carreau carreauCourrant;
}
