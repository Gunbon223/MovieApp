package org.gb.movieapp.Service.ServiceImplement;

import com.github.slugify.Slugify;
import jakarta.servlet.http.HttpSession;
import org.gb.movieapp.Exception.BadRequestException;
import org.gb.movieapp.Exception.ResourceNotFoundException;
import org.gb.movieapp.Model.Request.UpsertBlogRequest;
import org.gb.movieapp.Repository.BlogRepository;
import org.gb.movieapp.Service.BlogService;
import org.gb.movieapp.Service.CloudinaryService;
import org.gb.movieapp.Utils.RandomColor;
import org.gb.movieapp.entites.Blogs;
import org.gb.movieapp.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImplements implements BlogService {
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    HttpSession session;
    @Autowired
    CloudinaryService cloudinaryService;

    @Override
    public Page<Blogs> findByStatus(boolean status,  int size) {
        PageRequest pageRequest = PageRequest.of(1, size, Sort.by("createdAt").descending());
        return blogRepository.findByStatus(status, pageRequest);
    }

    @Override
    public Page<Blogs> findAllByStatus(boolean status, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page-1, size, Sort.by("createdAt").descending());
        return blogRepository.findByStatus(status, pageRequest);    }

    @Override
    public List<Blogs> findAllDescCreatedDate() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Blogs> findAllByUser_IdOrderByCreatedAtDesc(int id) {
        return blogRepository.findAllByUser_IdOrderByCreatedAtDesc(id);
    }

    @Override
    public Blogs getById(int id) {
        return blogRepository.findById(id);
    }

    @Override
    public Blogs getByIdAndSlug(String slug, int id) {
        return blogRepository.findByIdAndSlug(id, slug);
    }

    @Override
    public Blogs createBlog(UpsertBlogRequest request) {
        Slugify slg = Slugify.builder().build();
        String color = RandomColor.getRandomColor();
        String title = request.getTitle();
        Blogs blogs = new Blogs();
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            throw new BadRequestException("Bạn cần đăng nhập để thực hiện chức năng này");
        }

        blogs.setTitle(request.getTitle());
        blogs.setDescription(request.getDescription());
        blogs.setContent(request.getContent());
        blogs.setSlug(slg.slugify(request.getTitle()));
        blogs.setStatus(request.getIsStatus());
        blogs.setUser(user);
        blogs.setThumbnail("https://placehold.co/600x400/"+color+ "/FFF" + "?text=" + String.valueOf(title.charAt(0)).toUpperCase());
        blogs.setCreatedAt(java.time.LocalDateTime.now());
        blogs.setUpdatedAt(java.time.LocalDateTime.now());

        return blogRepository.save(blogs);
    }

    @Override
    public Blogs updateBlog(int id, UpsertBlogRequest updatedBlog) {
        Blogs blog = blogRepository.findById(id);
        if (blog == null) {
            throw new ResourceNotFoundException("Không tìm thấy blog");
        }
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            throw new BadRequestException("Bạn cần đăng nhập để thực hiện chức năng này");
        }
        if (!blog.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Bạn không thể chỉnh sửa blog của người khác");
        }
        blog.setTitle(updatedBlog.getTitle());
        blog.setDescription(updatedBlog.getDescription());
        blog.setContent(updatedBlog.getContent());
        blog.setStatus(updatedBlog.getIsStatus());
        blog.setUpdatedAt(java.time.LocalDateTime.now());
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(int id) {
        Blogs blog = blogRepository.findById(id);
        if (blog == null) {
            throw new ResourceNotFoundException("Không tìm thấy blog");
        }
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            throw new BadRequestException("Bạn cần đăng nhập để thực hiện chức năng này");
        }
        if (!blog.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Bạn không thể xóa blog của người khác");
        }
        blogRepository.delete(blog);
    }

    @Override
    public String uploadThumbnail(int id, MultipartFile file) {
        Blogs blog = blogRepository.findById(id);
        if (blog == null) {
            throw new ResourceNotFoundException("Không tìm thấy blog");
        }
        try {
            Map data = cloudinaryService.uploadFile(file, "avatar");
            String url = (String) data.get("url");
            blog.setThumbnail(url);
            blogRepository.save(blog);
            return url;

        } catch (Exception e) {
            throw new BadRequestException("Không thể upload ảnh");
        }
    }

}
