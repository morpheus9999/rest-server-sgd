

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;


import de.vogella.jersey.todo.model.Todo;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Tester {

	public static void main(String[] args) throws ParserConfigurationException, TransformerConfigurationException, TransformerException {

                Generator gen = new Generator();
                Info[] dados;

                int i = 0;
                dados = gen.getDados();

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		WebResource service = client.resource("http://169.254.254.83:8084/Server/");
		// Create one todo
		Todo todo = new Todo("4", "Blabla" );
//Info info = new Info();
		ClientResponse response = service.path("rest").path("todos").path(todo.getId()).accept(MediaType.APPLICATION_XML).put(ClientResponse.class, todo);
		// Return code should be 201 == created resource
		/*System.out.println(response.getStatus());
		// Get the Todos
		System.out.println(service.path("rest").path("todos").accept(
				MediaType.TEXT_XML).get(String.class));
		// Get XML for application
		System.out.println(service.path("rest").path("todos").accept(
				MediaType.APPLICATION_JSON).get(String.class));
		// Get JSON for application
		System.out.println(service.path("rest").path("todos").accept(
				MediaType.APPLICATION_XML).get(String.class));

		// Get the  Todo with id 1
		System.out.println(service.path("rest").path("todos/1").accept(
				MediaType.APPLICATION_XML).get(String.class));
		// get Todo with id 1
		//service.path("rest").path("todos/8").delete();
		// Get the all todos, id 1 should be deleted
		System.out.println(service.path("rest").path("todos").accept(
				MediaType.APPLICATION_XML).get(String.class));

*/

                for (i = 0; i < 100000; i++) {
		// Create a Todo
		Form form = new Form();


                /////////////////////////////
                //Creating an empty XML Document

                //We need a Document
                DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
                Document doc = docBuilder.newDocument();

                ////////////////////////
                //Creating the XML tree

                //create the root element and add it to the document
                Element root = doc.createElement("root");
                doc.appendChild(root);

                Element child0 = doc.createElement("tempo");
                root.appendChild(child0);
                //add a text element to the child
                Text text0 = doc.createTextNode("" + System.currentTimeMillis());
                child0.appendChild(text0);

                //create child element, add an attribute, and add to root
                Element child = doc.createElement("caller_id");
                root.appendChild(child);
                //add a text element to the child
                Text text = doc.createTextNode(dados[i].getCaller_id());
                child.appendChild(text);

                Element child1 = doc.createElement("duration");

                root.appendChild(child1);
                //add a text element to the child
                Text text1 = doc.createTextNode("" + dados[i].getDuration());
                child1.appendChild(text1);

                Element child2 = doc.createElement("billsec");
                root.appendChild(child2);
                //add a text element to the child
                Text text2 = doc.createTextNode("" + dados[i].getBillsec());
                child2.appendChild(text2);

                Element child3 = doc.createElement("progresssec");
                root.appendChild(child3);
                //add a text element to the child
                Text text3 = doc.createTextNode("" + dados[i].getProgressec());
                child3.appendChild(text3);

                Element child4 = doc.createElement("progress_mediasec");
                root.appendChild(child4);
                //add a text element to the child
                Text text4 = doc.createTextNode("" + dados[i].getProgress_mediasec());
                child4.appendChild(text4);

                Element child5 = doc.createElement("flow_billsec");
                root.appendChild(child5);
                //add a text element to the child
                Text text5 = doc.createTextNode("" + dados[i].getFlow_billsec());
                child5.appendChild(text5);

                Element child6 = doc.createElement("mduration");
                root.appendChild(child6);
                //add a text element to the child
                Text text6 = doc.createTextNode("" + dados[i].getMduration());
                child6.appendChild(text6);

                Element child7 = doc.createElement("billmsec");
                root.appendChild(child7);
                //add a text element to the child
                Text text7 = doc.createTextNode("" + dados[i].getBillmsec());
                child7.appendChild(text7);


                Element child8 = doc.createElement("progressmsec");
                root.appendChild(child8);
                //add a text element to the child
                Text text8 = doc.createTextNode("" + dados[i].getProgressmsec());
                child8.appendChild(text8);

                Element child9 = doc.createElement("progress_mediamsec");
                root.appendChild(child9);
                //add a text element to the child
                Text text9 = doc.createTextNode("" + dados[i].getProgress_mediamsec());
                child9.appendChild(text9);

                Element child10 = doc.createElement("flow_billmsec");
                root.appendChild(child10);
                //add a text element to the child
                Text text10 = doc.createTextNode("" + dados[i].getFlow_billmsec());
                child10.appendChild(text10);

                Element child11 = doc.createElement("uduration");
                root.appendChild(child11);
                //add a text element to the child
                Text text11 = doc.createTextNode("" + dados[i].getUduration());
                child11.appendChild(text11);

                TransformerFactory transfac = TransformerFactory.newInstance();
                Transformer trans = transfac.newTransformer();
                trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                trans.setOutputProperty(OutputKeys.INDENT, "yes");

                //create string from xml tree
                StringWriter sw = new StringWriter();
                StreamResult result = new StreamResult(sw);
                DOMSource source = new DOMSource(doc);
                trans.transform(source, result);
                String xmlString = sw.toString();
                System.out.println(xmlString);

		form.add("id", i);
		//form.add("summary", sw);
                //ClientResponse response = service.path("rest").path("infos").path(dados[i].getCaller_id()).accept(MediaType.APPLICATION_XML).put(ClientResponse.class, dados[i]);
                form.add("summary",doc);

		response = service.path("rest").path("infos").type(MediaType.APPLICATION_XML)
								   .post(ClientResponse.class, xmlString);
		System.out.println("Form response " + response.getEntity(String.class));
		// Get the all todos, id 4 should be deleted
		System.out.println(service.path("rest").path("infos").accept(
				MediaType.APPLICATION_XML).get(String.class));
            }

	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/de.vogella.jersey.todo").build();
	}
}
