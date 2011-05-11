package de.vogella.jersey.todo.resources;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import de.vogella.jersey.todo.dao.InfoDao;
import de.vogella.jersey.todo.model.Info;

public class InfoResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	public InfoResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	//Application integration
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Info getInfo() {
		Info info = InfoDao.instance.getModel().get(id);
		if(info==null)
			throw new RuntimeException("Get: Todo with " + id +  " not found");
		return info;
	}

	// For the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Info getInfoHTML() {
		Info info = InfoDao.instance.getModel().get(id);
		if(info==null)
			throw new RuntimeException("Get: Todo with " + id +  " not found");
		return info;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putInfo(JAXBElement<Info> info) {
            System.out.println("ENTRA PUT XML");
		Info c = info.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteInfo() {
		Info c = InfoDao.instance.getModel().remove(id);
		if(c==null)
			throw new RuntimeException("Delete: Todo with " + id +  " not found");
	}

	private Response putAndGetResponse(Info info) {
		Response res;
                System.out.println("ENTRA PUT n sei");
		if(InfoDao.instance.getModel().containsKey(info.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		InfoDao.instance.getModel().put(info.getId(), info);
                long tempoFinal = System.currentTimeMillis();
                int tempoInicial =info.getTempoInicial();
                long tempo = tempoFinal - tempoInicial;
                
                InfoDao.instance.getMedicoes().add(tempo);
		return res;
	}



}
