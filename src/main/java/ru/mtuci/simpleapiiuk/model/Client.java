package ru.mtuci.simpleapiiuk.model;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends AbstractBaseEntity {
    @NotNull
    private Integer serial;
    @NotBlank
    private String number;
    @NotBlank
    private String surname;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;

    @Override
    public String toString() {
        return serial + " : '" + name + " " + surname + " [" + number + "] (" + phone + ')';
    }
}
