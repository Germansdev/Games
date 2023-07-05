package com.example.games.appDestinations;

import java.lang.System;

/**
 * Interface to describe the navigation destinations for the app
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/example/games/appDestinations/NavigationDestination;", "", "route", "", "getRoute", "()Ljava/lang/String;", "titleRes", "", "getTitleRes", "()I", "app_debug"})
public abstract interface NavigationDestination {
    
    /**
     * Unique name to define the path for a composable
     */
    @org.jetbrains.annotations.NotNull
    public abstract java.lang.String getRoute();
    
    /**
     * String resource id to that contains title to be displayed for the screen.
     */
    public abstract int getTitleRes();
}