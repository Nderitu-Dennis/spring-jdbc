package tech.csm.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Country implements Serializable {

	private Integer id;
	
	private String name;
}
