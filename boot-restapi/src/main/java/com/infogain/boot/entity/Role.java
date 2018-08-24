package com.infogain.boot.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    

   
    @Column(name = "role",unique=true,nullable=false)
    @NotEmpty
    private String role;
    
    @JsonBackReference
    @ManyToMany(mappedBy="roles",fetch = FetchType.EAGER)
	private List<Users> users  =  new ArrayList<>(); 

    public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public Role() {
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
 
} 