package py.com.catedral.core.rs.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

//@Provider
//@PreMatching
//public class RESTCorsResponseFilter implements ContainerResponseFilter {
//
//    private final static Logger log = Logger.getLogger( RESTCorsResponseFilter.class.getName() );
//
////    @Override
////    public void filter( ContainerRequestContext requestCtx, ContainerResponseContext responseCtx ) throws IOException {
////        
////    }
//
//	/* (non-Javadoc)
//	 * @see com.sun.jersey.spi.container.ContainerResponseFilter#filter(com.sun.jersey.spi.container.ContainerRequest, com.sun.jersey.spi.container.ContainerResponse)
//	 */
//	@Override
//	public ContainerResponse filter(ContainerRequest requestCtx,
//			ContainerResponse responseCtx) {
//		log.info( "Executing REST response filter" );
//
//        responseCtx.getHttpHeaders().add( "Access-Control-Allow-Origin", "*" );
//        responseCtx.getHttpHeaders().add( "Access-Control-Allow-Credentials", "true" );
//        responseCtx.getHttpHeaders().add( "Access-Control-Allow-Methods", "GET, POST, DELETE, PUT" );
//	}
//}