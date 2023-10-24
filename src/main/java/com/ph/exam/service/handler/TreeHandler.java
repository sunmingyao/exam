package com.ph.exam.service.handler;


import com.ph.exam.entity.BaseTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树状结构处理器
 * @author : sunmingyao
 */
public interface TreeHandler {

    /**
     * 返回基础列表
     *
     * @return java.util.List<? extends com.haier.qms.hce.measurement.entity.base.BaseTree>
     * @author SunMingyao
     */
    List<? extends BaseTree> getTreeList();


    /**
     * 创建树状结构
     *
     * @param list 列表
     * @param code 顶级编码
     * @return java.util.List<com.haier.qms.hce.measurement.entity.base.BaseTree>
     * @author SunMingyao
     */
    default List<BaseTree> getTree(List<? extends BaseTree> list, String code) {
        List<BaseTree> result = new ArrayList<>();
        list.forEach(x -> {
            if (x.getParentCode().equalsIgnoreCase(code)) {
                x.setChildList(getTree(list, x.getCode()));
                result.add(x);
            }
        });
        return result;
    }

}
