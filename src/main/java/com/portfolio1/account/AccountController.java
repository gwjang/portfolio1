package com.portfolio1.account;

import com.portfolio1.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;

    // 로그인하기
    @RequestMapping("/account/login.json")
    public String login(HttpServletRequest request, Model model) {

        // 파라메터를 가져온다.
        HashMap<Object, Object> parameters = getAllParameters(request);

        Object result = accountService.login(parameters);

        // 모델에 객체를 추가한다.
        model.addAttribute("data",  result);

        // 뷰이름을 반환한다.
        return "jsonView";
    }
}
