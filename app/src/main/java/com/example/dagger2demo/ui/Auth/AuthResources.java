package com.example.dagger2demo.ui.Auth;

import androidx.annotation.NonNull;

public class AuthResources<T> {
    @NonNull
    public final AuthStatus status;

    @NonNull
    public final T data;

    @NonNull
    public final String message;

    public AuthResources(@NonNull AuthStatus status, @NonNull T data, @NonNull String message){
        this.status = status;
        this. data = data;
        this.message = message;
    }

    public static <T> AuthResources<T> authenticated(@NonNull T data){
        return new AuthResources<>(AuthStatus.AUTHENTICATED, data, null);
    }

    public static <T> AuthResources<T> error(@NonNull String message, @NonNull T data){
        return new AuthResources<>(AuthStatus.ERROR, data, message);
    }

    public static <T> AuthResources<T> loading(@NonNull T data){
        return new AuthResources<>(AuthStatus.LOADING, data, null);
    }

    public static <T> AuthResources<T> logout(){
        return new AuthResources<>(AuthStatus.NOT_AUTHENTICATED, null, null);
    }

    public enum AuthStatus{AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED};
}
