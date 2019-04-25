package com.xiaoyang.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.LinkedList;
import java.util.List;

import com.xiaoyang.bean.Clazz;
import com.xiaoyang.bean.Grade;
import com.xiaoyang.bean.Student;
import com.xiaoyang.dao.inter.StudentDaoInter;
import com.xiaoyang.tools.MysqlTool;
import org.apache.commons.beanutils.BeanUtils;

public class StudentDaoImpl extends BaseDaoImpl implements StudentDaoInter {

	public List<Student> getStudentList(String sql, List<Object> param) {
		//数据集合
		List<Student> list = new LinkedList<>();
		try {
			//获取数据库连接
			Connection conn = MysqlTool.getConnection();
			//预编译
			PreparedStatement ps = conn.prepareStatement(sql);
			//设置参数
			if(param != null && param.size() > 0){
				for(int i = 0;i < param.size();i++){
					ps.setObject(i+1, param.get(i));
				}
			}
			//执行sql语句
			ResultSet rs = ps.executeQuery();
			//获取元数据
			ResultSetMetaData meta = rs.getMetaData();
			//遍历结果集
			while(rs.next()){
				//创建对象
				Student stu = new Student();
				//遍历每个字段
				for(int i=1;i <= meta.getColumnCount();i++){
					String field = meta.getColumnName(i);
					BeanUtils.setProperty(stu, field, rs.getObject(field));
				}
				//查询班级
				Clazz clazz = (Clazz) getObject(Clazz.class, "SELECT * FROM clazz WHERE id=?", new Object[]{stu.getClazzid()});
				//查询年级
				Grade grade = (Grade) getObject(Grade.class, "SELECT * FROM grade WHERE id=?", new Object[]{stu.getGradeid()});
				//添加
				stu.setClazz(clazz);
				stu.setGrade(grade);
				//添加到集合
				list.add(stu);
			}
			//关闭连接
			MysqlTool.closeConnection();
			MysqlTool.close(ps);
			MysqlTool.close(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
