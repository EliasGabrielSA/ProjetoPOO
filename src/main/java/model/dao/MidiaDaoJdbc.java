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
import model.Midia;

/**
 *
 * @author lefoly
 */
public class MidiaDaoJdbc implements InterfaceDao<Midia> {

    private Connection conn;

    public MidiaDaoJdbc() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void incluir(Midia entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Midia (nome, tipo, quantidade_faixas, ano_lancamento, duracao_total) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getTipo());
            ps.setInt(3, entidade.getQuantidade_faixas());
            ps.setString(2, entidade.getAno_lancamento());
            ps.setFloat(3, entidade.getDuracao_total());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void editar(Midia entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE Contato SET nome=?, tipo=?, quantidade_faixa=?, ano_lancamento = ?, duracao_total = ?, WHERE id=?");
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getTipo());
            ps.setInt(3, entidade.getQuantidade_faixas());
            ps.setString(2, entidade.getAno_lancamento());
            ps.setFloat(3, entidade.getDuracao_total());
            ps.setInt(4, entidade.getId());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void excluir(Midia entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Contato WHERE id=?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public Midia pesquisarPorId(int id) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Midia");
            ResultSet rs = ps.executeQuery();
            List<Midia> lista = new ArrayList();
            while (rs.next()) {
                if (rs.getInt("id") == id) {
                    Midia m = new Midia();
                    m.setId(rs.getInt("id"));
                    m.setNome(rs.getString("nome"));
                    m.setTipo(rs.getString("tipo"));
                    m.setQuantidade_faixas(rs.getInt("quantidade_faixas"));
                    m.setAno_lancamento(rs.getString("ano_lancamento"));
                    m.setDuracao_total(rs.getFloat("duracao_total"));
                    
                    return m;
                }   
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }
    

    @Override
    public List<Midia> listar() throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Contato");
            ResultSet rs = ps.executeQuery();
            List<Midia> lista = new ArrayList();
            while (rs.next()) {
                Midia m = new Midia();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setTipo(rs.getString("tipo"));
                m.setQuantidade_faixas(rs.getInt("quantidade_faixas"));
                m.setAno_lancamento(rs.getString("ano_lancamento"));
                m.setDuracao_total(rs.getFloat("duracao_total"));
                lista.add(m);
            }
            return lista;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
