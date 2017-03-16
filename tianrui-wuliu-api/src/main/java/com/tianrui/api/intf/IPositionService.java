package com.tianrui.api.intf;

import java.util.List;
import java.util.Map;

import com.tianrui.common.vo.Result;

public interface IPositionService {

	Result update(List<Map<String,String>> req);
}
