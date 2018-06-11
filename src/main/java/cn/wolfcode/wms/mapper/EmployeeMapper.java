package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.query.EmployeeQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee entity);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee entity);

    int query4Count(EmployeeQueryObject qo);

    List<Employee> query4List(EmployeeQueryObject qo);

    //维护与permission中间表的关系
    void deleteRelation(Long employeeId);

    void insertRelation(@Param("employeeId") Long employeeId, @Param("roleId") Long roleId);

    Employee checkLogin(@Param("username") String username, @Param("password") String password);

    List<String> getExpression(Long id);

    void batchDelete(Long[] ids);

}