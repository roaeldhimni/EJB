/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Employe;
import entities.Machine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author lenovo
 */
@Stateless
public class MachineFacade extends AbstractFacade<Machine> {
    @PersistenceContext(unitName = "EJBMachineEmpPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MachineFacade() {
        super(Machine.class);
    }
    
     public List<Machine> getMachinesByEmployee(Employe employe) {
        TypedQuery<Machine> query = em.createQuery("SELECT m FROM Machine m WHERE m.employe = :employe", Machine.class);
        query.setParameter("employe", employe);
        return query.getResultList();
     }

    public List<Object[]> getMachineAcquisitionData() {
        String jpqlQuery = "SELECT FUNCTION('YEAR', m.dateAchat), COUNT(m) FROM Machine m GROUP BY FUNCTION('YEAR', m.dateAchat)";
         Query query = getEntityManager().createQuery(jpqlQuery);
        return query.getResultList();
    }
    
}
