/**
 * 
 */
package uk.tw.enery.dto;

/**
 * @author nc23370_sandeep_kumar_soni
 *
 */
public class ApiResponse<T> {
	
	private String message;
	private Integer statusCode;
	private T data;
	
	
	
	
	public ApiResponse(String message, Integer statusCode, T data) {
		this.data = data;
		this.message = message;
		this.statusCode = statusCode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	

}
