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
public interface Observateur {
    public void notifier(Message msg);
    public boolean demande(MessageDemande msg);
   
}