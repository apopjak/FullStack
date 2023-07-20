package com.popjak.personalBlog.controller;

import com.popjak.personalBlog.dao.*;
import com.popjak.personalBlog.entity.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class BlogController {

    private final BlogDAO blogDAO;

    public BlogController(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }


    @GetMapping("/")
    public String index(Model model) {
        String title = "Home";
        String homeContent = "Lacus vel facilisis volutpat est velit egestas dui id ornare. Semper auctor neque vitae tempus quam. Sit amet cursus sit amet dictum sit amet justo. Viverra tellus in hac habitasse. Imperdiet proin fermentum leo vel orci porta. Donec ultrices tincidunt arcu non sodales neque sodales ut. Mattis molestie a iaculis at erat pellentesque adipiscing. Magnis dis parturient montes nascetur ridiculus mus mauris vitae ultricies. Adipiscing elit ut aliquam purus sit amet luctus venenatis lectus. Ultrices vitae auctor eu augue ut lectus arcu bibendum at. Odio euismod lacinia at quis risus sed vulputate odio ut. Cursus mattis molestie a iaculis at erat pellentesque adipiscing.";
        List<Blog> blogs = blogDAO.getAllBlogs();

        model.addAttribute("title", title);
        model.addAttribute("content", homeContent);
        model.addAttribute("blogs", blogs);

        return "index";
    }

    @GetMapping("/about")
    private String about(Model model) {
        String title = "About";
        String aboutContent = "Welcome to my website! I'm a passionate learner and aspiring full stack developer, currently focusing on honing my skills in HTML, CSS, and JavaScript for the frontend. Additionally, I am diving into Spring Boot with Java and Thymeleaf for backend development. With a strong foundation in these technologies, I am dedicated to crafting seamless and intuitive web experiences. Join me on this exciting journey as I explore the world of full stack development and strive to create innovative and user-friendly solutions.";
        model.addAttribute("title", title);
        model.addAttribute("content", aboutContent);

        return "index";
    }

    @GetMapping("/contact")
    private String contact(Model model) {
        String title = "Contact";
        String contactContent = "If you have any questions or need assistance, please feel free to reach out to me via email at apopjak@gmail.com. I'm here to help and look forward to hearing from you!";
        model.addAttribute("title", title);
        model.addAttribute("content", contactContent);

        return "index";
    }

    @GetMapping("/compose")
    private String compose() {
        return "compose";
    }

    @PostMapping("/compose")
    public String composePost(@RequestParam("newTitle") String newTitle,
                              @RequestParam("newBlogContent") String newBlogContent,
                              Model model) {

        // Obtain title and body from user, create a new blog entity and save it into DB
        Blog blog = new Blog(newTitle,newBlogContent);

        if (blogDAO.existsInDb(blog.getTitle())) {
            model.addAttribute("errorBlogExists",true);
            return "compose";
        }

        if (newTitle.isEmpty() || newBlogContent.isEmpty()) {
            model.addAttribute("errorEmptyTitle",true);
            return "compose";
        }

        blogDAO.saveBlog(blog);
        return "redirect:/";
    }


    @GetMapping("/post/{key}")
    public String blogPostMapper(@PathVariable("key") String pathVariableKey,
                                 Model model) {

        List<Blog> blogs = blogDAO.getAllBlogs();

        Optional<Blog> blogPost = blogs.stream()
                .filter(blog -> blog.getTitle().equalsIgnoreCase(pathVariableKey))
                .findFirst();

        if (blogPost.isPresent()) {
            model.addAttribute("blogPost", blogPost);
            return "post";
        } else {
            return "error"; // Return an error page or handle the error as per your requirement
        }
    }

    @GetMapping("/post/delete/{title}")
    public String removePost(@PathVariable("title") String title) {

        Blog blog = blogDAO.getBlogByTitle(title);
        blogDAO.deleteBlog(blog);

        return "redirect:/";
    }

    @GetMapping("/post/update/{title}")
    public String updatePost(@PathVariable("title") String title,
                             Model model) {

        Blog blog = blogDAO.getBlogByTitle(title);

        model.addAttribute("blog",blog);
        return "update-post";
    }

    @PostMapping("/post/update")
    public String updateBlog(@RequestParam("blogTitle") String blogTitle,
                                 @RequestParam("newBlogContent") String newBlogContent,
                                 @RequestParam("newTitle") String newTitle,
                                 Model model) {
        // Get old blog
        Blog oldBlog = blogDAO.getBlogByTitle(blogTitle);

        if (blogDAO.existsInDb(newTitle) & !blogTitle.equalsIgnoreCase(newTitle)) {
            model.addAttribute("errorBlogExists",true);
            model.addAttribute("blog", oldBlog);
            return "update-post";
        }
        if (newTitle.isEmpty() || newBlogContent.isEmpty()) {
            model.addAttribute("errorEmptyTitle",true);
            return "compose";
        }
        Blog newBlog = new Blog(newTitle,newBlogContent);
        blogDAO.deleteBlog(oldBlog);
        blogDAO.saveBlog(newBlog);
        return "redirect:/";
    }
}
