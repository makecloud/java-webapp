package com.liuyihui.webapp.service;

import org.springframework.stereotype.Service;

import com.liuyihui.webapp.entity.Course;

@Service
public class CourseService {
	
	
	public Course getCourseById(int id){
		Course course=new Course();
		course.setCourseId(id);
		return course;
	}
}
