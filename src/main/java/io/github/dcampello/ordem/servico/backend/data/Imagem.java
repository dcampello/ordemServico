/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dcampello.ordem.servico.backend.data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Daniel
 */
public class Imagem {
    
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private OrdemServico id;
    
    @Column
    private byte imagem;
}
