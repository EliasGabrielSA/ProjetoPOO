package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Disco;

public class DiscoDaoJdbc implements InterfaceDao<Disco> {

    private Connection conn;

    public DiscoDaoJdbc() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void incluir(Disco entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO discos (nome, ano, tipo, duracao, faixas, visualizou, imagem) VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, entidade.getNome());
            ps.setInt(2, entidade.getAno());
            ps.setString(3, entidade.getTipo());
            ps.setInt(4, entidade.getDuracao());
            ps.setInt(5, entidade.getFaixas());
            ps.setString(6, entidade.getVisualizou());
            ps.setString(7, entidade.getImagem());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void editar(Disco entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE discos SET nome=?, ano=?, tipo=?, duracao=?, faixas=?, visualizou=?, imagem=? WHERE id=?");
            ps.setString(1, entidade.getNome());
            ps.setInt(2, entidade.getAno());
            ps.setString(3, entidade.getTipo());
            ps.setInt(4, entidade.getDuracao());
            ps.setInt(5, entidade.getFaixas());
            ps.setString(6, entidade.getVisualizou());
            ps.setString(7, entidade.getImagem());
            ps.setInt(8, entidade.getId());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void excluir(Disco entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM discos WHERE id=?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public Disco pesquisarPorNome(String nome) throws Exception {
        try {
            this.conn = ConnFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM discos WHERE nome = ?");
            ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Disco c = new Disco();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setAno(rs.getInt("ano"));
                c.setTipo(rs.getString("tipo"));
                c.setDuracao(rs.getInt("duracao"));
                c.setFaixas(rs.getInt("faixas"));
                c.setVisualizou(rs.getString("visualizou"));
                c.setImagem(rs.getString("imagem"));
                return c;
            } else {
                return null;
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<Disco> listar(String param) throws Exception {
        try {
            PreparedStatement ps = null;
            ResultSet rs = null;
            if(param.equals("")){
                ps = conn.prepareStatement("SELECT * FROM discos");
            } else{
                ps = conn.prepareStatement("SELECT * FROM discos WHERE nome like '%" + param + "%'");
            }
            rs = ps.executeQuery();
            List<Disco> lista = new ArrayList();
            while (rs.next()) {
                Disco c = new Disco();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setAno(rs.getInt("ano"));
                c.setTipo(rs.getString("tipo"));
                c.setDuracao(rs.getInt("duracao"));
                c.setFaixas(rs.getInt("faixas"));
                c.setVisualizou(rs.getString("visualizou"));
                c.setImagem(rs.getString("imagem"));
                lista.add(c);
            }
            return lista;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void discoSelecionado(Disco disco) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
