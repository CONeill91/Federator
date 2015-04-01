package com.gsc.federator.domain;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dwalsh on 31/03/2015.
 */
@Entity
public class LinkClickedRecord implements DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 512) //TODO define len
    private String query_id;

    @Column
    private Date date;

    @Column(length = 512)
    private String link;

    //constructor to automatically set date when record is created
    public LinkClickedRecord(){
        this.date = Calendar.getInstance().getTime();

    }
    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;

    }
    public void setQuery_id(final String id){
        this.query_id = id;
    }
    public void setLink(final String link) { this.link = link; }

    public String getLink(){ return link; }

    public Long getId() {
        return id;
    }

    public String getQueryId() {
        return query_id;
    }

    public void setQueryId(final String id) {
        this.query_id = id;
    }

    @Override
    public String toString() {
        return "SearchQuery{" +
                "date=" + date +
                ", id=" + id +
                ", link=" + link +
                ", query_id='" + query_id + '\'' +
                '}';
    }
}
