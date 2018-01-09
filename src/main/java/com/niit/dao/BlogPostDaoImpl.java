package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Notifications;
@Repository
public class BlogPostDaoImpl implements BlogPostDao{
	
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public void saveBlogPost(BlogPost blogPost) {
		
		 Session session=sessionFactory.openSession();
		 Transaction tx=session.beginTransaction();
		 session.saveOrUpdate(blogPost);
		 tx.commit();
		 
		 return; 
		}
	/*
   
	@Transactional
	public List<BlogPost> getBlogs(int approved) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BlogPost where approved=" + approved);
		return query.list();
	}

	@Transactional
	public BlogPost getBlogById(int id) {
		Session session = sessionFactory.getCurrentSession();
		BlogPost blogPost = (BlogPost) session.get(BlogPost.class, id);
		return blogPost;
	}

	@Transactional
	public void updateBlogPost(BlogPost blogPost, String rejectionReason){
		
		Session session = sessionFactory.getCurrentSession();
		Notifications notification = new Notifications();
		notification.setBlogTitle(blogPost.getBlogTitle());
		notification.setUsername(blogPost.getPostedBy().getUsername());// author
																		// who
																		// posted
																		// the
																		// blog
		if (blogPost.isApproved()) {// true admin approves the blogpost [Approve
									// radio button is selected by admin]
			session.update(blogPost);// update blogpost set approved=1 where
										// id=?
			notification.setApprovalStatus("Approved");
			session.save(notification);// insert into notification values (...)
		} else {// false admin rejects the blogpost [Reject radio button is
				// selected by admin]
			System.out.println(rejectionReason);
			if (rejectionReason.equals(""))
				notification.setRejectionReason("Not Mentioned by Admin");
			else
				notification.setRejectionReason(rejectionReason);
			notification.setApprovalStatus("Rejected");
			session.save(notification);// insert into notification values (...)
			session.delete(blogPost);// delete from blogpost where id=?

		}
		
		
	}

	@Transactional
	public void addComment(BlogComment blogComment) {
		 Session session=sessionFactory.getCurrentSession();
	        session.save(blogComment);//insert into BlogComment
		
	}
*/
}
