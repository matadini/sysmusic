package pl.matadini.sysmusic.server.context.user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
class UserDto {
    Long userId;
    String login;
}
