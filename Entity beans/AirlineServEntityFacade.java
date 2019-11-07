/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Entity Services
 */
package ASE;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pranavruparelia
 */
@Stateless
public class AirlineServEntityFacade extends AbstractFacade<AirlineServEntity> implements AirlineServEntityFacadeLocal {

    @PersistenceContext(unitName = "AirlineServEntity-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AirlineServEntityFacade() {
        super(AirlineServEntity.class);
    }
    
}
