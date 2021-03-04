package com.highland.dorothy;

import com.highland.dorothy.tool.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DorothyApplication {

	public static void main(String[] args) throws IOException {
		temp();
		//personIncharge();
	}
	public static void temp() throws IOException {
		List<List<Object>> list = new ArrayList();
		String file = "C:\\Users\\Administrator\\Desktop\\零售电量.xlsx";
		list = ExcelUtil.readExcel(file,"sheet1");
		//判断信息区域
		list = list.stream().filter(a->a.size()==8).collect(Collectors.toList());
		List<List<Object>> res = new ArrayList<>();
		int i = 0;
		int j = 1;
		while (i < list.size()){
			if (!(list.get(i).get(0).toString().equals("交易品种名称")&&list.get(i).get(3).toString().equals("输配电价执行方式 (仅水电消纳示范交 易品种）"))){
				s("Wrong!!!!!!!");
				break;
			}
			for (int month = i+9 ;  month <= i+20 ; month++){
				List<Object> temp = new ArrayList();
				temp.add(j);
				j++;
				temp.add(list.get(i+1).get(2));
				temp.add(list.get(month).get(0));
				temp.add(list.get(i).get(1));
				temp.add(list.get(month).get(1));
				temp.add(list.get(month).get(3));
				temp.add(list.get(month).get(5));
				temp.add(list.get(month).get(6));
				res.add(temp);
			}
			i+=22;
		}
		String[] strs2 = {"序号","用户名","月份","交易品种","交易总电量","交易电量","交易电价","浮动交易电价 (仅水电电量部分）"};
		Workbook workbook = ExcelUtil.getWorkbook(file);
		ExcelUtil.write2Sheet(workbook.createSheet("2月"),strs2,res,"YYYY/MM/DD");
		OutputStream out = new FileOutputStream(new File(file));
		workbook.write(out);
		out.close();
		s("stop");
	}
	public static void personIncharge() throws IOException {
		List<List<Object>> companyInfo = new ArrayList();
		List<List<Object>> companyNoInfo = new ArrayList();
		List<List<Object>> personinfo = new ArrayList();
		List<List<Object>> personDistribution = new ArrayList<>();
		String companyInformationFilePath = "F:\\hnch\\2021诚汇\\2021年用户偏差电量统计调整.xlsx";
		String personInformationFilePath = "F:\\hnch\\2021诚汇\\2021电量查询分配表.xlsx";
//		String companyInformationFilePath = "C:\\Users\\Administrator\\Desktop\\2021年用户偏差电量统计调整.xlsx";
//		String personInformationFilePath = "C:\\Users\\Administrator\\Desktop\\2021电量查询分配表.xlsx";
		//ExcelUtil.writeCell(companyInformationFilePath,"基础数据","A104","66666666");F
		try {
			companyInfo = ExcelUtil.readExcel(companyInformationFilePath,"基础数据");
			companyNoInfo = ExcelUtil.readExcel(companyInformationFilePath,"火电常规");
			personinfo = ExcelUtil.readExcel(personInformationFilePath,"登录账号");
			personDistribution = ExcelUtil.readExcel(personInformationFilePath,"1月");
		} catch (IOException e) {
			e.printStackTrace();
		}

		HashSet<String> allocatedCompanyNo = new HashSet();
		HashSet<String> allocatedCompanyName = new HashSet();
		personDistribution.stream().forEach(a->{
			if (allocatedCompanyNo.contains(a.get(3).toString()))  s("重复分配户号:"+a.get(3).toString());
			allocatedCompanyNo.add(a.get(3).toString());
			allocatedCompanyName.add(a.get(2).toString());

		});
		companyInfo = companyInfo.stream().filter(l->l.get(2)!=null && !allocatedCompanyName.contains(l.get(2)) && l.get(9)!=null && l.get(11)!=null && !l.get(9).toString().equals("低谷弃水") && l.get(11).toString().equals("2021")).collect(Collectors.toList());
		List<String> companyInfoName = companyInfo.stream().map(a->a.get(2).toString()).collect(Collectors.toList());
		companyNoInfo = companyNoInfo.stream().filter(l->companyInfoName.contains(l.get(1).toString()) && l.get(2) != null).collect(Collectors.toList());
		personinfo.remove(0);
		personinfo = personinfo.stream().filter(l->l.get(5).toString().equals("固定")).collect(Collectors.toList());
		long distributed ;
		String personName = personinfo.get(0).get(1).toString();
		distributed = personDistribution.stream().filter(a -> a.get(10).toString().equals(personName)).count();
		for (List l : companyNoInfo){
			String  companyName= l.get(1).toString();
			if (distributed >= 5 && personinfo.size() == 0){
				s(companyName + "未分配");
				continue;
			}
			String personName2 =  personinfo.get(0).get(1).toString();
			while(distributed >= 5 && personinfo.size() > 0) {
				personinfo.remove(0);
				personName2 = personinfo.get(0).get(1).toString();
				String personName3 = personName2;
				distributed = personDistribution.stream().filter(a -> a.get(10).toString().equals(personName3)).count();
			}
			String personName4 = personName2;
			personDistribution.add(new ArrayList(){{
				add(l.get(0));
				add(l.get(5));
				add(companyName);
				add(l.get(2));
				add(l.get(3));
				add(l.get(4));
				add(null);
				add(null);
				add(null);
				add(null);
				add(personName4);
			}});
			distributed++;
		}
		/***********排序***************/
		personinfo = ExcelUtil.readExcel(personInformationFilePath,"登录账号");
		companyNoInfo = ExcelUtil.readExcel(companyInformationFilePath,"火电常规");
		personinfo.remove(0);
		companyNoInfo = companyNoInfo.stream().filter(l->l.get(2) != null).collect(Collectors.toList());
		for (int i = 0 ; i < personinfo.size() ; i++){
			personinfo.get(i).add(i);
		}
		for (int i = 0 ; i < companyNoInfo.size() ; i++){
			companyNoInfo.get(i).add(i);
		}
		List<List<Object>> finalPersoninfo = personinfo;
		List head = personDistribution.get(0);
		personDistribution.remove(0);
		List<List<Object>> finalCompanyNoInfo = companyNoInfo;
		personDistribution = personDistribution.stream().sorted((x, y)->{
			String personName_x = x.get(10).toString();
			String personName_y = y.get(10).toString();
			String companyName_x = x.get(3).toString();
			String companyName_y = y.get(3).toString();
			int personNo_x = (Integer)finalPersoninfo.stream().filter(l->l.get(1).toString().equals(personName_x)).findFirst().get().get(7);
			int personNo_y = (Integer)finalPersoninfo.stream().filter(l->l.get(1).toString().equals(personName_y)).findFirst().get().get(7);
			if (personNo_x > personNo_y) return 1;
			if (personNo_x == personNo_y) {
				s(companyName_x);
				int companyNo_x = (Integer) finalCompanyNoInfo.stream().filter(l->l.get(2).toString().equals(companyName_x)).findFirst().get().get(19);
				int companyNo_y = (Integer) finalCompanyNoInfo.stream().filter(l->l.get(2).toString().equals(companyName_y)).findFirst().get().get(19);
				if (companyNo_x > companyNo_y) return 1;
				return -1;
			}
			return -1;
		}).collect(Collectors.toList());
		/***********排序***************/
		String[] strs2 = new String[head.size()];
		for(int i=0;i<head.size();i++){
			if (head.get(i) == null) strs2[i] = "";
			else strs2[i]=head.get(i).toString();
		}
		Workbook workbook = ExcelUtil.getWorkbook(personInformationFilePath);
		ExcelUtil.write2Sheet(workbook.createSheet("2月"),strs2,personDistribution,"YYYY/MM/DD");
		OutputStream out = new FileOutputStream(new File(personInformationFilePath));
		workbook.write(out);
		out.close();
	}

	public static void s(Object o){
		System.out.println(o);
	}

}
