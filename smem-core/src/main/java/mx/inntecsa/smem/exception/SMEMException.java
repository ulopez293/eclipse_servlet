package mx.inntecsa.smem.exception;

public class SMEMException extends Exception{

	public SMEMException(){
		super();
	}
	
	public SMEMException(String message){
		super(message);
	}
	
	public SMEMException(String message, Throwable cause) { 
		super(message, cause); 
	}
	
	public SMEMException(Throwable cause) { 
		super(cause); 
	}

}
