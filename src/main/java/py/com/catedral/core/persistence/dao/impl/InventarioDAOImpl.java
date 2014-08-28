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
 * 10/08/2014  			pablo			Primera Version
 */
 
package py.com.catedral.core.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import py.com.catedral.core.persistence.dao.InventarioDAO;
import py.com.catedral.core.persistence.entities.Producto;
import py.com.catedral.core.services.jpa.CoreSessionFactoryWrapper;
 
/**
 * @author pablo
 *
 */
@Repository
public class InventarioDAOImpl implements InventarioDAO {
	
	
	/* (non-Javadoc)
	 * @see py.com.catedral.core.persistence.dao.InventarioDAO#chequearProductoValidoYTomarInventario(java.lang.String, java.lang.String)
	 */
    @Transactional
	public Producto chequearProductoValidoYTomarInventario(
			String codigoDeBarras, String codigoInventario)
			throws DataAccessException {
//    	Query q = CoreSessionFactoryWrapper.getEntityManager().createNamedQuery("callInventarioStoreProcedure");
//    	Producto singleResult = (Producto) q.getSingleResult();
//    	return singleResult;
    	return null;
	}

	/* (non-Javadoc)
	 * @see py.com.catedral.core.persistence.dao.InventarioDAO#getProductos()
	 */
	@Override
	public List<Producto> getProductos() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
}
