package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "autoridades")
public class Autoridades extends BaseEntity{
    @ManyToOne
	@JoinColumn(name = "usuario")
	Usuario usuario;
	
	@Size(min = 3, max = 50)
	String autoridad;
	
}