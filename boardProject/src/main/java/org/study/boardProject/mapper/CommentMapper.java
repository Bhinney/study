package org.study.boardProject.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.study.boardProject.dto.CommentDto;
import org.study.boardProject.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentMapper {

	Comment postCommentDtoToComment(CommentDto.Post post);
	Comment patchCommentDtoToComment(CommentDto.Patch patch);
	CommentDto.Response commentToCommentResponseDto(Comment comment, long boardId);

	default List<CommentDto.Response> commentToCommentResponseDto(List<Comment> commentList, long boardId) {
		if ( commentList == null ) {
			return null;
		}

		List<CommentDto.Response> list = new ArrayList<>( commentList.size() );
		for ( Comment comment : commentList ) {
			list.add( commentToResponse( comment, boardId ) );
		}

		return list;
	}

	default CommentDto.Response commentToResponse(Comment comment, long boardId) {
		if ( comment == null ) {
			return null;
		}

		CommentDto.Response response =
			new CommentDto.Response( boardId, comment.getCommentId(), comment.getNickName(),
				comment.getContent() , comment.getCreatedAt(), comment.getModifiedAt());

		return response;
	}
}
