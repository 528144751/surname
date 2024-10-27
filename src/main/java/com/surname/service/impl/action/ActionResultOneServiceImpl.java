package com.surname.service.impl.action;

import com.surname.service.ActionResultService;
import org.springframework.stereotype.Component;


@Component("action1")
public class ActionResultOneServiceImpl implements ActionResultService {
    @Override
    public String getActionResult(String action) {
        return "Result1";
    }
}
