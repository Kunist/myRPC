package com.fanchen.myrpcV2.service.Impl;

import com.fanchen.myrpcV2.common.Blog;
import com.fanchen.myrpcV2.service.BlogService;

public class BlogServiceImpl implements BlogService {
    @Override
    public Blog getBlogById(Integer id) {
        Blog blog = Blog.builder().id(id).title("你的博客").userId(22).build();
        System.out.println("客户端查询了 id 为 " + id + "的博客");
        return blog;
    }
}
