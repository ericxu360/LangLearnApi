package com.example.langlearnapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auth0_user", schema = "auth0_user")
public class Auth0User {
    @Id
    @Column(name = "auth0_user_sub", nullable = false, length = Integer.MAX_VALUE)
    private String auth0UserSub;

    @NotNull
    @Column(name = "auth0_user_nickname", nullable = false, length = Integer.MAX_VALUE)
    private String auth0UserNickname;

    @NotNull
    @Column(name = "auth0_user_name", nullable = false, length = Integer.MAX_VALUE)
    private String auth0UserName;

    @NotNull
    @Column(name = "auth0_user_picture", nullable = false, length = Integer.MAX_VALUE)
    private String auth0UserPicture;

}