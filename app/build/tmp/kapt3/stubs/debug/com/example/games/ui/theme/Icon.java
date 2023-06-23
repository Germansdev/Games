package com.example.games.ui.theme;

import java.lang.System;

/**
 * A sealed class to make dealing with [ImageVector] and [DrawableRes] icons easier.
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/games/ui/theme/Icon;", "", "()V", "DrawableResourceIcon", "ImageVectorIcon", "Lcom/example/games/ui/theme/Icon$DrawableResourceIcon;", "Lcom/example/games/ui/theme/Icon$ImageVectorIcon;", "app_debug"})
public abstract class Icon {
    
    private Icon() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/games/ui/theme/Icon$ImageVectorIcon;", "Lcom/example/games/ui/theme/Icon;", "imageVector", "Landroidx/compose/ui/graphics/vector/ImageVector;", "(Landroidx/compose/ui/graphics/vector/ImageVector;)V", "getImageVector", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_debug"})
    public static final class ImageVectorIcon extends com.example.games.ui.theme.Icon {
        @org.jetbrains.annotations.NotNull
        private final androidx.compose.ui.graphics.vector.ImageVector imageVector = null;
        
        @org.jetbrains.annotations.NotNull
        public final com.example.games.ui.theme.Icon.ImageVectorIcon copy(@org.jetbrains.annotations.NotNull
        androidx.compose.ui.graphics.vector.ImageVector imageVector) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public java.lang.String toString() {
            return null;
        }
        
        public ImageVectorIcon(@org.jetbrains.annotations.NotNull
        androidx.compose.ui.graphics.vector.ImageVector imageVector) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.compose.ui.graphics.vector.ImageVector component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.compose.ui.graphics.vector.ImageVector getImageVector() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00d6\u0001J\t\u0010\u000e\u001a\u00020\u000fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/example/games/ui/theme/Icon$DrawableResourceIcon;", "Lcom/example/games/ui/theme/Icon;", "id", "", "(I)V", "getId", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
    public static final class DrawableResourceIcon extends com.example.games.ui.theme.Icon {
        private final int id = 0;
        
        @org.jetbrains.annotations.NotNull
        public final com.example.games.ui.theme.Icon.DrawableResourceIcon copy(@androidx.annotation.DrawableRes
        int id) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull
        @java.lang.Override
        public java.lang.String toString() {
            return null;
        }
        
        public DrawableResourceIcon(@androidx.annotation.DrawableRes
        int id) {
            super();
        }
        
        public final int component1() {
            return 0;
        }
        
        public final int getId() {
            return 0;
        }
    }
}