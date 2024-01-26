package letsgo.vn.elearning.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

import static letsgo.vn.elearning.entity.user.Permission.*;

@RequiredArgsConstructor
@Slf4j
public enum Role {

    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_CREATE,
                    MANAGER_DELETE,
                    USER_READ,
                    USER_UPDATE,
                    USER_CREATE,
                    USER_DELETE
            )
    ),
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_UPDATE,
                    MANAGER_CREATE,
                    MANAGER_DELETE,
                    USER_READ,
                    USER_UPDATE,
                    USER_CREATE,
                    USER_DELETE
            )
    ),
    USER(
            Set.of(
                    USER_READ,
                    USER_UPDATE,
                    USER_CREATE,
                    USER_DELETE
            )
    ),



    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Permission permission : getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission.name()));
        }

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        log.info("This authorities: " + authorities);

        return authorities;
    }


}
