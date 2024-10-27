package com.surname.service.impl.action;

import com.surname.service.ActionResultService;
import org.springframework.stereotype.Component;

@Component("action4")
public class ActionResultFourServiceImpl implements ActionResultService {
    @Override
    public String getActionResult(String action) {
        return "Result4";
    }
}
