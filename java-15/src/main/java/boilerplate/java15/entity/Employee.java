package boilerplate.java15.entity;

import boilerplate.java15.domain.employee.Age;
import boilerplate.java15.domain.employee.EmployeeId;
import boilerplate.java15.domain.employee.Name;
import org.seasar.doma.*;

@Entity(metamodel = @Metamodel)
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @SequenceGenerator(sequence = "EMPLOYEE_SEQ")
  public EmployeeId id;

  public Name name;

  public Age age;

  public void agePlusOne() {
    this.age = this.age.plusOne();
  }

  @Version public Integer version;

  @Override
  public String toString() {
    return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", version=" + version + "]";
  }
}
