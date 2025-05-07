package com.example.springpluswalking.comment.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import com.example.springpluswalking.common.entity.BaseEntity;
import com.example.springpluswalking.schedule.entity.Schedule;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String userEmail;

	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_id")
	private Schedule schedule;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_comment_id")
	private Comment parentComment;

	@OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
	@BatchSize(size = 10)
	private List<Comment> childComments = new ArrayList<>();

	public Comment(Long id, String userEmail, String content, Schedule schedule, Comment parentComment, List<Comment> childComments) {
		this.id = id;
		this.userEmail = userEmail;
		this.content = content;
		this.schedule = schedule;
		this.parentComment = parentComment;
		this.childComments = (childComments != null) ? childComments : new ArrayList<>();
	}

	public void update(String content){
		this.content = content;
	}

}
