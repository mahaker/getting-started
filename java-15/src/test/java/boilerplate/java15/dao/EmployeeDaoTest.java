package boilerplate.java15.dao;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import boilerplate.java15.TestEnvironment;
import boilerplate.java15.domain.employee.Age;
import boilerplate.java15.domain.employee.EmployeeId;
import boilerplate.java15.domain.employee.Name;
import boilerplate.java15.entity.Employee;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
class EmployeeDaoTest {

  private final EmployeeDao dao;

  EmployeeDaoTest(Config config) {
    Objects.requireNonNull(config);
    dao = new EmployeeDaoImpl(config);
  }

  @Test
  void selectAll() {
    var employees = dao.selectAll(toList());
    assertEquals(3, employees.size());
  }

  @Test
  void selectAll2() {
    final Map<EmployeeId, Name> employeeNames
      = dao.selectAll(toMap(employee -> employee.id, employee -> employee.name));

    assertEquals(3, employeeNames.size());
    assertEquals(Name.of("ALLEN"), employeeNames.get(EmployeeId.of(1)));
    assertEquals(Name.of("WARD"), employeeNames.get(EmployeeId.of(2)));
    assertEquals(Name.of("JONES"), employeeNames.get(EmployeeId.of(3)));
  }

  @Test
  public void selectById() {
    var employee = dao.selectById(EmployeeId.of(1));
    assertNotNull(employee);
    assertEquals(Name.of("ALLEN"), employee.name);
    assertEquals(Age.of(30), employee.age);
    assertEquals(0, employee.version);
  }

  @Test
  void insert() {
    var employee = new Employee();
    employee.name = Name.of("ABC");
    employee.age = Age.of(25);
    dao.insert(employee);
    var employee2 = dao.selectById(employee.id);
    assertNotNull(employee2);
    assertNotNull(employee2.id);
    assertNotNull(employee2.version);
  }

  @Test
  void update() {
    var employee = dao.selectById(EmployeeId.of(1));
    employee.name = Name.of("ABC");
    dao.update(employee);
    var employee2 = dao.selectById(EmployeeId.of(1));
    assertEquals(Name.of("ABC"), employee2.name);
  }

  @Test
  void delete() {
    var employee = dao.selectById(EmployeeId.of(1));
    dao.delete(employee);
    var employees = dao.selectAll(toList());
    assertEquals(2, employees.size());
  }
}
