package br.com.dalcatech.allfoodpay.entity;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gustavo on 5/17/2017.
 */

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @SerializedName("Name")
    private String name;

    private String password;

    private String rg;

    private String cpf;

    @SerializedName("BirthDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;

    @SerializedName("Email")
    private String email;

    private String gender;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
