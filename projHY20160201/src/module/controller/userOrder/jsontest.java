package module.controller.userOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jsontest {
	public static void main(String[] args)
    {
        List a=new ArrayList();
        Map b=new HashMap();//每個商品
        List c=new ArrayList();//商品所有細項
        List<Map<String,Double>> sizeList=new ArrayList<Map<String,Double>>();//商品size price,第二第三層屬性
        Map<String,Double> sizeMap=new HashMap<String,Double>();
        List<Map<String,List<String>>> classList=new ArrayList<Map<String,List<String>>>();
        
        Map<String,List<String>> classMap1=new HashMap<String,List<String>>();
        List<String> classMapList1=new ArrayList<String>();//冰度
        classMapList1.add("正常全冰");
        classMapList1.add("少冰");
        classMapList1.add("去冰");
        List<String> classMapList2=new ArrayList<String>();//甜度
        classMapList2.add("正常全糖");
        classMapList2.add("少糖");
        classMapList2.add("無糖");
        List<String> classMapList3=new ArrayList<String>();//加料
        classMapList3.add("加珍珠");
        classMapList3.add("加布丁");
        classMap1.put("冰度", classMapList1);
        classMap1.put("甜度", classMapList2);
        classMap1.put("加料", classMapList3);
        classList.add(classMap1);
        
        sizeMap.put("大", 30.0);
        sizeMap.put("中", 25.0);
        sizeMap.put("小", 20.0);
        sizeList.add(sizeMap);
    }
}
