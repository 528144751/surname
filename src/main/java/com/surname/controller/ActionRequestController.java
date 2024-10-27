package com.surname.controller;

import com.surname.responseResult.ResponseResult;
import com.surname.enumEntity.ResultStatus;
import com.surname.factory.ActionRequestFactory;
import com.surname.service.ActionResultService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户规则类
 */
@Slf4j
@RestController
@RequestMapping("/surname/swagger")
public class ActionRequestController {

    @Autowired
    private ActionRequestFactory actionRequestFactory;

    /**
     * 根据参数获取不同返回值
     * @param action    用户规则参数
     * @return
     */
    @ApiOperation(value = "根据用户规则获取对应返回结果", notes = "根据不同的参数(action1,action2,action3,action4)执行不同的service逻辑的接口")
    @GetMapping("/action")
    public ResponseResult getActionRequest(@RequestParam("action") String action){
        //通过工厂获取规则对应的实现类
        ActionResultService actionResultService = actionRequestFactory.getActionRequest(action);
        //如果为空，就返回错误信息
        if (null == actionResultService){
            return ResponseResult.error(ResultStatus.ACTION_REQUEST_NULL.getCode(),ResultStatus.ACTION_REQUEST_NULL.getMessage());
        }
        //通过参数执行对应的实现类
        String actionRequest = actionResultService.getActionResult(action);
        return ResponseResult.success(actionRequest);
    }

}
