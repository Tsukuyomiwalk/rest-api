package vk.app.authorization.userInfo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@RequiredArgsConstructor
public enum AccessRoles {

    USER(Collections.emptySet()),
    ADMIN(AccessesOf(
            Access.ADMIN_READ, Access.ADMIN_UPDATE, Access.ADMIN_DELETE, Access.ADMIN_CREATE,
            Access.POSTS_READ, Access.POSTS_UPDATE, Access.POSTS_DELETE, Access.POSTS_CREATE,
            Access.USERS_READ, Access.USERS_UPDATE, Access.USERS_DELETE, Access.USERS_CREATE,
            Access.ALBUMS_READ, Access.ALBUMS_UPDATE, Access.ALBUMS_DELETE, Access.ALBUMS_CREATE
    )),
    POSTS(AccessesOf(
            Access.POSTS_READ, Access.POSTS_UPDATE, Access.POSTS_DELETE, Access.POSTS_CREATE
    )),
    USERS(AccessesOf(
            Access.USERS_READ, Access.USERS_UPDATE, Access.USERS_DELETE, Access.USERS_CREATE
    )),
    ALBUMS(AccessesOf(
            Access.ALBUMS_READ, Access.ALBUMS_UPDATE, Access.ALBUMS_DELETE, Access.ALBUMS_CREATE
    ));

    private final Set<Access> Accesses;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return Accesses.stream()
                .map(Access -> new SimpleGrantedAuthority(Access.getAccess()))
                .collect(Collectors.toList());
    }

    private static Set<Access> AccessesOf(Access... Accesses) {
        return Set.of(Accesses);
    }
}
