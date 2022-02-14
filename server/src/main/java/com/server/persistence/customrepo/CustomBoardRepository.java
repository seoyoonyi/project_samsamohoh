package com.server.persistence.customrepo;

import java.util.List;

import com.server.domain.Board;

public interface CustomBoardRepository {
	public Board getBoardWithCommentAndReply(long serachKeyword);
}
