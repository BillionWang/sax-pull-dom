package com.example.sax;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;




public class DomXML {
	public static List<Person> domXML() throws Throwable
	{
		//获得xml文件的输入流
		InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("person.xml");
		List<Person> persons = new ArrayList<Person>();
		
		//定义一个工厂API，程序可以从这个API里得到一个能够从XML文档中产生DOM对象的解析器- 翻译的不好
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);
		
		//返回文档的根元素
		Element rootElement = (Element) document.getDocumentElement();
		
		//获取一个Node集合
		NodeList nodes = rootElement.getElementsByTagName("person");
		
		//遍历Node集合
		for(int i = 0;i<nodes.getLength();i++)
		{
			Element personElement = (Element) nodes.item(i);
			Person person = new Person();
			person.setId(Integer.valueOf(personElement.getAttribute("id")));;
			
			NodeList childNodes = personElement.getChildNodes();
			for(int j = 0 ;j<childNodes.getLength();j++)
			{
				Node childNode = childNodes.item(j);
				
				//判断子Note的类型为元素Note
				if(childNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Element childElement  = (Element) childNode;
				
					if("name".equals(childElement.getNodeName()))
					{
						person.setName(childElement.getFirstChild().getNodeValue());
					}
					else if ("age".equals(childElement.getNodeName())) {
						person.setAge(Integer.valueOf(childElement.getFirstChild().getNodeValue()));
					}
			  }
		  }
					Log.e("log", person.toString());
					persons.add(person);
	  }
					return persons;
	}
}
