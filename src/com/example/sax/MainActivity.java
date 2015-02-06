package com.example.sax;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private PersonAdapter personAdapter;
	private ArrayList<Person> personsArrayList;
	private ListView mlListView;
	private Button btnSax;
	private Button btnPull;
	private Button btnDom;
	private Context mContext;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        btnSax.setOnClickListener(this);
        btnPull.setOnClickListener(this);
        btnDom.setOnClickListener(this);
    }

    private void initView()
    {
    	btnSax =(Button)findViewById(R.id.buttonSax);
    	btnPull = (Button)findViewById(R.id.buttonPull);
    	btnDom = (Button)findViewById(R.id.buttonDom);
    	mlListView = (ListView) findViewById(R.id.listView1);
    }
    
    public static List<Person> sax_XML() throws Exception{
    	InputStream inputStream = MainActivity.class.getClassLoader().getResourceAsStream("person.xml");
    	if(inputStream!=null)
    	{
    		Log.d("inputStream", inputStream+"");
    		
    	}
    	else {
    		Log.d("inputStream", inputStream+"为空");
		}
    	SAXforHandler saXforHandler = new SAXforHandler();
    	SAXParserFactory spf = SAXParserFactory.newInstance();
    	SAXParser saxParser = spf.newSAXParser();
    	saxParser.parse(inputStream, saXforHandler);
    	List<Person> list = saXforHandler.getPersons();
    	inputStream.close();
    	return list;
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSax:
			  try {
					Toast.makeText(mContext, "Sax解析", Toast.LENGTH_LONG).show();
		        	personsArrayList = (ArrayList<Person>) sax_XML();
		        	 personAdapter = new PersonAdapter(personsArrayList, mContext," Sax ");
		        	 mlListView.setAdapter(personAdapter);
				} catch (Exception e) {
					e.printStackTrace();
				}
			break;
	   case R.id.buttonPull:
			try {
				Log.d("click", "clcikPull");
				Toast.makeText(mContext, "Pull解析", Toast.LENGTH_LONG).show();
				personsArrayList =  (ArrayList<Person>) PullXML.pullXML();
				Log.d("click", personsArrayList.size()+"");
				 personAdapter = new PersonAdapter(personsArrayList, mContext," Pull ");
	        	 mlListView.setAdapter(personAdapter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		break;
	   case R.id.buttonDom:
		   	Toast.makeText(mContext, "Dom解析", Toast.LENGTH_LONG).show();
		   	try {
				personsArrayList = (ArrayList<Person>) DomXML.domXML();
				personAdapter = new PersonAdapter(personsArrayList, mContext, " DOM ");
				mlListView.setAdapter(personAdapter);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		   break;

		}
	}
}
