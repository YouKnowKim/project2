package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
public class CommentVo {
	private int comNo;
	private int postNo;
	private int memNo;
	private String content;
	private int order;
	private int parents;
	private int isblind;
	private String writeday;

	public CommentVo(int comNo, int isblind) {
		super();
		this.comNo = comNo;
		this.isblind = isblind;
	}

	public CommentVo(int comNo, String content) {
		super();
		this.comNo = comNo;
		this.content = content;
	}

//	댓글 작성시 사용
	public CommentVo(int postNo, int memNo, String content, int order, int parents) {
		super();
		this.postNo = postNo;
		this.memNo = memNo;
		this.content = content;
		this.order = order;
		this.parents = parents;
	}

//	댓글 목록 조회시 사용
	public CommentVo(int postNo, int memNo, String content, int order, int parents, int isblind, String writeday) {
		super();
		this.postNo = postNo;
		this.memNo = memNo;
		this.content = content;
		this.order = order;
		this.parents = parents;
		this.isblind = isblind;
		this.writeday = writeday;
	}

}
