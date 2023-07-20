package com.popjak.personalBlog.dao;

import com.popjak.personalBlog.entity.*;
import com.popjak.personalBlog.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BlogDAOImpl implements BlogDAO{

    private final BlogRepository repository;

    public BlogDAOImpl(BlogRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveBlog(Blog blog) {
        repository.save(blog);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return repository.findAll();
    }

    @Override
    public Blog getBlogByTitle(String title) {
        return repository.getBlogByTitle(title);
    }

    @Override
    public void updateBlog(Blog blog) {
        repository.save(blog);
    }

    @Override
    public void deleteBlog(Blog blog) {
        repository.delete(blog);
    }

    @Override
    public boolean existsInDb(String title) {
        return repository.existsBlogByTitle(title);
    }
}
