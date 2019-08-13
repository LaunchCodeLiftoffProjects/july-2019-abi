package org.launchcode.liftoff.models.data;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TeamDao extends CrudRepository <Team, Integer> {
}
