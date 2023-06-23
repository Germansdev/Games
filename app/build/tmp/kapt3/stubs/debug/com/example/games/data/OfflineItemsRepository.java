package com.example.games.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J)\u0010\u0010\u001a\u00020\u00062\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0012j\b\u0012\u0004\u0012\u00020\b`\u0013H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/example/games/data/OfflineItemsRepository;", "Lcom/example/games/data/ItemsRepository;", "itemDao", "Lcom/example/games/data/GameDao;", "(Lcom/example/games/data/GameDao;)V", "deleteItem", "", "item", "Lcom/example/games/model/Game;", "(Lcom/example/games/model/Game;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllItemsStream", "Lkotlinx/coroutines/flow/Flow;", "", "getItemStream", "id", "", "insertAll", "items", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertItem", "updateItem", "app_debug"})
public final class OfflineItemsRepository implements com.example.games.data.ItemsRepository {
    private final com.example.games.data.GameDao itemDao = null;
    
    public OfflineItemsRepository(@org.jetbrains.annotations.NotNull
    com.example.games.data.GameDao itemDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllItemsStream() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public com.example.games.model.Game getItemStream(int id) {
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