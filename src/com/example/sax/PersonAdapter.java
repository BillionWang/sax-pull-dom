package com.example.sax;

import java.util.ArrayList;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter{
	private ArrayList<Person> arrayList;
	private LayoutInflater mInflater;
	private String mType;
	public PersonAdapter(ArrayList<Person> arrayList, Context mContext,String type
			) {
		super();
		this.arrayList = arrayList;
		this.mInflater = LayoutInflater.from(mContext);
		this.mType = type;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if(arrayList!=null)
		{
			return arrayList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.d("getView", "getView");
		viewHolder holder = null;
		if(convertView == null)
		{
			holder = new viewHolder();
			convertView = mInflater.inflate(R.layout.listitem, null, false);
			holder.tvid = (TextView)convertView.findViewById(R.id.tvid);
			holder.tvname = (TextView)convertView.findViewById(R.id.tvname);
			holder.tvage = (TextView)convertView.findViewById(R.id.tvage);
			convertView.setTag(holder);
		}
		else {
			holder = (viewHolder) convertView.getTag();
		}
		holder.tvage.setText("");
		holder.tvid.setText("");
		holder.tvname.setText("");
		holder.tvid.setText(arrayList.get(position).getId()+mType);
		holder.tvage.setText(arrayList.get(position).getAge()+"");
		holder.tvname.setText(arrayList.get(position).getName()+"");
		return convertView;
	}
	
	class viewHolder
	{
		private TextView tvid;
		private TextView tvname;
		private TextView tvage;
	}
	
}
