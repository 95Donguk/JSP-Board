package edu.mzc.myboard.vo;

import edu.mzc.myboard.domain.user.Id;
import edu.mzc.myboard.domain.user.Name;
import edu.mzc.myboard.domain.user.Password;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode(exclude = "password")
public class User {
    @Getter(AccessLevel.NONE)
    private final Id id;
    private final Password password;
    @Getter(AccessLevel.NONE)
    private final Name name;
    private final String role;

    public String getId() {
        return id.getId();
    }

    public String getName() {
        return name.getName();
    }
}
