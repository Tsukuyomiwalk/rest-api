package vk.app.authorization.userInfo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Access {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    POSTS_READ("posts:read"),
    POSTS_UPDATE("posts:update"),
    POSTS_CREATE("posts:create"),
    POSTS_DELETE("posts:delete"),
    USERS_READ("users:read"),
    USERS_UPDATE("users:update"),
    USERS_CREATE("users:create"),
    USERS_DELETE("users:delete"),
    ALBUMS_READ("albums:read"),
    ALBUMS_UPDATE("albums:update"),
    ALBUMS_CREATE("albums:create"),
    ALBUMS_DELETE("albums:delete");

    private final String access;
}
