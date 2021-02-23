package com.highland.dorothy;

import com.highland.dorothy.tool.ExcelUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DorothyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DorothyApplication.class, args);
		List<List<Object>> companyInfo = new ArrayList();
		try {
			companyInfo = ExcelUtil.readExcel("C:\\Users\\Administrator\\Desktop\\2021年用户偏差电量统计调整.xlsx","基础数据");
		} catch (IOException e) {
			e.printStackTrace();
		}
		int rowSize = companyInfo.get(0).size();
		companyInfo.stream().forEach(r->{
			int i = rowSize - r.size();
			while(i > 0){
				i--;
				r.add("");
			}
		});
		System.out.println(companyInfo.stream());
	}

}
