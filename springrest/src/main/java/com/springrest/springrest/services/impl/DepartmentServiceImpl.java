 package com.springrest.springrest.services.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.dao.DepartmentDao;
import com.springrest.springrest.dto.DepartmentDto;
//import com.springrest.springrest.dto.DepartmentDto;
import com.springrest.springrest.entity.Department;
import com.springrest.springrest.services.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentDao departmentDao;
	
	
	@Override
	public List<Department> getDepartments() {
		return departmentDao.findAll();
	}

	@Override
	public Department getDepartment(Long departmentId)  {
		Optional<Department> department =
				departmentDao.findById(departmentId);

		return  department.get();
	}

	@Override
	public Department addDepartment(DepartmentDto departmentDto) {
		Department department = new Department();
		department.setDepartmentName(departmentDto.getDepartmentName());
		department.setDepartmentAddress(departmentDto.getDepartmentAddress());
		department.setDepartmentCode(departmentDto.getDepartmentCode());
	
		return departmentDao.save(department);
		
//		return departmentDao.save(department);
	}


	@Override
	public void deleteDepartment(Long departmentId) {
		departmentDao.deleteById(departmentId);
	}

	@Override
	public Department updateDepartment(Long departmentId, DepartmentDto departmentDto) {
		Department depDB = departmentDao.findById(departmentId).get();
		

		if(Objects.nonNull(departmentDto.getDepartmentName()) &&
				!"".equalsIgnoreCase(departmentDto.getDepartmentName())) {
			depDB.setDepartmentName(departmentDto.getDepartmentName());
		}

		if(Objects.nonNull(departmentDto.getDepartmentCode()) &&
				!"".equalsIgnoreCase(departmentDto.getDepartmentCode())) {
			depDB.setDepartmentCode(departmentDto.getDepartmentCode());
		}

		if(Objects.nonNull(departmentDto.getDepartmentAddress()) &&
				!"".equalsIgnoreCase(departmentDto.getDepartmentAddress())) {
			depDB.setDepartmentAddress(departmentDto.getDepartmentAddress());
		}

		return departmentDao.save(depDB);
		
	}

	@Override
	public Department fetchDepartmentByName(String departmentName) {
		return departmentDao.findByDepartmentNameIgnoreCase(departmentName);
	}
}
