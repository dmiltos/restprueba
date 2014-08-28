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
 * 29/07/2014  			Pablo			Primera Version
 */

package py.com.catedral.core.services.utils;
import java.util.List;

import py.com.catedral.core.exceptions.AppException;

import com.google.common.base.Strings;


/**
 * Esta clase representa m&eacute;todos utilitarios de validaci&oacute;n
 * @author Pablo
 *
 */
public final class ValidationUtil {

	/**
	 * Constructor por defecto
	 */
	private ValidationUtil() {	}
    
	/**
	 * Verifica si el argumento pasado por par&aacute;metro es nulo o vacio
	 * @param argument 	Par&aacute;metro a validar
	 * @param message   Mensaje de validaci&oacute;n
	 * @return 			El argumento recibido, si es v&aacute;lido
	 * @throws AppException Excepción lanzada en caso de que el argumento recibido sea nulo o una cadena vacía
	 */
	public static String validarArgumento(String argument, String message)
			throws AppException {
		if (Strings.isNullOrEmpty(argument)) {
			throw new AppException.IllegalArgumentException(message);
		}
		return argument;
	}
	
	/**
	 * Valida que el argumento no sea nulo
	 * @param argument		Par&aacute;metro a validar
	 * @param message    	Mensaje de validaci&oacute;n
	 * @return 				El argumento recibido, si es v&aacute;lido
	 * @throws AppException Excepción lanzada en caso de que el argumento recibido sea nulo
	 */

	public static <T> T validarArgumento(T argument, String message)
			throws AppException {
		if (argument == null) {
			throw new AppException.IllegalArgumentException(message);
		}
		return argument;
	}

	/**
	 * Valida si la expresi&oacute;n es <code>true</code> o <code>false</code>
	 * 
	 * @param expression 	Argumeto a validar
	 * @param message   	Mensaje de error en caso de no pasar la validaci&oacute;n
	 * @throws AppException 
	 */
	public static void validarArgumento(boolean expression, String message)
			throws AppException {
		if (!expression) {
			throw new AppException.IllegalArgumentException(message);
		}
	}
	
	
	
	/**
	 * Valida que el objeto pasado por par&aacute;metro no sea nulo ni vac&iacute;o en caso que el objeto sea un array
	 * @param object 	Argumento a validar si es encontrado
	 * @param message 	mensaje de validaci&oacute;n
	 * @return 			El objeto en caso de que se encuentre
	 * @throws AppException
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T validarEncontrado(T object, String message) throws AppException {
		if (object == null) {
			throw new AppException.ResourceNotFoundException(message);
		} else if (object instanceof java.util.Collection) {
			if (((java.util.Collection)object).isEmpty()) {
				throw new AppException.ResourceNotFoundException(message);
			}
		}
		
		return object;
	}
	
	
	/**
	 * Valida que el objeto pasado por par&aacute;metro tiene un s&oacute;lo elemento
	 * @param list El argumento a validar
	 * @param message  Mensaje de error en caso de que no pase la validaci&oacute;n
	 * @return retorna El objeto encontrado en la lista en caso que sea el &uacute;nico 
	 * @throws AppException
	 */
	
	public static <T> T validarUnico(List<T> list, String message) throws AppException {
		if (list.size() > 1) {
			throw new AppException.InternalError(message);
		}
		return list.get(0);
	}
}