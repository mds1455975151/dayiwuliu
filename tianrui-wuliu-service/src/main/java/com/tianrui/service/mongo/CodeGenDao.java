package com.tianrui.service.mongo;

import com.tianrui.service.bean.CodeGen;
/**
  * @ClassName: CodeGenDao 
  * @Description: 编码生成
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月15日 上午9:07:58 
  *
 */
public interface CodeGenDao extends BaseDao<CodeGen,String> {

	public String codeGen(int type);
}
