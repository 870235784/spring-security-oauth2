package com.tca.security.resource.controller;

import com.tca.beans.ErrorCode;
import com.tca.beans.ReturnBaseResultBean;
import com.tca.utils.WebBaseUtils;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhoua
 * @Date 2020/5/26
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('sys:product:list')")
    public ReturnBaseResultBean<String> list() {
        ReturnBaseResultBean<String> result = new ReturnBaseResultBean<>();
        List<String> list = Lists.newArrayList("双肩包", "笔袋", "铅笔");
        result.setRows(list);
        result.setTotal(list.size());
        WebBaseUtils.setReturnBaseMessage(result, ErrorCode.S0000);
        return result;
    }
}
