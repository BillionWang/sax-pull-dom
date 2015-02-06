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
		//获取person文件的输入流
		InputStream is = PullXML.class.getClassLoader().getResourceAsStream("person.xml");
		//用来存放解析的person对象
		List<Person> persons = null;
		//一个标记
		boolean flag = false;
		Person person = null;
		
		//实例化一个XmlPullParser对象
		XmlPullParser parser = Xml.newPullParser();
		
		//设置输入流的编码
		parser.setInput(is,"UTF-8");
		
		//设置第一个事件，从这个事件开始解析文档
		int eventCode = parser.getEventType();
		
		//设定结束标记，如果是END_DOCUEMNT,解析就结束了
		 while (eventCode != XmlPullParser.END_DOCUMENT) {  
	            switch(eventCode){  
	            case XmlPullParser.START_DOCUMENT:{  
	                //开始解析的时候我们一般做一些初始化的操作  
	                persons = new ArrayList<Person>();  
	                break;  
	            }  
	            case XmlPullParser.START_TAG:{  
	                //判断当前的元素是否是需要检索的元素  
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
	            //这一步很重要，该方法返回一个事件码，也是触发下一个事件的方法  
	            eventCode = parser.next();  
	        }  
		return persons;
	}
}
