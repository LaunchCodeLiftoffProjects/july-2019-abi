package org.launchcode.liftoff.models.data;

import org.launchcode.liftoff.models.Roster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RosterDao extends CrudRepository<Roster, Integer> {
}
