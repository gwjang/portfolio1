package com.portfolio1.account;

import com.portfolio1.base.BaseDao;
import com.portfolio1.base.BaseService;
import com.portfolio1.base.CommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

@Service
public class AccountService extends BaseService {

    @Autowired
    private CommonDao dao;

    @Override
    protected BaseDao getDao() {
        return this.dao;
    }

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> redisHash;

    public Object login(HashMap<Object, Object> parameters) {

        HashMap<Object, Object> loginUser = (HashMap<Object, Object>) select("Login", parameters);

        if ("login".equals(loginUser.get("loginResult"))){
            putLoginUserInSession(loginUser);
            // 메세지에 대한 작업해야함
            // 성공 메시지 리턴
            return "성공";
        }

        //실패 메시지 리턴
        return "실패";
    }

    private void putLoginUserInSession(HashMap<Object, Object> loginUser) {
        redisHash.put("User", "id", (String) loginUser.get("id"));
        redisHash.put("User", "name", (String) loginUser.get("name"));
        redisHash.put("User", "email", (String) loginUser.get("email"));
    }
}
