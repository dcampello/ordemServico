/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.dcampello.ordem.servico.backend.business;
import io.github.dcampello.ordem.servico.backend.data.OrdemServico;
import io.github.dcampello.ordem.servico.backend.enumerado.ordemServico.Status;
import io.github.dcampello.ordem.servico.backend.infra.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
 
 /**
  *
  * @author Daniel
  */
 public class OrdemServicoBus {
 
     public Long inserir(OrdemServico ordemServico) {
         ordemServico.setDataRegistro(new Date());
         ordemServico.setStatus(Status.NOVA);
         ordemServico.setUsuario(UsuarioBus.selecionarOperador());
 
         Session s = HibernateUtil.getSessionFactory().openSession();
         Transaction t = s.beginTransaction();
         s.save(ordemServico);
         t.commit();
         return ordemServico.getId();
     }
 
     public void alterar(OrdemServico ordemServico) {
         Session s = HibernateUtil.getSessionFactory().openSession();
         Transaction t = s.beginTransaction();
         s.merge(ordemServico);
         t.commit();
     }
 
     public void excluir(long id) {
         Session s = HibernateUtil.getSessionFactory().openSession();
         OrdemServico c = selecionar(id);
 
         Transaction t = s.beginTransaction();
         s.delete(c);
         t.commit();
     }
 
     public OrdemServico selecionar(long id) {
         return (OrdemServico) HibernateUtil.getSessionFactory()
                 .openSession()
                 .get(OrdemServico.class, id);
     }
 
     public List<OrdemServico> listar() {
         return (List<OrdemServico>) HibernateUtil.getSessionFactory()
                 .openSession()
                 .createQuery("from OrdemServico")
                 .list();
     }
 }