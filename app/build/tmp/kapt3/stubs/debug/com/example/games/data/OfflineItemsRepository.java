package com.example.games.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\rH\u0016J\u001e\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000e0\r2\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u001e\u0010\u0014\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000e0\r2\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u001e\u0010\u0015\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000e0\r2\u0006\u0010\u0016\u001a\u00020\u0010H\u0016J\u0018\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\r2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J)\u0010\u001a\u001a\u00020\b2\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\n0\u001cj\b\u0012\u0004\u0012\u00020\n`\u001dH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u0019\u0010\u001f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010 \u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/example/games/data/OfflineItemsRepository;", "Lcom/example/games/data/ItemsRepository;", "itemDao", "Lcom/example/games/data/GameDao;", "apiService", "Lcom/example/games/network/GameApiService;", "(Lcom/example/games/data/GameDao;Lcom/example/games/network/GameApiService;)V", "deleteItem", "", "item", "Lcom/example/games/model/Game;", "(Lcom/example/games/model/Game;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFavoritesStream", "Lkotlinx/coroutines/flow/Flow;", "", "isFavorite", "", "getAllItemsStream", "getAllNotPlayedStream", "isPlayed", "getAllPlayedStream", "getAllSharedStream", "isShared", "getItemStream", "id", "", "insertAll", "items", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertItem", "updateItem", "app_debug"})
public final class OfflineItemsRepository implements com.example.games.data.ItemsRepository {
    private final com.example.games.data.GameDao itemDao = null;
    private final com.example.games.network.GameApiService apiService = null;
    
    public OfflineItemsRepository(@org.jetbrains.annotations.NotNull
    com.example.games.data.GameDao itemDao, @org.jetbrains.annotations.NotNull
    com.example.games.network.GameApiService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllItemsStream() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<com.example.games.model.Game> getItemStream(int id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllFavoritesStream(boolean isFavorite) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllPlayedStream(boolean isPlayed) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllNotPlayedStream(boolean isPlayed) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllSharedStream(boolean isShared) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object insertItem(@org.jetbrains.annotations.NotNull
    com.example.games.model.Game item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object insertAll(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.example.games.model.Game> items, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object deleteItem(@org.jetbrains.annotations.NotNull
    com.example.games.model.Game item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object updateItem(@org.jetbrains.annotations.NotNull
    com.example.games.model.Game item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}