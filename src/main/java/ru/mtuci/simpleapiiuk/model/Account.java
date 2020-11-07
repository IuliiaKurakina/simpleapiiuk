package ru.mtuci.simpleapiiuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Account extends AbstractBaseEntity { //?
    //@NotNull
    private Date opening_date;
    //@NotNull
    private Integer deposit_amount;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "client_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

//    @Override
//    public String toString() {
//        return serial + " : '" + name + " " + surname + " [" + number + "] (" + phone + ')';
// client_id, deposit_id, opening_date, deposit_amount   }
// accounts_id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
//    client_id   INTEGER REFERENCES  client (id),
//    deposit_id INTEGER   REFERENCES deposit (deposit_id),
//    date_closing DATE NOT NULL,
//    amount INTEGER NOT NULL
}
