package ru.mtuci.simpleapiiuk.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
public class Client {
    public static final int START_SEQ = 1;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    private Date date_of_birth;
    @NotBlank
    private String passport;
    @NotBlank
    private String surname;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL
    )
    private Set<Account> accounts = new HashSet<>();

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getPassport() {
        return passport;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
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

//    public Set<Client> getClients() {
//        return clients;
//    }

//    public void setClients(Set<Client> clients) {
//        this.clients = clients;
//
//        for(Client c : clients) {
//            c.setClients((Set<Client>) this);
//        }
//    }

    @Override
    public String toString() {
        return date_of_birth + " : '" + name + " " + surname + " [" + passport + "] (" + phone + ')'; //
    }
}


//@OneToMany(
//        cascade = CascadeType.ALL,
//        orphanRemoval = true
//)
//    private List<Account> accounts = new ArrayList<>();
//    @Entity
//    public class Account {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private int id;
//
//        @NotNull
//        private String name;
//
//        @ManyToOne(fetch = FetchType.LAZY, optional = false)
//        @JoinColumn(name = "client_id")
//        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//        private Client client;
//
//        public int getId() {
//            return id;
//        }
//        public void setId(int id) {
//            this.id = id;
//        }
//        public String getName() {
//            return name;
//        }
//        public void setName(String name) {
//            this.name = name;
//        }
//        public Client getClient() {
//            return client;
//        }
//        public void setClient(Client client) {
//            this.client = client;
//        }
//    }


