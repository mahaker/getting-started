package boilerplate.java15.domain.employee;

import org.seasar.doma.Domain;

import java.util.Objects;

@Domain(valueType = Integer.class, factoryMethod = "of")
public class EmployeeId {
  private final Integer value;

  public static EmployeeId of(Integer employeeId) {
    return new EmployeeId(employeeId);
  }

  private EmployeeId(Integer value) {
    this.value = value;
  }

  Integer getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    EmployeeId that = (EmployeeId) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
