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

    public static DiscoDaoJdbc novoDiscoDao() throws Exception {
        return new DiscoDaoJdbc();
    }

}
