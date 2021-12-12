package boilerplate.java15.dao;

import boilerplate.java15.domain.employee.EmployeeId;
import boilerplate.java15.entity.Employee;
import java.util.List;
import java.util.stream.Collector;

import org.seasar.doma.*;

@Dao
public interface EmployeeDao {

  @Sql(
      """
        select
          /*%expand*/*
        from
          employee
        order by
          id
        """)
  @Select(strategy = SelectType.COLLECT)
  <R> R selectAll(Collector<Employee, ?, R> collector);

  @Sql(
      """
        select
          /*%expand*/*
        from
          employee
        where
          id = /* id */0
        """)
  @Select
  Employee selectById(EmployeeId id);

  @Insert
  int insert(Employee employee);

  @Update
  int update(Employee employee);

  @Delete
  int delete(Employee employee);
}
