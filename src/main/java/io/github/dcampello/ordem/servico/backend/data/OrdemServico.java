/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dcampello.ordem.servico.backend.data;

import io.github.dcampello.ordem.servico.backend.enumerado.ordemServico.Status;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Daniel
 */
@Entity
@Table
public class OrdemServico implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_registro",nullable = false, updatable = false)
    private Date dataRegistro;
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Usuario usuario;
    
    @Column(length = 45, nullable = false)
    private String nome;
    
    @Column(length = 10, nullable = false)
    private String telefone;
    
    @Column(length = 100, nullable = false)
    private String endereco;
    
    @Column(length = 45, nullable = false)
    private String email;
    
    @Column(length = 45, nullable = false)
    private String equipamentoNome;
    
    @Column(length = 45, nullable = false)
    private String tipo;
    
    @Column(length = 45, nullable = false)
    private String marca;
    
    @Column(length = 200, nullable = false)
    private String defeito;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Status status;
    
 //   private List<byte> imagem = new List<byte>;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEquipamentoNome() {
        return equipamentoNome;
    }

    public void setEquipamentoNome(String equipamentoNome) {
        this.equipamentoNome = equipamentoNome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemServico other = (OrdemServico) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrdemServico{" + "id=" + id + ", dataRegistro=" + dataRegistro + ", usuario=" + usuario + ", nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + ", email=" + email + ", equipamentoNome=" + equipamentoNome + ", tipo=" + tipo + ", marca=" + marca + ", defeito=" + defeito + ", status=" + status + '}';
    }


 
    
    
}
