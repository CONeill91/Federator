package com.gsc.federator.domain;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

/**
 * Created by dwalsh on 30/03/2015.
 */
public interface SearchQueryRepository extends CrudRepository<SearchQueryRecord, Long> {

}
