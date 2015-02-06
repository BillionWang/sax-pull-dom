package com.example.sax;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import android.util.Log;

public class SAXforHandler extends DefaultHandler{

	/**
	 * This is log.d TAG
	 */
	private static final String TAG = "SAXTag";
	
	/**
	 * Store persons
	 * 存储人数组
	 */
	private List<Person> persons ;
	
	/**
	 * a Person
	 */
	private Person person;
	
	/**
	 * recode the name of current tag
	 */
	private String tag;
	
	
	/**
	 * When startDocument,you can initial something in this method
	 * 当文档开始时，会调用这个方法
	 */
	@Override
	public void startDocument() throws SAXException {
		persons = new ArrayList<Person>();
		Log.d(TAG, "the sax is starting");
		
	}

	/**
	 * invoke the method after endDocument
	 * 文档结束时会调用这个方法
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		Log.d(TAG, "endDocument");
	}

	/**
	 * invoke when startElement
	 * 开始读取元素时候调用这个方法
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if("person".equals(localName))
		{
			for(int i = 0;i<attributes.getLength();i++)
			{
				Log.d(TAG, "attributes.getLength"+ attributes.getLength());
				Log.d(TAG, "attributesName: " + attributes.getLocalName(i)+"_attributesValue"+attributes.getValue(i));
				person = new Person();
				person.setId(Integer.valueOf(attributes.getValue(i)));
			}
		}
		
		tag = localName;
		Log.i(TAG, "localName = " + localName);  
	}

	/**
	 * read the element content
	 * 读取元素里面的内容。
	 * 比如说读到age,age的内容是21.这个方法就是来读21的
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String data = new String(ch,start,length).trim();
		if(!"".equals(data))
		{
			Log.i(TAG, "Content: " +data);
		}
		if("name".equals(tag))
		{
			person.setName(data);
		}
		else if ("age".equals(tag)) {
			person.setAge(Integer.valueOf(data));
		}
	}
	
	/**
	 * 一个元素结束时候调用
	 * 就是遇到反斜杠时候调用
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if("person".equals(localName)&&person!=null)
		{
			persons.add(person);
			person = null;
		}
		tag = null;
	}

	public List<Person> getPersons()
	{
		return persons;
	}

}
