package com.example.games.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t0\b2\u0006\u0010\n\u001a\u00020\u000bH&J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH&J\u001e\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t0\b2\u0006\u0010\u000e\u001a\u00020\u000bH&J\u001e\u0010\u000f\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t0\b2\u0006\u0010\u000e\u001a\u00020\u000bH&J\u001e\u0010\u0010\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t0\b2\u0006\u0010\u0011\u001a\u00020\u000bH&J\u0018\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\b2\u0006\u0010\u0013\u001a\u00020\u0014H&J)\u0010\u0015\u001a\u00020\u00032\u0016\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0017j\b\u0012\u0004\u0012\u00020\u0005`\u0018H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001c"}, d2 = {"Lcom/example/games/data/ItemsRepository;", "", "deleteItem", "", "item", "Lcom/example/games/model/Game;", "(Lcom/example/games/model/Game;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFavoritesStream", "Lkotlinx/coroutines/flow/Flow;", "", "isFavorite", "", "getAllItemsStream", "getAllNotPlayedStream", "isPlayed", "getAllPlayedStream", "getAllSharedStream", "isShared", "getItemStream", "id", "", "insertAll", "items", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertItem", "updateItem", "app_debug"})
public abstract interface ItemsRepository {
    
    /**
     * Retrieve all the items from the the given data source.
     */
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllItemsStream();
    
    /**
     * Retrieve an item from the given data source that matches with the [id].
     */
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.example.games.model.Game> getItemStream(int id);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllFavoritesStream(boolean isFavorite);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllPlayedStream(boolean isPlayed);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllNotPlayedStream(boolean isPlayed);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.games.model.Game>> getAllSharedStream(boolean isShared);
    
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