package br.iff.pooa20181.rentcontrol.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Contratos extends RealmObject implements Serializable {

    @PrimaryKey
    int id;
    private String nome_locador;
    private String telefone_locador;
    private String email_locador;
    private String nome_locatario;
    private String telefone_locatario;
    private String email_locatario;
    private String rua;
    private String cep;
    private String cidade;


    public Contratos(){

    }

    public Contratos(int id, String nome_locador, String telefone_locador,String email_locador, String nome_locatario, String telefone_locatario, String email_locatario, String rua, String cep, String valor_aluguel, String cidade){

        this.id = id;
        this.nome_locador = nome_locador;
        this.telefone_locador = telefone_locador;
        this.email_locador = email_locador;
        this.nome_locatario = nome_locatario;
        this.telefone_locatario = telefone_locatario;
        this.email_locatario = email_locatario;
        this.rua = rua;
        this.cep = cep;
        this.cidade = cidade;

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_locador() {
        return nome_locador;
    }

    public void setNome_locador(String nome_locador) {
        this.nome_locador = nome_locador;
    }

    public String getTelefone_locador() {
        return telefone_locador;
    }

    public void setTelefone_locador(String telefone_locador) {
        this.telefone_locador = telefone_locador;
    }

    public String getEmail_locador() {
        return email_locador;
    }

    public void setEmail_locador(String email_locador) {
        this.email_locador = email_locador;
    }

    public String getNome_locatario() {
        return nome_locatario;
    }

    public void setNome_locatario(String nome_locatario) {
        this.nome_locatario = nome_locatario;
    }

    public String getTelefone_locatario() {
        return telefone_locatario;
    }

    public void setTelefone_locatario(String telefone_locatario) {
        this.telefone_locatario = telefone_locatario;
    }

    public String getEmail_locatario() {
        return email_locatario;
    }

    public void setEmail_locatario(String email_locatario) {
        this.email_locatario = email_locatario;
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

    public String getValor_aluguel() {
        return valor_aluguel;
    }

    public void setValor_aluguel(String valor_aluguel) {
        this.valor_aluguel = valor_aluguel;
    }

    private String valor_aluguel;

}
