package com.gsc.federator.domain;

import javax.persistence.*;

/**
 * Created by dwalsh on 30/03/2015.
 */
@Entity
public class SearchQueryRecord implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 512) //TODO define len
    private String query;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(final String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                ", id=" + id +
                ", query='" + query + '\'' +
                '}';
    }
}
