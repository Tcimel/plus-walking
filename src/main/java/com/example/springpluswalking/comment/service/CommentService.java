package com.example.springpluswalking.comment.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springpluswalking.comment.dto.request.CommentRequestDto;
import com.example.springpluswalking.comment.dto.response.CommentPageResponseDto;
import com.example.springpluswalking.comment.dto.response.CommentReplyResponseDto;
import com.example.springpluswalking.comment.dto.response.CommentResponseDto;
import com.example.springpluswalking.comment.dto.response.CommentResponseWithMessage;
import com.example.springpluswalking.comment.entity.Comment;
import com.example.springpluswalking.comment.exception.CommentErrorCode;
import com.example.springpluswalking.comment.exception.CommentException;
import com.example.springpluswalking.comment.repository.CommentRepository;
import com.example.springpluswalking.schedule.entity.Schedule;
import com.example.springpluswalking.schedule.repository.ScheduleRepository;
import com.example.springpluswalking.user.entity.User;
import com.example.springpluswalking.user.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final ScheduleRepository scheduleRepository;
	private final UserRepository userRepository;

	public CommentResponseDto createComment(Long scheudleId, @Valid CommentRequestDto requestDto, Long userId) {
		Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheudleId);
		User writer = userRepository.findByIdOrElseThrow(userId);

		Comment comment = Comment.builder()
			.content(requestDto.getContent())
			.schedule(findSchedule)
			.userEmail(writer.getEmail())
			.parentComment(null)
			.build();

		commentRepository.save(comment);
		return new CommentResponseDto(comment,0);
	}

	public CommentResponseWithMessage findAllAsList(Long scheduleId){
		List<Comment> commentList = commentRepository.findByScheduleId(scheduleId);
		commentList.sort(Comparator.comparing(Comment::getCreatedAt));
		List<CommentResponseDto> dtoList = commentList.stream()
			.map(comment -> new CommentResponseDto(comment,commentRepository.countByParentCommentId(comment.getId()))).toList();
		String message = dtoList.isEmpty() ? "댓글이 없습니다." : "";
		return CommentResponseWithMessage.builder()
			.message(message)
			.comments(dtoList)
			.build();
	}

	public Page<CommentPageResponseDto> findAll(Long scheduleId, int page, int size) {
		int adjustPage = (page>0) ? page-1 : 0;

		PageRequest pageable = PageRequest.of(adjustPage, size, Sort.by("createdAt").ascending());

		Page<Comment> commentPage = commentRepository.findByScheduleIdOrElseThrow(scheduleId, pageable);

		return commentPage.map(comment -> CommentPageResponseDto.builder()
			.id(comment.getId())
			.childrenCount(commentRepository.countByParentCommentId(comment.getId()))
			.userEmail(comment.getUserEmail())
			.content(comment.getContent())
			.createdAt(comment.getCreatedAt())
			.build()
		);
	}

	public CommentResponseDto findById(Long cid) {
		Comment findComment = commentRepository.findByIdOrElseThrow(cid);
		return new CommentResponseDto(findComment,getChildrenCommentCount(cid));
	}

	@Transactional
	public CommentResponseDto updateComment(Long cid, Long userId, CommentRequestDto requestDto){
		User findUser = userRepository.findByIdOrElseThrow(userId);
		Comment findComment = commentRepository.findByIdOrElseThrow(cid);

		if(!findComment.getUserEmail().equals(findUser.getEmail())){
			throw new CommentException(CommentErrorCode.NO_PERMISSION);
		}

		findComment.update(requestDto.getContent());
		commentRepository.save(findComment);
		return new CommentResponseDto(findComment,getChildrenCommentCount(cid));
	}

	public void deleteComment(Long cid, Long userId){
		User findUser = userRepository.findByIdOrElseThrow(userId);
		Comment findComment = commentRepository.findByIdOrElseThrow(cid);

		if(!findComment.getUserEmail().equals(findUser.getEmail())){
			throw new CommentException(CommentErrorCode.NO_PERMISSION);
		}
		commentRepository.delete(findComment);
	}

	public int getCommentCount(Long id){
		return commentRepository.countByScheduleId(id);
	}

	public int getChildrenCommentCount(Long parentId){
		return commentRepository.countByParentCommentId(parentId);
	}

	public CommentReplyResponseDto createReply(Long id, Long userId, @Valid CommentRequestDto requestDto) {
		Comment parentComment = commentRepository.findByIdOrElseThrow(id);
		User writer = userRepository.findByIdOrElseThrow(userId);

		if(parentComment.getParentComment()!=null){
			throw new CommentException(CommentErrorCode.CANNOT_REPLY);
		}

		Comment comment = Comment.builder()
			.content(requestDto.getContent())
			.schedule(parentComment.getSchedule())
			.userEmail(writer.getEmail())
			.parentComment(parentComment)
			.build();

		commentRepository.save(comment);
		return new CommentReplyResponseDto(comment, commentRepository.countByParentCommentId(id));
	}
}
