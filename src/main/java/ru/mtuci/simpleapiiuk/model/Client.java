package ru.mtuci.simpleapiiuk.model;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "clients")
public class Client {
    public static final int START_SEQ = 1;

    @Id
    @SequenceGenerator(name = "client_seq", sequenceName = "client_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String phone;

    public Client() {
    }
    public Client(Long id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Account> accounts = new HashSet<>();
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
        for (Account a : accounts) {
            a.setClient(this);
        }
    }
}

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "client_account",
//            joinColumns = { @JoinColumn(name = "client_id") },
//            inverseJoinColumns = { @JoinColumn(name = "account_id") })
//    private List<Account> accounts = new ArrayList<>();


