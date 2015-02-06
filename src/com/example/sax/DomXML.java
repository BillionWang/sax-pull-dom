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
		//���xml�ļ���������
		InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("person.xml");
		List<Person> persons = new ArrayList<Person>();
		
		//����һ������API��������Դ����API��õ�һ���ܹ���XML�ĵ��в���DOM����Ľ�����- ����Ĳ���
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(is);
		
		//�����ĵ��ĸ�Ԫ��
		Element rootElement = (Element) document.getDocumentElement();
		
		//��ȡһ��Node����
		NodeList nodes = rootElement.getElementsByTagName("person");
		
		//����Node����
		for(int i = 0;i<nodes.getLength();i++)
		{
			Element personElement = (Element) nodes.item(i);
			Person person = new Person();
			person.setId(Integer.valueOf(personElement.getAttribute("id")));;
			
			NodeList childNodes = personElement.getChildNodes();
			for(int j = 0 ;j<childNodes.getLength();j++)
			{
				Node childNode = childNodes.item(j);
				
				//�ж���Note������ΪԪ��Note
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
