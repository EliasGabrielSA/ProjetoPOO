/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

/**
 *
 * @author lefoly
 */
public class DaoFactory {

    public static MidiaDaoJdbc novoContatoDao() throws Exception {
        return new MidiaDaoJdbc();
    }

}
