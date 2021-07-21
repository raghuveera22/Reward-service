package com.retailer.app.exception;

import org.springframework.http.HttpStatus;

public class RewardServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private HttpStatus status;


	  public RewardServiceException() {}
	  
	  public RewardServiceException(String s) {
	    super(s);
	  }
	  
	  public RewardServiceException(String s, Throwable throwable) {
	    super(s, throwable);
	  }
	  
	  public RewardServiceException(String s, HttpStatus status) {
	    super(s);
	    this.status = status;
	  }
	  
	  public HttpStatus getStatus() {
	    return this.status;
	  }
	  
	  public void setStatus(HttpStatus status) {
	    this.status = status;
	  }
}
