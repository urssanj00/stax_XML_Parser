package com.test.xml.parser;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class STAXParser {

	public static void main(String[] args) {
		STAXParser stp = new STAXParser();
		Map l = stp.getItems();
		//System.out.println(l);
		//stp.init();
		//stp.parse(null);
		
	}
	XMLInputFactory xmlFactory = null;

	public void init(){

		try {
			xmlFactory = XMLInputFactory.newInstance();
			xmlFactory.setProperty( XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,	Boolean.TRUE);
			xmlFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,	Boolean.FALSE);
			//IS_COALESCING property to true
			//gets whole text data as one event
			xmlFactory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.FALSE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void parse(String filename){
		
		
		XMLStreamReader xmlReader = null;
		try {
			Map xmlmap = new HashMap();
			// convert String into InputStream
			InputStream is = new ByteArrayInputStream(xml.getBytes());
			
			int i = 0;
			xmlReader = xmlFactory.createXMLStreamReader(is);
			while (xmlReader.hasNext()) {

				//returns the event type
				 xmlReader.next();

				//now print the element by choosing the respective method
				//returns event type for reference
				int eventType = xmlReader.getEventType();
				System.out.print("Event:" +eventType);

				//returns the text
				try {
					
					if(xmlReader.hasName()){
						if(eventType==1){
							if(i==0){
								String name = xmlReader.getLocalName();
								xmlmap.put(xmlReader.getLocalName(), new HashMap());
								System.out.println("  Local :" +name);
							}					
						}						
					}
					if(xmlReader.hasText()){
						System.out.println("  Text:" +xmlReader.getText());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//use the right methods for printing attributes, names, etc
				i++;
			}
		} /*catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/ catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public Map getItems() {
		
		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
		Map map = new HashMap();

		try {
			
			XMLInputFactory factory = XMLInputFactory.newFactory();
			
			InputStream is = new ByteArrayInputStream(xml.getBytes());

			
			XMLStreamReader parser = factory.createXMLStreamReader(is);
			
			String currentTag = null;
			Map<String, Object> currentRow = null;
			Map<String, Object> currentRowMap = null;

			while (parser.hasNext()) {
				Map currMap = null;
				int currentEvent = parser.next();
				switch (currentEvent) {	
				
					case XMLStreamReader.START_ELEMENT:
						String tmpCurrentTag = currentTag;
						
						currentTag = parser.getLocalName();
						
						System.out.println("<"+currentTag+">");	
						currMap = new HashMap();
						if(tmpCurrentTag==null){
							map.put(currentTag, currMap);
						}
						if(map.get(tmpCurrentTag)==null){
							currMap.put(currentTag, null);
							map.put(tmpCurrentTag, currMap);
						}
						//map.put(currentTag, null);
						break;
					case XMLStreamReader.CHARACTERS:
						if(parser.hasText()){
							System.out.println("\t"+parser.getText());
							if(currMap!=null)
							currMap.put(currentTag, parser.getText());
						}						
						break;
					case XMLStreamReader.END_ELEMENT:						
						currentTag = parser.getLocalName();
						System.out.println("<"+currentTag+"/>");					
						break;
				}
				
			}
			
			
		} catch (Exception e) {
			//logger.error("Erro ao processar XML " + xmlFilePath + ": " + e.getMessage());
			throw new IllegalStateException(e);
		}
		
		return map;
	}
	
	String xml = "<?xml version=\"1.0\"?>"
			+ "<Zoo xmlns=\"http://www.animaldept.gov\">"
				+ "<Lion> "
					+ "<Type>Wild</Type>"
					+ "<Sound>Roar</Sound>"
					+ "<Jungle><Den>Home</Den></Jungle>"

					+ "<!-- it will eat you man -->"
				+ "</Lion>"
			+ "</Zoo>";
}
