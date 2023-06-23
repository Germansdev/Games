package com.example.games.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/example/games/di/AppContainer;", "", "gameApiService", "Lcom/example/games/network/GameApiService;", "getGameApiService", "()Lcom/example/games/network/GameApiService;", "gameRepository", "Lcom/example/games/data/GameRepository;", "getGameRepository", "()Lcom/example/games/data/GameRepository;", "itemsRepository", "Lcom/example/games/data/ItemsRepository;", "getItemsRepository", "()Lcom/example/games/data/ItemsRepository;", "app_debug"})
public abstract interface AppContainer {
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.games.network.GameApiService getGameApiService();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.games.data.GameRepository getGameRepository();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.games.data.ItemsRepository getItemsRepository();
}