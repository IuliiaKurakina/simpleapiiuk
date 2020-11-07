package ru.mtuci.simpleapiiuk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Deposit extends AbstractBaseEntity { //?
    @NotNull
    private Integer interest_rate;
    @NotBlank
    private Date closing_date;
    @NotBlank
    private Boolean prolongation;


//    @Override
//    public String toString() {
//    interest_rate, term_of_deposit, prolongation @NotNull
//    return serial + " : '" + name + " " + surname + " [" + number + "] (" + phone + ')';
//deposit_id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
//    interest_rate   INTEGER NOT NULL,
//    term_of_deposit INTEGER NOT NULL,
//    prolongation    BOOLEAN NOT NULL
}
