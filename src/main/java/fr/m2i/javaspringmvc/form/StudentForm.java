
package fr.m2i.javaspringmvc.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StudentForm {

    @Min(value = 0, message = "Enter a valid age")
    @NotNull(message = "The field age is mandatory")
    private Integer age;

    @NotBlank(message = "The field name is mandatory")
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
