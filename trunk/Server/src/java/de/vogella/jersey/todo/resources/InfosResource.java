package de.vogella.jersey.todo.resources;


import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.vogella.jersey.todo.dao.InfoDao;
import de.vogella.jersey.todo.model.Info;



// Will map the resource to the URL todos
@Path("/Infos")
public class InfosResource {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;


	// Return the list of todos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Info> getInfosBrowser() {
		List<Info> infos = new ArrayList<Info>();
		infos.addAll( InfoDao.instance.getModel().values() );
		return infos;
	}

	// Return the list of todos for applications
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Info> getTodos() {
		List<Info> infos = new ArrayList<Info>();
		infos.addAll( InfoDao.instance.getModel().values() );
		return infos;
	}


	// retuns the number of todos
	// Use http://localhost:8080/de.vogella.jersey.todo/rest/todos/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = InfoDao.instance.getModel().size();
		//System.out.println("ENTRA2222");
                return String.valueOf(count)+"\n "+InfoDao.instance.getMedicoes().print_statistics();
                
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newInfo(
                        @FormParam("id") String id,
			@FormParam("caller_id") String caller_id,
			@FormParam("duration") int duration,
                        @FormParam("billsec") int billsec,
                        @FormParam("billmsec") int billmsec,
                        @FormParam("progressec") int progressec,
                        @FormParam("progress_mediasec") int progress_mediasec,
                        @FormParam("flow_billsec") int flow_billsec,
                        @FormParam("mduration") int mduration,
                        @FormParam("progressmsec") int progressmsec,
                        @FormParam("progress_mediamsec") int progress_mediamsec,
                        @FormParam("flow_billmsec") int flow_billmsec,
                        @FormParam("uduration") int uduration,
			@Context HttpServletResponse servletResponse
                
                
	) throws IOException {
		Info info = new Info(id,caller_id, duration, billsec, billmsec, progressec, progress_mediasec, flow_billsec, mduration, progressmsec, progress_mediamsec, flow_billmsec, uduration);
		System.out.println("ENTRA");
//                if (description!=null){
//			Info.setDescription(description);
//		}
		InfoDao.instance.getModel().put(id, info);

		URI uri = uriInfo.getAbsolutePathBuilder().path(id).build();
		Response.created(uri).build();

		servletResponse.sendRedirect("../create_todo.html");
	}


	// Defines that the next path parameter after todos is
	// treated as a parameter and passed to the TodoResources
	// Allows to type http://localhost:8080/de.vogella.jersey.todo/rest/todos/1
	// 1 will be treaded as parameter todo and passed to TodoResource
	@Path("{info}")
	public InfoResource getInfo(
			@PathParam("info") String id) {
		return new InfoResource(uriInfo, request, id);
	}

}
