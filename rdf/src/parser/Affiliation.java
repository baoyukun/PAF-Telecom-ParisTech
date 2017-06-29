package parser;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Affiliation {
	private Set<String> department;
	private Set<String> laboratory;
	private Set<String> institution;
	private Set<String> settlement;
	private Set<String> country;
	private Set<String>	group;
	private Set<String> address;
	
	
	public Affiliation() {
		this.department  = new HashSet<String>();
		this.laboratory  = new HashSet<String>();
		this.institution  = new HashSet<String>();
		this.settlement  = new HashSet<String>();
		this.country = new HashSet<>();
		this.group = new HashSet<>();
		
		
		
	}
	
	
	public final Set<String> getGroup() {
		return group;
	}
	
	@JsonDeserialize(using = SetDeserializer.class)
	public final void setGroup(Set<String> group) {
		this.group = group;
	}


	public final Set<String>  getDepartment() {
		return department;
	}
	
	@JsonDeserialize(using = SetDeserializer.class)
	public final void setDepartment(Set<String>  departement) {
		this.department = departement;
	}
	public final Set<String>  getLaboratory() {
		return laboratory;
	}
	
	@JsonDeserialize(using = SetDeserializer.class)
	public final void setLaboratory(Set<String>  laboratory) {
		this.laboratory = laboratory;
	}
	public final Set<String>  getInstitution() {
		return institution;
	}
	
	@JsonDeserialize(using = SetDeserializer.class)
	public final void setInstitution(Set<String>  institution) {
		this.institution = institution;
	}
	public final Set<String>  getSettlement() {
		return settlement;
	}
	
	@JsonDeserialize(using = SetDeserializer.class)
	public final void setSettlement(Set<String>  settlement) {
		this.settlement = settlement;
	}
	
	public final Set<String>  getCountry() {
		return country;
	}
	
	@JsonDeserialize(using = SetDeserializer.class)
	public final void setCountry(Set<String>  country) {
		this.country = country;
	}
	public final Set<String> getAddress() {
		return address;
	}
	
	@JsonDeserialize(using = SetDeserializer.class)
	public final void setAddress(Set<String> address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuilder output = new StringBuilder();
		output.append("{");
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object obj = field.get(this); 
				
				if(obj == null) output.append(field.getName() + ":null");
				else output.append(field.getName() + ":" + obj.toString());
				output.append(",");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		output.deleteCharAt(output.lastIndexOf(","));
		output.append("}");
		return output.toString();
		
	}

}
