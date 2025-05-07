package com.example.springpluswalking.schedule.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import com.example.springpluswalking.comment.entity.Comment;
import com.example.springpluswalking.common.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@Table(name = "schedule")
public class Schedule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String userEmail;

	@Column(nullable = false)
	private String title;

	private String content;

	@OneToMany(
		cascade = CascadeType.ALL,
		mappedBy = "schedule"
	)
	@BatchSize(size = 30)
	private List<Comment> comments;

	public Schedule(Long id, String userEmail, String title, String content, List<Comment> comments) {
		this.id = id;
		this.userEmail = userEmail;
		this.title = title;
		this.content = content;
		this.comments = comments != null ? comments : new ArrayList<>();
	}

	public void updateTitle(String title){
		this.title = title;
	}
	public void updateContent(String content){
		this.content = content;
	}
}
