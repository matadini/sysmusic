package pl.matadini.sysmusic.server.context.user;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Access(AccessType.FIELD)
class User {

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    Long userId;

    @Column(unique = true, nullable = false)
    String login;

    @Column(nullable = false)
    String password;

    void changePassword(String newPassword) throws UserException{
        if(!this.password.equals(newPassword)) {
            this.password = newPassword;
        } else {
            throw new UserException("Nie można zmienić hasła na takie samo");
        }
    }

}

