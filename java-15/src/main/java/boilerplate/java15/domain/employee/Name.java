package boilerplate.java15.domain.employee;

import org.seasar.doma.Domain;

import java.util.Objects;

@Domain(valueType = String.class, factoryMethod = "of")
public class Name {
  private final String value;

  public static Name of(String name) {
    return new Name(name);
  }

  private Name(String value) {
    this.value = value;
  }

  String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Name name = (Name) o;
    return value.equals(name.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
