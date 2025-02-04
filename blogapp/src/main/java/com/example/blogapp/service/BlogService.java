package com.example.blogapp.service;

import com.example.blogapp.model.Blog;
import com.google.firebase.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
public class BlogService {
    @Autowired
    private FirebaseDatabase firebaseDatabase;

    public String saveBlog(Blog blog) {
        DatabaseReference ref = firebaseDatabase.getReference("blogs").push();
        ref.setValueAsync(blog);
        return ref.getKey();
    }

    public List<Blog> getAllBlogs() throws InterruptedException {
        DatabaseReference ref = firebaseDatabase.getReference("blogs");
        System.out.println("Database Reference: " + ref);
        List<Blog> blogs = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("DataSnapshot: " + dataSnapshot);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Blog blog = snapshot.getValue(Blog.class);
                    System.out.println("Blog: " + blog);
                    blogs.add(blog);
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.err.println("DatabaseError: " + databaseError.getMessage());
                latch.countDown();
            }
        });

        latch.await();
        return blogs;
    }

    public Blog getBlogById(String id) throws InterruptedException {
        DatabaseReference ref = firebaseDatabase.getReference("blogs").child(id);
        CountDownLatch latch = new CountDownLatch(1);
        final Blog[] blog = new Blog[1];

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                blog[0] = dataSnapshot.getValue(Blog.class);
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                latch.countDown();
            }
        });

        latch.await();
        return blog[0];
    }

    public void deleteBlog(String id) {
        DatabaseReference ref = firebaseDatabase.getReference("blogs").child(id);
        ref.removeValueAsync();
    }
}