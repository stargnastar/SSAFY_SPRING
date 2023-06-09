package com.ssafy.ssafit.model.dto;

public class Subscribe {
	private int id; //영상번호
	private int subscriber; //구독자
 	
	public Subscribe() {}
	public Subscribe(int id, int subscriber) {
		this.id = id;
		this.subscriber = subscriber;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(int subscriber) {
		this.subscriber = subscriber;
	}


	
	
	
}
