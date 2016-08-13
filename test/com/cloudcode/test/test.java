package com.cloudcode.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import net.sf.json.JSONArray;

import com.cloudcode.futures.model.SharingPlans;
import com.cloudcode.futures.utils.SinaStock;

public class test {
public static void main(String[] args) {
	
        JSONArray jsArr2 = new JSONArray();
String s="s";
s="22";
        String r="[[\"9965.000\",\"9974.451\",\"926\",\"84954\",\"09:00\"],[\"9960.000\",\"9971.639\",\"762\",\"84844\",\"09:01\"]]";
       r=r.replace("],[", "]&[").replace("[[", "[").replace("]]", "]");
       System.out.println(r);
        String[] strings =r.split("&");
        try {
			org.json.JSONArray js=new org.json.JSONArray(strings);
			for (int i = 0; i < js.length(); i++) {
				//org.json.JSONObject jo= (JSONObject) js.get(i);
				System.out.println(js);
			}
			System.out.println(js);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        net.sf.json.JSONArray j =  (net.sf.json.JSONArray) net.sf.json.JSONSerializer.toJSON(r);
        
        System.out.print(j.size()+"-------------");
        
	Date d = null;
	try {
		d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2015-03-23 09:00:00");
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(d);
	
	String futuresType="L1505";
	SinaStock sinaStock = new SinaStock();
//	ArrayList<SharingPlans>  lists 
	JSONArray jsArr =  sinaStock.getFiveJSONArray(futuresType);
//	ArrayList<SharingPlans>  sh= new ArrayList<SharingPlans>();
//	for(SharingPlans sharingPlans:lists){
//		if(sharingPlans.getMax()){sh.add(sharingPlans);
//			System.out.println(sharingPlans.getTimesX()+" Max: "+sharingPlans.getTimes()+" ********* "+sharingPlans.getPrice());
//		}
//		if(sharingPlans.getMin()){sh.add(sharingPlans);
//			System.out.println(sharingPlans.getTimesX()+" Min: "+sharingPlans.getTimes()+" ********* "+sharingPlans.getPrice());
//		}
//		
//	}
//	System.out.println(sh.size());
//	for(int i=0;i<sh.size();i++){		
//		if(i+1 != sh.size()){
//		LineSlope lineSlope = new LineSlope(sh.get(i), sh.get(i+1));
//		sh.get(i).setSlope(lineSlope.getSlope());
//		System.out.println(lineSlope.getSlope());
//		}
//	}
	getFiveSharingPlansList(jsArr);
	System.out.println(jsArr);
	/*String url="http://stock2.finance.sina.com.cn/futures/api/json.php/IndexService.getInnerFuturesMinLine?symbol=L1505&?_=1";
	HttpManager hm = new HttpManager();
	try {
		ArrayList  list = hm.getResponseInJsonArray(url, null,ArrayList.class, false);
		 JSONArray jsArr = JSONArray.fromObject(list);  
		 ArrayList<SharingPlans> sharingPlansList =new ArrayList<SharingPlans>();
		 Map<String,SharingPlans> map = new HashMap<String, SharingPlans>();
		 for (int i = 0; i < jsArr.size(); i++) {
			   String str= jsArr.getString(i);
			   str=str.replace("[", "").replace("]", "").replaceAll("\"", "");
			   String[] s = str.split(",");
			  // for(String st:s){
				   SharingPlans sharingPlans = new SharingPlans();
				   sharingPlans.setPrice(Double.parseDouble(s[0]));
				   sharingPlans.setAvgPrice(Double.parseDouble(s[1]));
				   sharingPlans.setVolume(Double.parseDouble(s[2]));
				   sharingPlans.setPositions(Double.parseDouble(s[3]));
				   sharingPlans.setTimes(s[4]);
				   sharingPlansList.add(sharingPlans);
				   map.put(s[4], sharingPlans);
//				   System.out.println(s[4]);
//				   System.out.println(sharingPlans);
//				   System.out.println(map);
			 //  }
			   //System.out.println(str);
			}
		 System.out.println("***********************************************");
		 System.out.println(map.get("14:00"));
		 System.out.println("***********************************************");
		//System.out.println(a);
	} catch (FuturesException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
}
	
public static ArrayList<SharingPlans> getFiveSharingPlansList(JSONArray jsArr) {
	ArrayList<SharingPlans> sharingPlansList = new ArrayList<SharingPlans>();
	Map<String, SharingPlans> map = new LinkedHashMap<String, SharingPlans>();
	for (int i = 0; i < jsArr.size(); i++) {
		String str = jsArr.getString(i);
		str = str.replace("[", "").replace("]", "").replaceAll("\"", "");
		String[] s = str.split(",");
		SharingPlans sharingPlans = getFiveSharingPlans(jsArr, i);
//		calculateMaxMin(sharingPlans, getSharingPlans(jsArr, i + 1),
//				getSharingPlans(jsArr, i - 1));
//		setPrevNext(sharingPlans, jsArr, i);
//		sharingPlansList.add(sharingPlans);
//		map.put(s[4], sharingPlans);
	}
	System.out.println("***********************************************");
	System.out.println(map.get("14:00"));
	System.out.println("***********************************************");
	return sharingPlansList;
}
private static String date="";
public static SharingPlans getFiveSharingPlans(JSONArray jsArr, int i) {
	if (i == jsArr.size() || i == -1) {
		return null;
	}
	String str = jsArr.getString(i);
	str = str.replace("[", "").replace("]", "").replaceAll("\"", "");
	String[] s = str.split(",");
	if(s.length == 7){
		date = s[6];
	}
	SharingPlans sharingPlans = new SharingPlans();
	sharingPlans.setPrice(Double.parseDouble(s[0]));
	sharingPlans.setAvgPrice(Double.parseDouble(s[1]));
	sharingPlans.setVolume(Double.parseDouble(s[2]));
	sharingPlans.setPositions(Double.parseDouble(s[3]));
	sharingPlans.setTimes(s[4]);
	sharingPlans.setTimesX(i);
	sharingPlans.setDate(date);
	// setPrevNext(sharingPlans, jsArr, i);
	Date d = null;
	try {
		d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(date +" "+s[4]);
		sharingPlans.setMs(d.getTime());
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return sharingPlans;
}
}
