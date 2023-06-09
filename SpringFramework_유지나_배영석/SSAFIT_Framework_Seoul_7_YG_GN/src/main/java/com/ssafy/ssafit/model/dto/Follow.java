package com.ssafy.ssafit.model.dto;

public class Follow {
	
	private int follow, followed;

	public Follow() {
	}

	public Follow(int follow, int followed) {
		this.follow = follow;
		this.followed = followed;
	}

	public int getFollow() {
		return follow;
	}

	public void setFollow(int follow) {
		this.follow = follow;
	}

	public int getFollowed() {
		return followed;
	}

	public void setFollowed(int followed) {
		this.followed = followed;
	}

	@Override
	public String toString() {
		return "Follow [follow=" + follow + ", followed=" + followed + "]";
	}
	
	

	
	

}
