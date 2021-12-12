package boilerplate.java15.domain.employee;

import org.seasar.doma.Domain;

import java.util.Objects;

@Domain(valueType = Integer.class, factoryMethod = "of")
public class Age {
  private final Integer value;

  public static Age of(Integer age) {
    return new Age(age);
  }

  public Age plusOne() {
    return of(value + 1);
  }

  private Age(Integer age) {
    this.value = age;
  }

  Integer getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Age age = (Age) o;
    return value.equals(age.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
