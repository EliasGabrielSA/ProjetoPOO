/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author lefoly
 */
public class Disco {
    
    private int id;
    private String nome;
    private int ano;
    private String tipo;
    private int duracao;
    private int faixas;
    private boolean visualizou;
    private String imagem;
    
    public Disco(){
        
    }

    public Disco(String nome, int ano, String tipo, int duracao, int faixas, boolean visualizou, String imagem) {
        this.nome = nome;
        this.ano = ano;
        this.tipo = tipo;
        this.duracao = duracao;
        this.faixas = faixas;
        this.visualizou = visualizou;
        this.imagem = imagem;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getFaixas() {
        return faixas;
    }

    public void setFaixas(int faixas) {
        this.faixas = faixas;
    }

    public boolean isVisualizou() {
        return visualizou;
    }

    public void setVisualizou(boolean visualizou) {
        this.visualizou = visualizou;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return id + " | " + nome + " | " + ano + " | " + tipo + " | " + duracao + " | " + faixas + " | " + visualizou + " | " + imagem;
    }
        
}
