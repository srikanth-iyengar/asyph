package io.company.usermicroservice.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.company.usermicroservice.models.Blog;
import io.company.usermicroservice.repository.BlogRepository;
import io.company.usermicroservice.requestmodels.BlogRequest;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;


	public Map<String, String> saveBlog(BlogRequest request) {
		Blog blog = new Blog.Builder()
											.title(request.title)
											.content(request.content)
											.createdBy(request.createdBy)
											.createdAt(LocalDateTime.now())
											.updatedAt(LocalDateTime.now())
											.build();
		blogRepository.save(blog);
		Map<String, String> res = new HashMap<>();
		res.put("status", "OK");
		res.put("message", "Blog Created Successfully");
		return res;
	}

	public Map<String, String> updateBlog(BlogRequest request, String id) {
		Optional<Blog> blogDb = blogRepository.findById(id);
		if(blogDb.isEmpty()) {
			Map<String, String> res = new HashMap<String, String>();
			res.put("status", "FAIL");
			res.put("message", "Blog doesn't exists");
			return res;
		}
		Blog blog = blogDb.get();
		blog.setTitle(request.title);
		blog.setContent(request.content);
		blog.setUpdatedAt(LocalDateTime.now());
		Map<String, String> res = new HashMap<String, String>();
		res.put("status", "OK");
		res.put("message", "Blog Updated Successfully");
		return res;
	}

	public List<Blog> latestBlog(int limit) {
		return blogRepository.findAll(PageRequest.of(0, limit)).toList(); 
	}
}
