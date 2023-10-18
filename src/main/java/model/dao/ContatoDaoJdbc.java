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
import model.Contato;

/**
 *
 * @author lefoly
 */
public class ContatoDaoJdbc implements InterfaceDao<Contato> {

    private Connection conn;

    public ContatoDaoJdbc() throws Exception {
        this.conn = ConnFactory.getConnection();
    }

    @Override
    public void incluir(Contato entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Contato (nome, email, telefone) VALUES(?, ?, ?)");
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getEmail());
            ps.setString(3, entidade.getTelefone());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void editar(Contato entidade) throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE Contato SET nome=?, email=?, telefone=? WHERE id=?");
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getEmail());
            ps.setString(3, entidade.getTelefone());
            ps.setInt(4, entidade.getId());
            ps.execute();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public void excluir(Contato entidade) throws Exception {
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
    public Contato pesquisarPorId(int id) throws Exception {
        try {
            this.conn = ConnFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM contato");
            ResultSet rs = ps.executeQuery();
            ArrayList<Contato> lista = new ArrayList<>();
            if (rs.next()) {
                Contato c = new Contato();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                return c;
            }else{
                return null;
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    @Override
    public List<Contato> listar() throws Exception {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Contato");
            ResultSet rs = ps.executeQuery();
            List<Contato> lista = new ArrayList();
            while (rs.next()) {
                Contato c = new Contato();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
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
