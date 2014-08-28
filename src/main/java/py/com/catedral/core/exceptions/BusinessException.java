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

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;

/**
 * 
 * Excepci&oacute;n a propagar cuando ocurra una violaci&oacute;n de alguna regla de negocio.
 * 
 * @author pablo
 *
 */
public abstract class BusinessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -672923228461562741L;

	public BusinessException(String message) {
		super(message);
	}

	/**
	 * Esta clase representa una violaci&oacute;n de una o m&aacute;s reglas de negocio relacionadas a
	 * restricciones del modelo
	 * 
	 * @author Pablo
	 *
	 */
	public static class BzRulesViolationException extends BusinessException {

		private static final long serialVersionUID = 1149303182428995606L;

		/**
		 * 
		 * @param message Mensaje de la excepci&oacute;n.
		 * @param violationMessages Contiene la lista de violaciones o contraints que fueron violadas.
		 */
		public BzRulesViolationException(String message, List<String> violationMessages) {

			super(message);

			if (violationMessages!=null && violationMessages.size()>0) {
				this.violationMessages = violationMessages;
			} else {
				this.violationMessages = new ArrayList<String>();
			}
		}

		/**
		 * 
		 * @param message Mensaje de la excepci&oacute;n.
		 * @param violationMessage Contiene la violaci&oacute;n o contraint que fue violada.
		 */
		public BzRulesViolationException(String message, String violationMessage) {

			super(message);

			if (!Strings.isNullOrEmpty(violationMessage)) {

				this.violationMessages = new ArrayList<String>();
				this.violationMessages.add(violationMessage);

			} else {

				this.violationMessages = new ArrayList<String>();

			}
		}

		private List<String> violationMessages;

		public List<String> getViolationMessages() {
			return violationMessages;
		}

		public void setViolationMessages(List<String> violationMessages) {
			this.violationMessages = violationMessages;
		}
	}

	/**
	 * Esta clase representa la violaci&oacute;n de una regla de negocios.
	 *
	 */
	public static class BzRuleViolationException extends BusinessException {

		private static final long serialVersionUID = 1149303182428995606L;

		/**
		 * 
		 * @param message Mensaje de la excepci&oacute;n.
		 * @param violation Contiene la violaci&oacute;n cometida sobre una regla de negocio.
		 */
		public BzRuleViolationException(String violation) {
			super(violation);
		}
	}

}
