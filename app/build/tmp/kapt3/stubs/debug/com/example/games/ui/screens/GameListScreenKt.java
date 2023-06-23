package com.example.games.ui.screens;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u001e\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0007\u001a&\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0007\u001a*\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u000bH\u0007\u001a\b\u0010\u0015\u001a\u00020\u0003H\u0007\u001a\b\u0010\u0016\u001a\u00020\u0003H\u0007\u001a\u001e\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0007\u001a\u001e\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u00062\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\bH\u0007\u001a\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\f\u001a\u00020\rH\u0000\u001a0\u0010 \u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"TAG", "", "ErrorScreenPreview", "", "FavoriteButton", "favorite", "", "onFavoriteClick", "Lkotlin/Function0;", "GameCard", "gameViewModel", "Lcom/example/games/ui/GameViewModel;", "game", "Lcom/example/games/model/Game;", "onClick", "GameListScreen", "modifier", "Landroidx/compose/ui/Modifier;", "games", "", "viewModel", "GameScreenPreview", "LoadingScreenPreview", "PlayButton", "play", "onPlayClick", "ShareButton", "share", "onShareClick", "playGame", "context", "Landroid/content/Context;", "shareGame", "subject", "summary", "link", "app_debug"})
public final class GameListScreenKt {
    private static final java.lang.String TAG = "Dev4";
    
    @androidx.compose.runtime.Composable
    public static final void GameListScreen(@org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.games.model.Game> games, @org.jetbrains.annotations.NotNull
    com.example.games.ui.GameViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void GameCard(@org.jetbrains.annotations.NotNull
    com.example.games.ui.GameViewModel gameViewModel, @org.jetbrains.annotations.NotNull
    com.example.games.model.Game game, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    public static final void playGame(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.example.games.model.Game game) {
    }
    
    public static final void shareGame(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String subject, @org.jetbrains.annotations.NotNull
    java.lang.String summary, @org.jetbrains.annotations.NotNull
    java.lang.String link, @org.jetbrains.annotations.NotNull
    com.example.games.model.Game game) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void ShareButton(boolean share, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onShareClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void PlayButton(boolean play, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onPlayClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void FavoriteButton(boolean favorite, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onFavoriteClick) {
    }
    
    @androidx.compose.runtime.Composable
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    public static final void LoadingScreenPreview() {
    }
    
    @androidx.compose.runtime.Composable
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    public static final void ErrorScreenPreview() {
    }
    
    @androidx.compose.runtime.Composable
    @androidx.compose.ui.tooling.preview.Preview(showBackground = true)
    public static final void GameScreenPreview() {
    }
}