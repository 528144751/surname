package com.surname.factory;

import com.surname.service.ActionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂模式代理类
 */
@Service
public class ActionRequestFactory {

    @Autowired
    Map<String, ActionResultService> actionRequestServiceMap = new ConcurrentHashMap<>();

    public ActionResultService getActionRequest(String action){
        //通过map将继承ActionRequestService的实体类注入到map，根据实现类别名获取对应实现类
        return actionRequestServiceMap.get(action);
    }

}
