package com.xiaoyang.dao.inter;

import java.util.List;
import java.util.Map;

import com.xiaoyang.bean.Exam;


/**
 * 操作成绩的数据层接口
 * @author xiaoyang
 *
 */
public interface ScoreDaoInter extends BaseDaoInter {
	
	/**
	 * 获取学生成绩表
	 * @param exam
	 * @param
	 * @return
	 */
	List<Map<String, Object>> getScoreList(Exam exam);
	
}
