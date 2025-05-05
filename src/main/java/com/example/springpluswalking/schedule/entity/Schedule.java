package com.example.springpluswalking.schedule.entity;

import com.example.springpluswalking.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "scehdule")
public class Schedule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String userEmail;

	@Column(nullable = false)
	private String title;

	private String content;

	public Schedule(Long id, String userEmail, String title, String content) {
		this.id = id;
		this.userEmail = userEmail;
		this.title = title;
		this.content = content;
	}

	public void updateTitle(String title){
		this.title = title;
	}
	public void updateContent(String content){
		this.content = content;
	}
}
