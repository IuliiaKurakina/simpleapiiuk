package ru.mtuci.simpleapiiuk.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class Account {
    public static final int START_SEQ = 1;

    @Id
    @SequenceGenerator(name = "account_seq", sequenceName = "account_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    private Long id;
    @NotNull
    private Integer number;
    @NotNull
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

    public Account() {
    }

    public Account(Long id, Integer number, Integer amount) {
        this.id = id;
        this.number = number;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Deposit> deposits = new HashSet<>();

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<Deposit> deposits) {
        this.deposits = deposits;
        for (Deposit d : deposits) {
            d.setAccount(this);
        }
    }
}
//@ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "client_account",
//            joinColumns = {@JoinColumn(name = "client_id")},
//            inverseJoinColumns = {@JoinColumn(name = "account_id")})
//    private List<Account> account = new ArrayList<>();