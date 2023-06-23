package com.example.games.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH&J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH&J)\u0010\r\u001a\u00020\u00032\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u000fj\b\u0012\u0004\u0012\u00020\u0005`\u0010H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/example/games/data/ItemsRepository;", "", "deleteItem", "", "item", "Lcom/example/games/model/Game;", "(Lcom/example/games/model/Game;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllItemsStream", "Lkotlinx/coroutines/flow/Flow;", "", "getItemStream", "id", "", "insertAll", "items", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertItem", "updateItem", "app_debug"})
public abstract interface ItemsRepository {
    
    /**
     * Retrieve all the items from the the given data source.
     */
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllItemsStream();
    
    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    @org.jetbrains.annotations.Nullable
    public abstract com.example.games.model.Game getItemStream(int id);
    
    /**
     * Insert item in the data source
     */
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertItem(@org.jetbrains.annotations.NotNull
    com.example.games.model.Game item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.example.games.model.Game> items, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * Delete item from the data source
     */
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteItem(@org.jetbrains.annotations.NotNull
    com.example.games.model.Game item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    /**
     * Update item in the data source
     */
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateItem(@org.jetbrains.annotations.NotNull
    com.example.games.model.Game item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}