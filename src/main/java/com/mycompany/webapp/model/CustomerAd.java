package com.mycompany.webapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.appfuse.model.Address;
import org.appfuse.model.Role;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;


@Entity
@Table(name = "service_request")
@Indexed
@XmlRootElement
public class CustomerAd {
  
	
	private Long id;
    private String source;                    // required
    private String destination;                    // required
    private double weight;
    private String description;
    private Date date;                   // required
    private CFCUser user;              // required
    private Date createdDate;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    public Long getId() {
        return id;
    }


    @Column(name = "source", nullable = false, length = 50)
    @Field
	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}


	@Column(name = "destination", nullable = false, length = 50)
    @Field
	public String getDestination() {
		return destination;
	}



	public void setDestination(String destination) {
		this.destination = destination;
	}


	@Column(name = "weight", nullable = false, length = 50)
    @Field
	public double getWeight() {
		return weight;
	}



	public void setWeight(double weight) {
		this.weight = weight;
	}


	@Column(name = "description", nullable = false, length = 50)
    @Field
	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name = "date", nullable = false, length = 50)
    @Field
	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}


	 @OneToOne
	       @JoinTable(
	            name = "user",
	            joinColumns = { @JoinColumn(name = "user_id") },
	            inverseJoinColumns = @JoinColumn(name = "user_id")
	    )
	public CFCUser getUser() {
		return user;
	}



	public void setUser(CFCUser user) {
		this.user = user;
	}



	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "created_date")
    @Field
	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
    
     
    
    
    
}
