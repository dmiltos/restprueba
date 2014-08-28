/*
 * $Id$
 * ===========================================================================
 * Copyright 2014 Eidos S.A.
 * Todos los derechos reservados.
 * ===========================================================================
 */
 
/*
 * Historial de cambio:
 *
 * Fecha             	Origen        	Descripcion
 * ----             	------        	--------------------------------------------------
 * 01/06/2014  			pablo			Primera Version
 */
 
package py.com.catedral.core.exceptions;
 
/**
 * 
 * @author pablo
 *
 */
@javax.ejb.ApplicationException(rollback=true)
public class AppException extends Exception {

	private static final long serialVersionUID = 2424527599593553087L;

	public AppException() {
		super();
	}
    /**
     * 
     * @param message
     */
	public AppException(String message) {
		super(message);
	}
    /**
     * 
     * @param message
     * @param cause
     */
	public AppException(String message,  Throwable cause) {
		super(message, cause);
	}
    /**
     * 
     * @param cause
     */
	public AppException(Throwable cause) {
		super(cause);
	}
  
	public static class InternalError extends AppException {

		private static final long serialVersionUID = -3139036056656795230L;

		public InternalError(String message) {
			super(message);
		}

		public InternalError(String message,  Throwable cause) {
			super(message, cause);
		}

		public InternalError(Throwable cause) {
			super(cause);
		}

	}

	public static class IllegalArgumentException extends AppException {

		private static final long serialVersionUID = -8765864922699868014L;

		public IllegalArgumentException(String message) {
			super(message);
		}

	}

	public static class ResourceNotFoundException extends AppException {

		private static final long serialVersionUID = 1149303182428995606L;

		public ResourceNotFoundException(String message) {
			super(message);
		}
	}
	
	public static class NonUniqueResultException extends AppException {
		
		private static final long serialVersionUID = 1149303182428995606L;

		public NonUniqueResultException(String message) {
			super(message);
		}
	}

    /**
     * Esta clase representa una excepci&oacute;n que se propaga para abortar la transacci&oacute;n
     * activa en un contexto EJB.
     * @author pablo
     *
     */
    @javax.ejb.ApplicationException(rollback=true)
	public static class AbortingException extends AppException {
		
		private static final long serialVersionUID = 1015472722434313503L;

		public AbortingException(String message) {
			super(message);
		}
	}
}

