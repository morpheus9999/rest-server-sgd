package de.vogella.jersey.todo.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import de.vogella.jersey.todo.dao.InfoDao;
import de.vogella.jersey.todo.model.Info;

public class InfoResourceWithHTMLHeader_NotUsed {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	public InfoResourceWithHTMLHeader_NotUsed(UriInfo uriInfo, Request request, String id) {
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
	public Info getTodoHTML() {
		Info info = InfoDao.instance.getModel().get(id);
		if(info==null)
			throw new RuntimeException("Get: Todo with " + id +  " not found");
		return info;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putContact(JAXBElement<Info> info) {
            System.out.println("ENTRA");
		Info c = info.getValue();
		return putAndGetResponse(c);
	}


	@PUT
	public Response putInfo(@Context HttpHeaders headers, byte[] in) {
            System.out.println("ENTRA");
		Map<String,String> params = parse(new String(in));
                Info m= new Info(params.get("id"),params.get("Caller_id"), Integer.parseInt(params.get("duration")), Integer.parseInt(params.get("billsec")), Integer.parseInt(params.get("billmsec")), Integer.parseInt(params.get("progressec")), Integer.parseInt(params.get("progress_mediasec")), Integer.parseInt(params.get("flow_billsec")), Integer.parseInt(params.get("mduration")), Integer.parseInt(params.get("progressmsec")), Integer.parseInt(params.get("progress_mediamsec")), Integer.parseInt(params.get("flow_billmsec")), Integer.parseInt(params.get("uduration")));
                
		//Info c = new Info(params.get("id"), params.get("summary"));

		return putAndGetResponse(m);
	}

	@DELETE
	public void deleteInfo() {
		Info c = InfoDao.instance.getModel().remove(id);
		if(c==null)
			throw new RuntimeException("Delete: Info with " + id +  " not found");
	}

	private Response putAndGetResponse(Info info) {
            System.out.println("ENTRA");
		Response res;
		if(InfoDao.instance.getModel().containsKey(info.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		InfoDao.instance.getModel().put(info.getId(), info);
		return res;
	}



	public Map<String,String> parse(String paramString) {
		Map<String,String> params = new HashMap<String,String>();
		String[] paramPairs = paramString.split("&");
		for(String param : paramPairs) {
			String[] key_value = param.split("=");
			params.put(key_value[0], key_value[1]);
		}
		return params;
	}
}
