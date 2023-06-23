package com.example.games;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/example/games/GameApplication;", "Landroid/app/Application;", "()V", "container", "Lcom/example/games/di/AppContainer;", "getContainer", "()Lcom/example/games/di/AppContainer;", "setContainer", "(Lcom/example/games/di/AppContainer;)V", "onCreate", "", "app_debug"})
public final class GameApplication extends android.app.Application {
    
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    public com.example.games.di.AppContainer container;
    
    public GameApplication() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.games.di.AppContainer getContainer() {
        return null;
    }
    
    public final void setContainer(@org.jetbrains.annotations.NotNull
    com.example.games.di.AppContainer p0) {
    }
    
    @java.lang.Override
    public void onCreate() {
    }
}