package model.dao;

public class DaoFactory {

    public static DiscoDaoJdbc novoDiscoDao() throws Exception {
        return new DiscoDaoJdbc();
    }

}
