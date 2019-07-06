package br.com.rafaelleme.senai.meusgastos.vo;

/**
 * Created by rafae on 10/06/2019.
 */

public class Lancamento {

    private Long id;
    private String tipo;
    private String data;
    private String descricao;
    private Double valor;

    public Lancamento(Long id, String tipo, String data, String descricao, Double valor) {
        this.id = id;
        this.tipo = tipo;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Lancamento() {

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
