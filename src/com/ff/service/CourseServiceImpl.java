package com.ff.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ff.dao.CourseDAO;
import com.ff.model.CourseResultVO;
import com.ff.model.School;
import com.ff.model.SearchResultVO;
import com.ff.model.SearchVO;
import com.ff.model.UnlockCourseVO;
import com.ff.service.resp.SchoolResposeList;

public class CourseServiceImpl implements CourseService{
	
	Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Context ServletContext context;
	
	
	private CourseDAO courseDAO;
	

	public CourseDAO getCourseDAO() {
		return courseDAO;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	@Override
	public Response getCountrySchools(int countryId) {
		SchoolResposeList schoolResponseList = new SchoolResposeList();
		try{
			List<School> schoolList = courseDAO.getAllCountrySchool(countryId);
			
			
			schoolResponseList.setSchoolList(schoolList);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return Response.ok(schoolResponseList).build();
		
	}

	@Override
	public Response searchCourses(SearchVO searchVO) {
		SearchResultVO responseData = null ;
		logger.debug("in searchCourse method.");
		try {
			responseData = courseDAO.searchCourses(searchVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.ok(responseData).build();
		 
	}

	@Override
	public Response unlockCourse(UnlockCourseVO unlockCourseVO) {
		CourseResultVO courseResultVO = null;
		try {
			courseResultVO = courseDAO.unlockCourse(unlockCourseVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok(courseResultVO).build();
	}	

	
	
	
	
	
	
}