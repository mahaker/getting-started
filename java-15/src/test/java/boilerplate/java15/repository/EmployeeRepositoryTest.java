package boilerplate.java15.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import boilerplate.java15.TestEnvironment;
import boilerplate.java15.domain.employee.Age;
import boilerplate.java15.domain.employee.EmployeeId;
import boilerplate.java15.domain.employee.Name;
import boilerplate.java15.entity.Employee;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seasar.doma.jdbc.Config;

@ExtendWith(TestEnvironment.class)
class EmployeeRepositoryTest {

  private final EmployeeRepository repository;

  EmployeeRepositoryTest(Config config) {
    Objects.requireNonNull(config);
    this.repository = new EmployeeRepository(config);
  }

  @Test
  void selectAll() {
    List<Employee> employees = repository.selectAll();
    assertEquals(3, employees.size());
  }

  @Test
  void selectById() {
    Employee employee = repository.selectById(EmployeeId.of(1));
    assertNotNull(employee);
    assertEquals(Name.of("ALLEN"), employee.name);
    assertEquals(Age.of(30), employee.age);
    assertEquals(0, employee.version);
  }

  @Test
  void insert() {
    Employee employee = new Employee();
    employee.name = Name.of("ABC");
    employee.age = Age.of(25);
    repository.insert(employee);
    Employee employee2 = repository.selectById(employee.id);
    assertNotNull(employee2);
    assertNotNull(employee2.id);
    assertNotNull(employee2.version);
  }

  @Test
  void update() {
    Employee employee = repository.selectById(EmployeeId.of(1));
    employee.name = Name.of("ABC");
    repository.update(employee);
    Employee employee2 = repository.selectById(EmployeeId.of(1));
    assertEquals(Name.of("ABC"), employee2.name);
  }

  @Test
  void delete() {
    Employee employee = repository.selectById(EmployeeId.of(1));
    repository.delete(employee);
    List<Employee> employees = repository.selectAll();
    assertEquals(2, employees.size());
  }
}
