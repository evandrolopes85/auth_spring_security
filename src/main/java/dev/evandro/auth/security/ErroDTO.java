package dev.evandro.auth.security;

public class ErroDTO {

	private int status;
	private String message;

	public ErroDTO(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ErroDTO() {
		super();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
