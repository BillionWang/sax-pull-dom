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
	 * �洢������
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
	 * ���ĵ���ʼʱ��������������
	 */
	@Override
	public void startDocument() throws SAXException {
		persons = new ArrayList<Person>();
		Log.d(TAG, "the sax is starting");
		
	}

	/**
	 * invoke the method after endDocument
	 * �ĵ�����ʱ������������
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		Log.d(TAG, "endDocument");
	}

	/**
	 * invoke when startElement
	 * ��ʼ��ȡԪ��ʱ������������
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
	 * ��ȡԪ����������ݡ�
	 * ����˵����age,age��������21.���������������21��
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
	 * һ��Ԫ�ؽ���ʱ�����
	 * ����������б��ʱ�����
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
