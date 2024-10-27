package com.surname.service.impl.action;

import com.surname.service.ActionResultService;
import org.springframework.stereotype.Component;

@Component("action2")
public class ActionResultTwoServiceImpl implements ActionResultService {
    @Override
    public String getActionResult(String action) {
        return "Result2";
    }
}
