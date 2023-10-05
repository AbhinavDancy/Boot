
package com.dancy.blog.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String category;
	private String categoryDescription;
	
	@OneToMany(mappedBy="category",cascade = CascadeType.ALL)
	 private List<Posts> posts;
	
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", category=" + category + ", categoryDescription=" + categoryDescription + "]";
	}
	
	
}
