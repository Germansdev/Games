package com.example.games.network;

import java.lang.System;

/**
 * A public interface that exposes the [getGames] method
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\u0097@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\b"}, d2 = {"Lcom/example/games/network/GameApiService;", "", "getGames", "Ljava/util/ArrayList;", "Lcom/example/games/model/Game;", "Lkotlin/collections/ArrayList;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
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
    
    /**
     * A public interface that exposes the [getGames] method
     */
    @kotlin.Metadata(mv = {1, 7, 1}, k = 3)
    public final class DefaultImpls {
        
        /**
         * Returns a [List] of [Game] and this method can be called from a Coroutine.
         * The @GET annotation indicates that the "games" endpoint will be requested with the GET
         * HTTP method
         */
        @org.jetbrains.annotations.Nullable
        @retrofit2.http.GET(value = "games")
        public static java.lang.Object getGames(@org.jetbrains.annotations.NotNull
        com.example.games.network.GameApiService $this, @org.jetbrains.annotations.NotNull
        kotlin.coroutines.Continuation<? super java.util.ArrayList<com.example.games.model.Game>> p1) {
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