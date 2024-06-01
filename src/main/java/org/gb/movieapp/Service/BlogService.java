package org.gb.movieapp.Service;

import org.gb.movieapp.Model.Request.UpsertBlogRequest;
import org.gb.movieapp.entites.Blogs;
import org.gb.movieapp.entites.User;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Page<Blogs> findByStatus(boolean status, int size);
    Page<Blogs> findAllByStatus(boolean status,int page, int size);

    List<Blogs> findAllDescCreatedDate();

    List<Blogs> findAllByUser_IdOrderByCreatedAtDesc(int id);

    Blogs getById(int id);

    Blogs getByIdAndSlug(String title, int id);

    Blogs createBlog(UpsertBlogRequest request);

    Blogs updateBlog(int id, UpsertBlogRequest request);

    void deleteBlog(int id);

    String uploadThumbnail(int id, MultipartFile file);

    int getBlogCount();

    Map<Integer, List<Blogs>> getBlogCountByMonth(int year);
}
