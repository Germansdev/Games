package com.example.games.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u0015"}, d2 = {"Lcom/example/games/di/DefaultAppContainer;", "Lcom/example/games/di/AppContainer;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "gameApiService", "Lcom/example/games/network/GameApiService;", "getGameApiService", "()Lcom/example/games/network/GameApiService;", "gameApiService$delegate", "Lkotlin/Lazy;", "gameRepository", "Lcom/example/games/data/GameRepository;", "getGameRepository", "()Lcom/example/games/data/GameRepository;", "gameRepository$delegate", "itemsRepository", "Lcom/example/games/data/ItemsRepository;", "getItemsRepository", "()Lcom/example/games/data/ItemsRepository;", "itemsRepository$delegate", "app_debug"})
public final class DefaultAppContainer implements com.example.games.di.AppContainer {
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy gameApiService$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy itemsRepository$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy gameRepository$delegate = null;
    
    public DefaultAppContainer(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.example.games.network.GameApiService getGameApiService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.example.games.data.ItemsRepository getItemsRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public com.example.games.data.GameRepository getGameRepository() {
        return null;
    }
}