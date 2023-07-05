package com.example.games;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u000f"}, d2 = {"Lcom/example/games/DetailsViewModel;", "Landroidx/lifecycle/ViewModel;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "itemsRepository", "Lcom/example/games/data/ItemsRepository;", "(Landroidx/lifecycle/SavedStateHandle;Lcom/example/games/data/ItemsRepository;)V", "itemId", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/example/games/GameDetailsUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "Companion", "app_debug"})
public final class DetailsViewModel extends androidx.lifecycle.ViewModel {
    private final int itemId = 0;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.example.games.GameDetailsUiState> uiState = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.games.DetailsViewModel.Companion Companion = null;
    private static final long TIMEOUT_MILLIS = 5000L;
    
    public DetailsViewModel(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull
    com.example.games.data.ItemsRepository itemsRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.example.games.GameDetailsUiState> getUiState() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/games/DetailsViewModel$Companion;", "", "()V", "TIMEOUT_MILLIS", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}