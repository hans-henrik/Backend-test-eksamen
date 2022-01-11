/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

/**
 *
 * @author EG
 */
import dtos.OwnerDTO;
import entities.Owner;
import errorhandling.API_Exception;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


public class OwnerFacade {

    private static EntityManagerFactory emf;
    private static OwnerFacade instance;

    private OwnerFacade() {
    }

    public static OwnerFacade getOwnerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OwnerFacade();
        }
        return instance;
    }   
    
    public List<OwnerDTO> getAllOwners() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
        List<Owner> owners = query.getResultList();
        owners.forEach(o -> System.out.println(o));
        return OwnerDTO.getDTOs(owners);
    }
    
    
       public List<Owner> getAllOwners1() throws API_Exception {
        EntityManager em = emf.createEntityManager();
        try{
        TypedQuery<Owner> query = em.createQuery("SELECT o FROM Owner o", Owner.class);
        return query.getResultList();
        }catch (Exception e)
        {
            throw new API_Exception(e.getMessage());
        }
    }
    
}