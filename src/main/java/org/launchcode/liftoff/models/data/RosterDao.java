package org.launchcode.liftoff.models.data;

import org.launchcode.liftoff.models.Roster;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface RosterDao extends CrudRepository<Roster, Integer> {
}
