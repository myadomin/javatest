package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.Dict;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.util.Result;
import com.example.demo.util.Tools;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableAutoConfiguration
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    // http://localhost:8888/user/id/1
    @RequestMapping(value="/id/{id}", method=RequestMethod.GET)
    public Result getUserById(@PathVariable long id) throws Exception {
        User user = userService.getUserById(id);
        Result res = new Result();
        res.putData("user", user);
        return res.ok();
    }

    // http://localhost:8888/user/list?sex=female
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public List<User> getUserListBySex(@RequestParam(value="sex",defaultValue="male") String sex) throws Exception {
        List<User> userList = userService.getUserListBySex(sex);
        return userList;
    }

    // http://localhost:8888/user/listJson
    @RequestMapping(value="/listJson", method=RequestMethod.POST)
    public Result getUserListJsonBySex(@RequestBody JSONObject jsonParam) throws Exception {
        List<User> userList = userService.getUserListBySex(jsonParam.getString("sex"));
        Result res = new Result();
        res.putData("userList", userList);
        return res.ok();
    }

    // http://localhost:8888/user/map
    @RequestMapping(value="/map", method=RequestMethod.POST)
    public Result map() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("1", "11");
        map.put("2", "22");
        Result res = new Result();
        res.putData("map", map);
        return res.ok();
    }

    // http://localhost:8888/user/list
    @RequestMapping(value="/list", method=RequestMethod.POST)
    public Result list() throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("key1", "One");
        map1.put("key2", "Two");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("key1", "Three");
        map2.put("key2", "Four");
        list.add(map1);
        list.add(map2);
        Result res = new Result();
        res.putData("list", list);
        return res.ok();
    }

    // http://localhost:8888/user/register
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(
        @Valid @RequestBody User user,
        BindingResult bindingResult
    ) {
        Result res = new Result();
        if(bindingResult.hasErrors()) {
            String error = Tools.getValidateError(bindingResult);
            return res.fail(error);
        }
        if (userService.hasMobileInUserList(user.getMobile())) {
            return res.fail(Dict.mobileExists);
        }
        if (userService.insert(user) > 0) {
            return res.ok();
        } else {
            return res.fail(Dict.dbInsertError);
        }
    }

}
