package com.example.games.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \n2\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000b"}, d2 = {"Lcom/example/games/ui/FavoritesViewModel;", "Landroidx/lifecycle/ViewModel;", "itemsRepository", "Lcom/example/games/data/ItemsRepository;", "(Lcom/example/games/data/ItemsRepository;)V", "favoritesUiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/example/games/ui/FavoritesUiState;", "getFavoritesUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "Companion", "app_debug"})
public final class FavoritesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.games.ui.FavoritesUiState> favoritesUiState = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.games.ui.FavoritesViewModel.Companion Companion = null;
    private static final long TIMEOUT_MILLIS = 5000L;
    
    public FavoritesViewModel(@org.jetbrains.annotations.NotNull
    com.example.games.data.ItemsRepository itemsRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.games.ui.FavoritesUiState> getFavoritesUiState() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/games/ui/FavoritesViewModel$Companion;", "", "()V", "TIMEOUT_MILLIS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}