/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import Data.Carreau;
import Data.Carte.Carte;
import Data.CaseFinanciere;
import Data.Joueur;

/**
 *
 * @author Baptiste
 */
public class Message {
    public Types type;
    public enum Types {
       parcGratuit,faillite,payementPrison,lancerDes,allerPrisonDouble, carreauCourant, achatPatrimoine,payerLoyer,dejaProprio,allerPrison,modiRichesse,carte,parDepart,debit,encaissement,carteDurable,finPartie,ordreJoueurs,initJoueurs,manquePionHotel,manquePionMaison,manqueArgent,tourPrison,actuTour
    };
    
    public Joueur joueurCourrant;
    public Joueur joueurProprio;
    public int n1;
    public int n2;
    public Carreau carreauCourrant;
    public Carte carte;
    public Carreau carreauDepart;
}
