package org.blue.helper.StringHelper.controller;

import org.blue.helper.StringHelper.controller.support.AddUserFriendApplyDTO;
import org.blue.helper.StringHelper.service.UserFriendService;
import org.blue.helper.StringHelper.utils.ResultUtil;
import org.blue.helper.core.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/friend/")
public class UserFriendController {
    private static final Logger logger=LoggerFactory.getLogger(UserFriendController.class);

    @Autowired
    private UserFriendService userFriendService;

    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("addNewFriend")
    @ResponseBody
    public Map<String,Object> addNewFriend(@RequestBody AddUserFriendApplyDTO applyDTO, HttpServletRequest request){


        userFriendService.applyAddFriend(applyDTO);
        return ResultUtil.commonSuccess();
    }
}
