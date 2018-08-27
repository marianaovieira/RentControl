package br.iff.pooa20181.rentcontrol.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Imovel extends RealmObject implements Serializable {

    @PrimaryKey
    int id;
    String nomeProp;
    String telefoneProp;
    String emailProp;
    String rua;
    String cep;
    String cidade;
    String numero;
    String bairro;
    String valor;


    public Imovel(){}

    public Imovel(int id, String nomeProp, String valor, String cep){

        this.id = id;
        this.nomeProp = nomeProp;
        this.valor = cep;
        this.cep = cep;


    }

    public Imovel(int id, String nomeProp, String telefoneProp, String emailProp, String rua, String cep, String cidade, String numero, String bairro, String valor){

        this.id = id;
        this.nomeProp = nomeProp;
        this.valor = cep;
        this.cep = cep;
        this.telefoneProp = telefoneProp;
        this.emailProp = emailProp;
        this.rua = rua;
        this.cidade = cidade;
        this.numero = numero;
        this.bairro = bairro;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProp() {
        return nomeProp;
    }

    public void setNomeProp(String nomeProp) {
        this.nomeProp = nomeProp;
    }

    public String getTelefoneProp() {
        return telefoneProp;
    }

    public void setTelefoneProp(String telefoneProp) {
        this.telefoneProp = telefoneProp;
    }

    public String getEmailProp() {
        return emailProp;
    }

    public void setEmailProp(String emailProp) {
        this.emailProp = emailProp;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
