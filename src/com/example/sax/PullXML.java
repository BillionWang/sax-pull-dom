package com.example.sax;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import android.util.Log;
import android.util.Xml;

public class PullXML {
	public static List<Person> pullXML() throws Exception
	{
		//��ȡperson�ļ���������
		InputStream is = PullXML.class.getClassLoader().getResourceAsStream("person.xml");
		//������Ž�����person����
		List<Person> persons = null;
		//һ�����
		boolean flag = false;
		Person person = null;
		
		//ʵ����һ��XmlPullParser����
		XmlPullParser parser = Xml.newPullParser();
		
		//�����������ı���
		parser.setInput(is,"UTF-8");
		
		//���õ�һ���¼���������¼���ʼ�����ĵ�
		int eventCode = parser.getEventType();
		
		//�趨������ǣ������END_DOCUEMNT,�����ͽ�����
		 while (eventCode != XmlPullParser.END_DOCUMENT) {  
	            switch(eventCode){  
	            case XmlPullParser.START_DOCUMENT:{  
	                //��ʼ������ʱ������һ����һЩ��ʼ���Ĳ���  
	                persons = new ArrayList<Person>();  
	                break;  
	            }  
	            case XmlPullParser.START_TAG:{  
	                //�жϵ�ǰ��Ԫ���Ƿ�����Ҫ������Ԫ��  
	                if("person".equals(parser.getName())){  
	                    flag = true;  
	                    person = new Person();  
	                    person.setId(Integer.valueOf(parser.getAttributeValue(0)));  
	                }  
	                if(flag){  
	                    if("name".equals(parser.getName())){  
	                        person.setName(parser.nextText());  
	                    }else if("age".equals(parser.getName())){  
	                        person.setAge(Integer.valueOf(parser.nextText()));  
	                    }  
	                }  
	                break;  
	            }  
	            case XmlPullParser.END_TAG:{  
	                if("person".equals(parser.getName()) && person != null){  
	                    flag = false;  
	                    persons.add(person);  
	                    Log.e("log", person.toString());  
	                    person = null;  
	                }  
	                break;  
	            }  
	            }  
	            //��һ������Ҫ���÷�������һ���¼��룬Ҳ�Ǵ�����һ���¼��ķ���  
	            eventCode = parser.next();  
	        }  
		return persons;
	}
}
