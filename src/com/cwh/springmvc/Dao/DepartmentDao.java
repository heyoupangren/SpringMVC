package com.cwh.springmvc.Dao;

import com.cwh.springmvc.entites.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cwh
 * CreateTime 2019/7/18 16:55
 */
@Repository
public class DepartmentDao {

    private static Map<Integer, Department> departments =null;

    static {
        departments = new HashMap<Integer, Department>();

    }

    public Collection<Department> getDepartments(){
        return departments.values();
    }

    public Department getDepartment(Integer id){
        return departments.get(id);
    }
}
