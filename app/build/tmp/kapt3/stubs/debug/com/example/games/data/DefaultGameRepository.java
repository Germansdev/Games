package com.example.games.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J!\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"Lcom/example/games/data/DefaultGameRepository;", "Lcom/example/games/data/GameRepository;", "gameApiService", "Lcom/example/games/network/GameApiService;", "(Lcom/example/games/network/GameApiService;)V", "getGames", "Ljava/util/ArrayList;", "Lcom/example/games/model/Game;", "Lkotlin/collections/ArrayList;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DefaultGameRepository implements com.example.games.data.GameRepository {
    private final com.example.games.network.GameApiService gameApiService = null;
    
    public DefaultGameRepository(@org.jetbrains.annotations.NotNull
    com.example.games.network.GameApiService gameApiService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getGames(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.ArrayList<com.example.games.model.Game>> continuation) {
        return null;
    }
}