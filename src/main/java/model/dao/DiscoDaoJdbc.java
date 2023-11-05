/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Disco;

/**
 *
 * @author lefoly
 */
public class DiscoDaoJdbc implements InterfaceDao<Disco> {

    private Connection conn;

    public DiscoDaoJdbc() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void incluir(Disco entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO colecao (nome, ano, tipo, duracao, faixas, visualizou, imagem) VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, entidade.getNome());
            ps.setInt(2, entidade.getAno());
            ps.setString(3, entidade.getTipo());
            ps.setInt(4, entidade.getDuracao());
            ps.setInt(5, entidade.getFaixas());
            ps.setBoolean(6, entidade.isVisualizou());
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
                    "UPDATE colecao SET nome=?, ano=?, tipo=?, duracao=?, faixas=?, visualizou=?, imagem=? WHERE id=?");
            ps.setString(1, entidade.getNome());
            ps.setInt(2, entidade.getAno());
            ps.setString(3, entidade.getTipo());
            ps.setInt(4, entidade.getDuracao());
            ps.setInt(5, entidade.getFaixas());
            ps.setBoolean(6, entidade.isVisualizou());
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
            PreparedStatement ps = conn.prepareStatement("DELETE FROM colecao WHERE id=?");
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
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM colecao WHERE nome = ?");
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
                c.setVisualizou(rs.getBoolean("visualizou"));
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
    public List<Disco> listar() throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM colecao");
            ResultSet rs = ps.executeQuery();
            List<Disco> lista = new ArrayList();
            while (rs.next()) {
                Disco c = new Disco();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setAno(rs.getInt("ano"));
                c.setTipo(rs.getString("tipo"));
                c.setDuracao(rs.getInt("duracao"));
                c.setFaixas(rs.getInt("faixas"));
                c.setVisualizou(rs.getBoolean("visualizou"));
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

}
