package com.liuyihui.platform.service;

import com.liuyihui.platform.entity.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	
	public Course getCourseById(int id){
		Course course=new Course();
		course.setCourseId(id);
		return course;
	}
}
