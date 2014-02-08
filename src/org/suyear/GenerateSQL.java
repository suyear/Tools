package org.suyear;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class GenerateSQL {
	public static void main(String[] args) {
		
	}
	
	
	public static void generateSQL(String fileDir)throws BiffException, IOException{
		InputStream is=new FileInputStream(new File(fileDir));
		Workbook wb=Workbook.getWorkbook(is);
		Sheet sheet=wb.getSheet(0);
		
		String idtemp="";
		for (int i = 0; i < sheet.getRows(); i++) {
			Cell first=sheet.getCell(0,i);
			Cell second=sheet.getCell(1,i);
			Cell third=sheet.getCell(2,i);
			Cell fourth=sheet.getCell(3,i);
			String id=first.getContents();
			
			if(id.equals("商家ID")){
				continue;
			}
			if(id==null || id==""){
				id=idtemp;
			}else{
				idtemp=id;
			}
			String content=second.getContents();
			if(content==null || content==""){
				continue;
			}
			String score_1=third.getContents();
			String score_2=fourth.getContents();
			
			System.out.println("INSERT INTO jxm_comment(shop_id,content,score_1,score_2) VALUES("+id+",'"+content.trim()+"','"+score_1+"','"+score_2+"');");
		}
		
		System.out.println("\n-- 修改用户ID\n" +
				"UPDATE jxm_comment SET member_id=(SELECT id FROM jxm_member WHERE id < 12000 ORDER BY RAND() LIMIT 1 ) WHERE member_id IS NULL;");
		
		System.out.println("-- 修改用户名称\n" +
				"UPDATE jxm_comment jc SET jc.member_name=(SELECT member_name FROM jxm_member jm WHERE jm.id=jc.member_id);");
		
		System.out.println("-- 修改头像\n" +
				"UPDATE jxm_comment jc SET jc.head_img=(SELECT head_img FROM jxm_member jm WHERE jm.id=jc.member_id);");
		
		System.out.println("-- 修改发布日期\n" +
				"UPDATE jxm_comment jc SET jc.create_date=(SELECT ADDTIME(create_date,'1000000') AS create_time FROM jxm_member jm WHERE jm.id=jc.member_id) WHERE create_date IS NULL;\n");
		
		System.out.println("UPDATE jxm_shangjia_extend jse SET jse.score_1=(SELECT SUBSTRING(AVG(score_1),1,3) FROM jxm_comment WHERE shop_id=jse.member_id);");
		
		System.out.println("UPDATE jxm_shangjia_extend jse SET jse.score_2=(SELECT SUBSTRING(AVG(score_2),1,3) FROM jxm_comment WHERE shop_id=jse.member_id);");
		
		System.out.println("UPDATE jxm_shangjia_extend SET score_1=0 WHERE score_1 IS NULL;");
		
		System.out.println("UPDATE jxm_shangjia_extend SET score_2=0 WHERE score_2 IS NULL;");
	}
}
