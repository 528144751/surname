package com.surname.service.impl.action;

import com.surname.service.ActionResultService;
import org.springframework.stereotype.Component;

@Component("action3")
public class ActionResultThreeServiceImpl implements ActionResultService {
    @Override
    public String getActionResult(String action) {
        return "Result3";
    }
}
