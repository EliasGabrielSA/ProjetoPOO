/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lefoly
 */
public class Midia {
    
    private int id;
    private String nome;
    private String tipo; // vinil ou CD
    private int quantidade_faixas;
    private String ano_lancamento; // dd/mm/aaaa
    private float duracao_total; // em minutos

    public Midia() {
    }

    public Midia(String nome, String tipo, int quantidade_faixas, String ano_lancamento, float duracao_total) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade_faixas = quantidade_faixas;
        this.ano_lancamento = ano_lancamento;
        this.duracao_total = duracao_total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade_faixas() {
        return quantidade_faixas;
    }

    public String getAno_lancamento() {
        return ano_lancamento;
    }

    public float getDuracao_total() {
        return duracao_total;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setQuantidade_faixas(int quantidade_faixas) {
        this.quantidade_faixas = quantidade_faixas;
    }

    public void setAno_lancamento(String ano_lancamento) {
        this.ano_lancamento = ano_lancamento;
    }

    public void setDuracao_total(float duracao_total) {
        this.duracao_total = duracao_total;
    }

    @Override
    public String toString() {
        return id + " | " + nome + " | " + tipo + " | " + quantidade_faixas + " | " + ano_lancamento + " | " + duracao_total;
    }    
}
