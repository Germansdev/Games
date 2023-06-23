package com.example.games.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001aH\u0010\t\u001a\u00020\u00012\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u0013H\u0007\u001aj\u0010\t\u001a\u00020\u00012\b\b\u0001\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u0013H\u0007\u001a\u001c\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u00a8\u0006\u001b"}, d2 = {"BottomBar", "", "navController", "Landroidx/navigation/NavHostController;", "items", "", "Lcom/example/games/appDestinations/BottomBarScreen;", "modifier", "Landroidx/compose/ui/Modifier;", "CustomTopBar", "titleRes", "", "actionIcon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "actionIconContentDescription", "", "colors", "Landroidx/compose/material3/TopAppBarColors;", "onActionClick", "Lkotlin/Function0;", "navigationIcon", "navigationIconContentDescription", "onNavigationClick", "GameApp", "viewModel", "Lcom/example/games/ui/GameViewModel;", "currentRoute", "app_debug"})
public final class GameAppKt {
    
    @androidx.compose.runtime.Composable
    @android.annotation.SuppressLint(value = {"UnusedMaterialScaffoldPaddingParameter"})
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void GameApp(@org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull
    com.example.games.ui.GameViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void CustomTopBar(@androidx.annotation.StringRes
    int titleRes, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.graphics.vector.ImageVector navigationIcon, @org.jetbrains.annotations.Nullable
    java.lang.String navigationIconContentDescription, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.graphics.vector.ImageVector actionIcon, @org.jetbrains.annotations.Nullable
    java.lang.String actionIconContentDescription, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull
    androidx.compose.material3.TopAppBarColors colors, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigationClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onActionClick) {
    }
    
    /**
     * Top app bar with action, displayed on the right
     */
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void CustomTopBar(@androidx.annotation.StringRes
    int titleRes, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.graphics.vector.ImageVector actionIcon, @org.jetbrains.annotations.Nullable
    java.lang.String actionIconContentDescription, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull
    androidx.compose.material3.TopAppBarColors colors, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onActionClick) {
    }
    
    @org.jetbrains.annotations.Nullable
    @androidx.compose.runtime.Composable
    public static final java.lang.String currentRoute(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController) {
        return null;
    }
    
    @androidx.compose.runtime.Composable
    public static final void BottomBar(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavHostController navController, @org.jetbrains.annotations.NotNull
    java.util.List<? extends com.example.games.appDestinations.BottomBarScreen> items, @org.jetbrains.annotations.NotNull
    androidx.compose.ui.Modifier modifier) {
    }
}