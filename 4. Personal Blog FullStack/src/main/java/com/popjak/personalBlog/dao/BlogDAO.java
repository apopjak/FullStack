package com.popjak.personalBlog.dao;

import com.popjak.personalBlog.entity.*;

import java.util.*;

public interface BlogDAO {

    // INSERT
    void saveBlog(Blog blog);
    // READ
    List<Blog> getAllBlogs();
    Blog getBlogByTitle(String title);
    // UPDATE
    void updateBlog(Blog blog);

    // DELETE
    void deleteBlog(Blog blog);

    //Exists
    boolean existsInDb(String title);
}
