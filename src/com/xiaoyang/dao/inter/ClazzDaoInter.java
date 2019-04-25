package com.xiaoyang.dao.inter;

import java.util.List;

import com.xiaoyang.bean.Clazz;
import com.xiaoyang.bean.Page;

/**
 * 班级数据层接口
 * @author xiaoyang
 *
 */
public interface ClazzDaoInter extends BaseDaoInter {
	
	/**
	 * 获取班级详细信息
	 * @param gradeid 年级ID
	 * @param page 分页参数
	 * @return
	 */
	public List<Clazz> getClazzDetailList(String gradeid, Page page);
	
}
