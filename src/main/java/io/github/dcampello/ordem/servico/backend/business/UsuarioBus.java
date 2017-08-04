/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dcampello.ordem.servico.backend.business;

 import io.github.dcampello.ordem.servico.backend.data.Usuario;
 import io.github.dcampello.ordem.servico.backend.infra.HibernateUtil;
 import java.util.Date;
 import org.apache.commons.codec.digest.DigestUtils;
 import org.hibernate.Session;
 import org.hibernate.Transaction;
 
 /**
  *
  * @author operador
  */
 public class UsuarioBus {
     // TODO Remover método temporario
 
     public static Usuario selecionarOperador() {
         Usuario operador = (Usuario) HibernateUtil.getSessionFactory()
                 .openSession()
                 .createQuery("from Usuario where login = :login")
                 .setString("login", "operador")
                 .uniqueResult();
 
         if (operador == null) {
             operador = new Usuario();
             operador.setLogin("operador");
             operador.setSenha(DigestUtils.sha256Hex("123"));
             operador.setNome("Operador Teste");
             operador.setDataRegistro(new Date());
             operador.setAtivo(true);
 
             UsuarioBus usuarioBus = new UsuarioBus();
             usuarioBus.inserir(operador);
         }
 
         return operador;
     }
 
     public Long inserir(Usuario usuario) {
         Session s = HibernateUtil.getSessionFactory().openSession();
         Transaction t = s.beginTransaction();
         s.save(usuario);
         t.commit();
         return usuario.getId();
     }
 
     public Usuario selecionar(long id) {
         return (Usuario) HibernateUtil.getSessionFactory()
                 .openSession()
                 .get(Usuario.class, id);
     }
 }