package com.LoanSystem.model;
import java.io.Serializable;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;



@Entity
@Table(name="role")
public class Role extends BaseEntity  implements Serializable {

 	private static final long serialVersionUID = 1L;
 	
	
	@Column(name="role_name")
    String name;
	
	@JsonIgnore
	 @ManyToMany(mappedBy = "roles")
	 private Set<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	
}