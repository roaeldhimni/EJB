/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Admin;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author pc
 */
@Stateless
public class AdminFacade extends AbstractFacade<Admin> {
    @PersistenceContext(unitName = "EJBMachineEmpPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminFacade() {
        super(Admin.class);
    }
    
    public Admin findByUsername(String username) {
        TypedQuery<Admin> query = em.createNamedQuery("Admin.findByUsername", Admin.class);
        query.setParameter("username", username);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Username not found
        }
    }
}
