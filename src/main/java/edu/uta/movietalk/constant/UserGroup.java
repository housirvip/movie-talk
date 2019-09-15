package edu.uta.movietalk.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author housirvip
 */
@Getter
@AllArgsConstructor
public enum UserGroup {

    /**
     * Primary
     */
    Limited("Limited"),
    /**
     * User
     */
    User("user"),
    /**
     * Vip
     */
    Vip("vip"),
    /**
     * Admin
     */
    Admin("admin"),
    /**
     * Root
     */
    Root("root");

    private String value;
}
