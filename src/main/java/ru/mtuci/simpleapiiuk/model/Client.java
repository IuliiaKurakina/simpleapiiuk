package ru.mtuci.simpleapiiuk.model;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.EqualsAndHashCode;
        import lombok.NoArgsConstructor;
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
}
