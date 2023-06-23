package com.example.games.network;

import java.lang.System;

/**
 * A public interface that exposes the [getGames] method
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u0000 \r2\u00020\u0001:\u0001\rJ!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH&J-\u0010\t\u001a\u00020\n2\u001a\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/example/games/network/GameApiService;", "", "getGames", "Ljava/util/ArrayList;", "Lcom/example/games/model/Game;", "Lkotlin/collections/ArrayList;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocalGames", "Lcom/example/games/data/GameDao;", "updateLocalGames", "", "games", "(Ljava/util/ArrayList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface GameApiService {
    @org.jetbrains.annotations.NotNull
    public static final com.example.games.network.GameApiService.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String BASE_URL = "https://www.freetogame.com/api/";
    
    /**
     * Returns a [List] of [Game] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "games" endpoint will be requested with the GET
     * HTTP method
     */
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "games")
    public abstract java.lang.Object getGames(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.ArrayList<com.example.games.model.Game>> continuation);
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.games.data.GameDao getLocalGames();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateLocalGames(@org.jetbrains.annotations.Nullable
    java.util.ArrayList<com.example.games.model.Game> games, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * A public interface that exposes the [getGames] method
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 3)
    public final class DefaultImpls {
        
        @org.jetbrains.annotations.Nullable
        public static java.lang.Object updateLocalGames(@org.jetbrains.annotations.NotNull
        com.example.games.network.GameApiService $this, @org.jetbrains.annotations.Nullable
        java.util.ArrayList<com.example.games.model.Game> games, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super kotlin.Unit> p2) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/games/network/GameApiService$Companion;", "", "()V", "BASE_URL", "", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String BASE_URL = "https://www.freetogame.com/api/";
        
        private Companion() {
            super();
        }
    }
}