package com.popjak.personalBlog.repository;

import com.popjak.personalBlog.entity.*;
import org.springframework.data.mongodb.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface BlogRepository extends MongoRepository<Blog,String> {
    Blog getBlogByTitle(String title);
    boolean existsBlogByTitle(String title);
}
