package vn.letsgo.elearning.entity.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("ADMIN:GET"),
    ADMIN_UPDATE("ADMIN:PUT"),
    ADMIN_CREATE("ADMIN:POST"),
    ADMIN_DELETE("ADMIN:DELETE"),
    MANAGER_READ("MANAGER:GET"),
    MANAGER_UPDATE("MANAGER:PUT"),
    MANAGER_CREATE("MANAGER:POST"),
    MANAGER_DELETE("MANAGER:DELETE"),
    USER_READ("USER:GET"),
    USER_UPDATE("USER:PUT"),
    USER_CREATE("USER:POST"),
    USER_DELETE("USER:DELETE"),

    ;

    @Getter
    private final String permission;
}
