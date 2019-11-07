/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Entity Services
 */
package ASE;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pranavruparelia
 */
@Local
public interface AirlineServEntityFacadeLocal {

    void create(AirlineServEntity airlineServEntity);

    void edit(AirlineServEntity airlineServEntity);

    void remove(AirlineServEntity airlineServEntity);

    AirlineServEntity find(Object id);

    List<AirlineServEntity> findAll();

    List<AirlineServEntity> findRange(int[] range);

    int count();
    
}
