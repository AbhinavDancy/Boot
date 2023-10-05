package com.dancy.blog.entity;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.dancy.blog.payloads.CategoryDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	private String content;
	private String title;
	private String image ;
	private LocalDate postDate;
	
	@ManyToOne
	private Category category;
	@ManyToOne
	private User user;
	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", content=" + content + ", title=" + title + ", image=" + image
				+ ", postDate=" + postDate + ", category=" + category + ", user=" + user + "]";
	}
	
	
}
