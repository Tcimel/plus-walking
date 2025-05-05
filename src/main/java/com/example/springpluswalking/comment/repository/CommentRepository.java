package com.example.springpluswalking.comment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springpluswalking.comment.entity.Comment;
import com.example.springpluswalking.comment.exception.CommentErrorCode;
import com.example.springpluswalking.comment.exception.CommentException;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	Page<Comment> findByScheduleId(Long scehduleId, PageRequest request);

	default Page<Comment> findByScheduleIdOrElseThrow(Long scehduleId,PageRequest request){
		Page<Comment> commentPage = findByScheduleId(scehduleId, request);
		if(commentPage.isEmpty()){
			throw new CommentException(CommentErrorCode.COMMENT_NOT_FOUND);
		}
		return commentPage;
	}

	default Comment findByIdOrElseThrow(Long id){
		return findById(id).orElseThrow(()->new CommentException(CommentErrorCode.COMMENT_NOT_FOUND));
	}

	int countByScheduleId(Long id);
}
