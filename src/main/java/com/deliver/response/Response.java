package com.deliver.response;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

public class Response<T> {

	private static final Logger log = LoggerFactory.getLogger(Response.class);

	private T data;
	private List<String> messages;


	public Response() {
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<String> getMessages() {
		if (this.messages == null) {
			this.messages = new ArrayList<String>();
		}
		return messages;
	}

	public boolean containMessages() {
		return !getMessages().isEmpty();
	}

	public void setMessages(List<String> messages) {
		messages.forEach(msg -> {
			addMessage(msg);
		});
	}
	
	public void addMessage(String message) {
		log.info(message);
		getMessages().add(message);
	}

	public void addMessages(BindingResult result) {
		result.getAllErrors().forEach( e -> addMessage(e.getDefaultMessage()));
	}

}
