package com.example.expandablelistview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

	ExpandableListView expand_listview;
	List<String> groupList;
	List<String> childList;
	Map<String, List<String>> laptopCollection;
	ArrayList<Childclass> list;
	Map<String, List<Childclass>> lap;
	static int size;
	View my_view;
	private int lastExpandedPosition = -1;
    MyAdapter adapter;
    ArrayList<CountClass> mylist;
    Map<Integer,TextView> map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        map=new LinkedHashMap<Integer, TextView>();
		expand_listview = (ExpandableListView) findViewById(R.id.expand_listview);
		createGroupList();
		createCollection();
		createCollectionn();
	    adapter = new MyAdapter(MainActivity.this, groupList, lap);
		expand_listview.setAdapter(adapter);
	    expand_listview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
          @Override
          public void onGroupExpand(int groupPosition) {
              if (lastExpandedPosition != -1
                      && groupPosition != lastExpandedPosition) {

                  expand_listview.collapseGroup(lastExpandedPosition);
              }
              lastExpandedPosition = groupPosition;
          }
      });


	}

	private void createGroupList() {
		groupList = new ArrayList<String>();
        mylist=new ArrayList<CountClass>();
		groupList.add("HP");
		groupList.add("Dell");
		groupList.add("Lenovo");
		groupList.add("Sony");
		groupList.add("HCL");
		groupList.add("Samsung");
        for(int i=0;i<groupList.size();i++)
        {
            CountClass cs=new CountClass(0);
            mylist.add(cs);
        }
	}

	private void createCollection() {
		// preparing laptops collection(child)
		String[] hpModels = { "HP Pavilion G6-2014TX", "ProBook HP 4540",
				"HP Envy 4-1025TX" };
		String[] hclModels = { "HCL S2101", "HCL L2102", "HCL V2002" };
		String[] lenovoModels = { "IdeaPad Z Series", "Essential G Series",
				"ThinkPad X Series", "Ideapad Z Series" };
		String[] sonyModels = { "VAIO E Series", "VAIO Z Series",
				"VAIO S Series", "VAIO YB Series" };
		String[] dellModels = { "Inspiron", "Vostro", "XPS" };
		String[] samsungModels = { "NP Series", "Series 5", "SF Series" };

		laptopCollection = new LinkedHashMap<String, List<String>>();

		for (String laptop : groupList) {
			if (laptop.equals("HP")) {
				loadChild(hpModels);
			} else if (laptop.equals("Dell"))
				loadChild(dellModels);
			else if (laptop.equals("Sony"))
				loadChild(sonyModels);
			else if (laptop.equals("HCL"))
				loadChild(hclModels);
			else if (laptop.equals("Samsung"))
				loadChild(samsungModels);
			else
				loadChild(lenovoModels);

			laptopCollection.put(laptop, childList);
		}
	}

	private void createCollectionn() {
		// preparing laptops collection(child)
		String[] hpModels = { "HP Pavilion G6-2014TX", "ProBook HP 4540",
				"HP Envy 4-1025TX" };
		String[] hclModels = { "HCL S2101", "HCL L2102", "HCL V2002" };
		String[] lenovoModels = { "IdeaPad Z Series", "Essential G Series",
				"ThinkPad X Series", "Ideapad Z Series" };
		String[] sonyModels = { "VAIO E Series", "VAIO Z Series",
				"VAIO S Series", "VAIO YB Series" };
		String[] dellModels = { "Inspiron", "Vostro", "XPS" };
		String[] samsungModels = { "NP Series", "Series 5", "SF Series" };
		lap = new LinkedHashMap<String, List<Childclass>>();
		for (String laptop : groupList) {
			if (laptop.equals("HP")) {
				LoadChild(hpModels);
			} else if (laptop.equals("Dell"))
				LoadChild(dellModels);
			else if (laptop.equals("Sony"))
				LoadChild(sonyModels);
			else if (laptop.equals("HCL"))
				LoadChild(hclModels);
			else if (laptop.equals("Samsung"))
				LoadChild(samsungModels);
			else
				LoadChild(lenovoModels);
			lap.put(laptop, list);
		}
        System.out.println("mapp"+lap);
	}

	private void loadChild(String[] laptopModels) {
		childList = new ArrayList<String>();
		for (String model : laptopModels)
			childList.add(model);
	}

	private void LoadChild(String[] laptopmodels) {
		list = new ArrayList<Childclass>();
		for (String model : laptopmodels) {
			Childclass classs = new Childclass(model, 0 + "", 0);
			list.add(classs);
		}
        System.out.println(list.toString());
	}

	private class MyAdapter extends BaseExpandableListAdapter {
		Context cnt;
		List<String> laptops;
		Map<String, List<Childclass>> laptopcollections;
		LayoutInflater inf;
		TextView  tv_name;
		CheckBox ch_box;
        TextView tv_header, tv_count;

		public MyAdapter(Context cnt, List<String> laptops,
				Map<String, List<Childclass>> laptopcollections) {
			this.cnt = cnt;
			this.laptops = laptops;
			this.laptopcollections = laptopcollections;
			inf = LayoutInflater.from(cnt);
		}

		@Override
		public int getGroupCount() {
			return laptops.size();
		}

		@Override
		public int getChildrenCount(int i) {

			return laptopcollections.get(laptops.get(i)).size();
		}

		@Override
		public Object getGroup(int i) {
			return laptops.get(i);
		}

		@Override
		public Object getChild(int groupposition, int childposition) {

			return laptopcollections.get(laptops.get(groupposition)).get(
					childposition);
		}

		@Override
		public long getGroupId(int groupposition) {
			return groupposition;
		}

		@Override
		public long getChildId(int groupposition, int childposition) {
			return childposition;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getGroupView(int i, boolean b, View view,
				ViewGroup viewGroup) {
			String laptopname = (String) getGroup(i);
			if (view == null) {
				view = inf.inflate(R.layout.group_layout,null);
			}
            tv_header = (TextView) view.findViewById(R.id.tv_header);
            tv_count = (TextView) view.findViewById(R.id.tv_count);
			tv_header.setText(laptopname);
            if(!map.containsKey(i))
            {
                tv_count.setTag(i);
                map.put(i,tv_count);
            }
            if(mylist.get(i).getCount()==0)
            {
                System.out.println("gone");
                tv_count.setVisibility(View.GONE);
            }
            else if(mylist.get(i).getCount()>0)
            {
                System.out.println("visible");
                tv_count.setVisibility(View.VISIBLE);
                tv_count.setText(mylist.get(i).getCount()+"");
            }
			return view;
		}

		@Override
		public View getChildView(final int groupposition, final int childposition,
				boolean b, View view, final ViewGroup viewGroup) {
			Childclass laptopname = (Childclass) getChild(groupposition,
					childposition);
			if (view == null) {
				view = inf.inflate(R.layout.exapandablelistview_row_item, null);
			}
			tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_name.setText(laptopname.getName());
			ch_box = (CheckBox) view.findViewById(R.id.ch_box);
			ch_box.setTag(childposition);
			ch_box.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View vieww) {
                    TextView tv_countt=map.get(groupposition);
					int position = (Integer) vieww.getTag();
					String group_name = (String) getGroup(groupposition);
                    size =mylist.get(groupposition).getCount();
					if (((CheckBox)vieww).isChecked()) {
						laptopcollections.get(group_name).get(position)
								.setState("1");
						size = size + 1;
						tv_countt.setText(size + "");
                        tv_countt.setVisibility(View.VISIBLE);

					} else {
						laptopcollections.get(group_name).get(position)
								.setState("0");
						size = size - 1;
					    if (size == 0) {
								tv_countt.setVisibility(View.GONE);
							} else if (size > 0) {
								tv_countt.setText(size + "");

                           tv_countt.setVisibility(View.VISIBLE);

						}
					}
                    mylist.get(groupposition).setCount(size);
                    System.out.println("data" + size);
					notifyDataSetChanged();
				}
			});
			if (laptopname.getState().equalsIgnoreCase("0")) {
				ch_box.setChecked(false);
			} else if (laptopname.getState().equalsIgnoreCase("1")) {
				ch_box.setChecked(true);
			}
			return view;
		}

		@Override
		public boolean isChildSelectable(int i, int i2) {
			return true;
		}

		@Override
		public boolean areAllItemsEnabled() {
			return true;
		}
        public void removeGroup(int groupposition)
        {
            laptops.remove(groupposition);
            laptopcollections.remove(getGroup(groupposition));
            mylist.remove(groupposition);
            map.remove(map.get(groupposition));
            notifyDataSetChanged();
        }
        public void removeChild(int groupposition,int childposition)
        {
            laptopcollections.get(getGroup(groupposition)).remove(childposition);
            if(mylist.get(groupposition).getCount()>0)
            {
                mylist.get(groupposition).setCount(mylist.get(groupposition).getCount()-1);
            }
            notifyDataSetChanged();
        }

	}

}
