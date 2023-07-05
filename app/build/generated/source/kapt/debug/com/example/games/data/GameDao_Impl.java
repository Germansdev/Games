package com.example.games.data;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.games.model.Game;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class GameDao_Impl implements GameDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Game> __insertionAdapterOfGame;

  private final EntityDeletionOrUpdateAdapter<Game> __deletionAdapterOfGame;

  private final EntityDeletionOrUpdateAdapter<Game> __updateAdapterOfGame;

  public GameDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGame = new EntityInsertionAdapter<Game>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Items` (`developer`,`game_url`,`freetogame_profile_url`,`genre`,`platform`,`publisher`,`release_date`,`id`,`title`,`short_description`,`thumbnail`,`rating`,`isFavorite`,`isPlayed`,`isShared`,`played`,`favorited`,`shared`) VALUES (?,?,?,?,?,?,?,nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Game value) {
        if (value.getDeveloper() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDeveloper());
        }
        if (value.getGame_url() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getGame_url());
        }
        if (value.getFreetogame_profile_url() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFreetogame_profile_url());
        }
        if (value.getGenre() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getGenre());
        }
        if (value.getPlatform() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPlatform());
        }
        if (value.getPublisher() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPublisher());
        }
        if (value.getRelease_date() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getRelease_date());
        }
        stmt.bindLong(8, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getTitle());
        }
        if (value.getShort_description() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getShort_description());
        }
        if (value.getThumbnail() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getThumbnail());
        }
        stmt.bindDouble(12, value.getRating());
        final int _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(13, _tmp);
        final int _tmp_1 = value.isPlayed() ? 1 : 0;
        stmt.bindLong(14, _tmp_1);
        final int _tmp_2 = value.isShared() ? 1 : 0;
        stmt.bindLong(15, _tmp_2);
        if (value.getPlayed() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindLong(16, value.getPlayed());
        }
        if (value.getFavorited() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindLong(17, value.getFavorited());
        }
        if (value.getShared() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindLong(18, value.getShared());
        }
      }
    };
    this.__deletionAdapterOfGame = new EntityDeletionOrUpdateAdapter<Game>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Items` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Game value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfGame = new EntityDeletionOrUpdateAdapter<Game>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Items` SET `developer` = ?,`game_url` = ?,`freetogame_profile_url` = ?,`genre` = ?,`platform` = ?,`publisher` = ?,`release_date` = ?,`id` = ?,`title` = ?,`short_description` = ?,`thumbnail` = ?,`rating` = ?,`isFavorite` = ?,`isPlayed` = ?,`isShared` = ?,`played` = ?,`favorited` = ?,`shared` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Game value) {
        if (value.getDeveloper() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDeveloper());
        }
        if (value.getGame_url() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getGame_url());
        }
        if (value.getFreetogame_profile_url() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getFreetogame_profile_url());
        }
        if (value.getGenre() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getGenre());
        }
        if (value.getPlatform() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPlatform());
        }
        if (value.getPublisher() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getPublisher());
        }
        if (value.getRelease_date() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getRelease_date());
        }
        stmt.bindLong(8, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getTitle());
        }
        if (value.getShort_description() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getShort_description());
        }
        if (value.getThumbnail() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getThumbnail());
        }
        stmt.bindDouble(12, value.getRating());
        final int _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(13, _tmp);
        final int _tmp_1 = value.isPlayed() ? 1 : 0;
        stmt.bindLong(14, _tmp_1);
        final int _tmp_2 = value.isShared() ? 1 : 0;
        stmt.bindLong(15, _tmp_2);
        if (value.getPlayed() == null) {
          stmt.bindNull(16);
        } else {
          stmt.bindLong(16, value.getPlayed());
        }
        if (value.getFavorited() == null) {
          stmt.bindNull(17);
        } else {
          stmt.bindLong(17, value.getFavorited());
        }
        if (value.getShared() == null) {
          stmt.bindNull(18);
        } else {
          stmt.bindLong(18, value.getShared());
        }
        stmt.bindLong(19, value.getId());
      }
    };
  }

  @Override
  public Object insert(final Game item, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGame.insert(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertAll(final ArrayList<Game> items, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfGame.insert(items);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object delete(final Game item, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfGame.handle(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object update(final Game item, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfGame.handle(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<Game>> getAllItems() {
    final String _sql = "SELECT * from items ORDER BY title ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"items"}, new Callable<List<Game>>() {
      @Override
      public List<Game> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeveloper = CursorUtil.getColumnIndexOrThrow(_cursor, "developer");
          final int _cursorIndexOfGameUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "game_url");
          final int _cursorIndexOfFreetogameProfileUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "freetogame_profile_url");
          final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfShortDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "short_description");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlayed");
          final int _cursorIndexOfIsShared = CursorUtil.getColumnIndexOrThrow(_cursor, "isShared");
          final int _cursorIndexOfPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "played");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfShared = CursorUtil.getColumnIndexOrThrow(_cursor, "shared");
          final List<Game> _result = new ArrayList<Game>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Game _item;
            final String _tmpDeveloper;
            if (_cursor.isNull(_cursorIndexOfDeveloper)) {
              _tmpDeveloper = null;
            } else {
              _tmpDeveloper = _cursor.getString(_cursorIndexOfDeveloper);
            }
            final String _tmpGame_url;
            if (_cursor.isNull(_cursorIndexOfGameUrl)) {
              _tmpGame_url = null;
            } else {
              _tmpGame_url = _cursor.getString(_cursorIndexOfGameUrl);
            }
            final String _tmpFreetogame_profile_url;
            if (_cursor.isNull(_cursorIndexOfFreetogameProfileUrl)) {
              _tmpFreetogame_profile_url = null;
            } else {
              _tmpFreetogame_profile_url = _cursor.getString(_cursorIndexOfFreetogameProfileUrl);
            }
            final String _tmpGenre;
            if (_cursor.isNull(_cursorIndexOfGenre)) {
              _tmpGenre = null;
            } else {
              _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
            }
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpRelease_date;
            if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
              _tmpRelease_date = null;
            } else {
              _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpShort_description;
            if (_cursor.isNull(_cursorIndexOfShortDescription)) {
              _tmpShort_description = null;
            } else {
              _tmpShort_description = _cursor.getString(_cursorIndexOfShortDescription);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsPlayed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlayed);
            _tmpIsPlayed = _tmp_1 != 0;
            final boolean _tmpIsShared;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsShared);
            _tmpIsShared = _tmp_2 != 0;
            final Integer _tmpPlayed;
            if (_cursor.isNull(_cursorIndexOfPlayed)) {
              _tmpPlayed = null;
            } else {
              _tmpPlayed = _cursor.getInt(_cursorIndexOfPlayed);
            }
            final Integer _tmpFavorited;
            if (_cursor.isNull(_cursorIndexOfFavorited)) {
              _tmpFavorited = null;
            } else {
              _tmpFavorited = _cursor.getInt(_cursorIndexOfFavorited);
            }
            final Integer _tmpShared;
            if (_cursor.isNull(_cursorIndexOfShared)) {
              _tmpShared = null;
            } else {
              _tmpShared = _cursor.getInt(_cursorIndexOfShared);
            }
            _item = new Game(_tmpDeveloper,_tmpGame_url,_tmpFreetogame_profile_url,_tmpGenre,_tmpPlatform,_tmpPublisher,_tmpRelease_date,_tmpId,_tmpTitle,_tmpShort_description,_tmpThumbnail,_tmpRating,_tmpIsFavorite,_tmpIsPlayed,_tmpIsShared,_tmpPlayed,_tmpFavorited,_tmpShared);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Game> getItem(final int id) {
    final String _sql = "SELECT * from items WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"items"}, new Callable<Game>() {
      @Override
      public Game call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeveloper = CursorUtil.getColumnIndexOrThrow(_cursor, "developer");
          final int _cursorIndexOfGameUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "game_url");
          final int _cursorIndexOfFreetogameProfileUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "freetogame_profile_url");
          final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfShortDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "short_description");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlayed");
          final int _cursorIndexOfIsShared = CursorUtil.getColumnIndexOrThrow(_cursor, "isShared");
          final int _cursorIndexOfPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "played");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfShared = CursorUtil.getColumnIndexOrThrow(_cursor, "shared");
          final Game _result;
          if(_cursor.moveToFirst()) {
            final String _tmpDeveloper;
            if (_cursor.isNull(_cursorIndexOfDeveloper)) {
              _tmpDeveloper = null;
            } else {
              _tmpDeveloper = _cursor.getString(_cursorIndexOfDeveloper);
            }
            final String _tmpGame_url;
            if (_cursor.isNull(_cursorIndexOfGameUrl)) {
              _tmpGame_url = null;
            } else {
              _tmpGame_url = _cursor.getString(_cursorIndexOfGameUrl);
            }
            final String _tmpFreetogame_profile_url;
            if (_cursor.isNull(_cursorIndexOfFreetogameProfileUrl)) {
              _tmpFreetogame_profile_url = null;
            } else {
              _tmpFreetogame_profile_url = _cursor.getString(_cursorIndexOfFreetogameProfileUrl);
            }
            final String _tmpGenre;
            if (_cursor.isNull(_cursorIndexOfGenre)) {
              _tmpGenre = null;
            } else {
              _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
            }
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpRelease_date;
            if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
              _tmpRelease_date = null;
            } else {
              _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpShort_description;
            if (_cursor.isNull(_cursorIndexOfShortDescription)) {
              _tmpShort_description = null;
            } else {
              _tmpShort_description = _cursor.getString(_cursorIndexOfShortDescription);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsPlayed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlayed);
            _tmpIsPlayed = _tmp_1 != 0;
            final boolean _tmpIsShared;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsShared);
            _tmpIsShared = _tmp_2 != 0;
            final Integer _tmpPlayed;
            if (_cursor.isNull(_cursorIndexOfPlayed)) {
              _tmpPlayed = null;
            } else {
              _tmpPlayed = _cursor.getInt(_cursorIndexOfPlayed);
            }
            final Integer _tmpFavorited;
            if (_cursor.isNull(_cursorIndexOfFavorited)) {
              _tmpFavorited = null;
            } else {
              _tmpFavorited = _cursor.getInt(_cursorIndexOfFavorited);
            }
            final Integer _tmpShared;
            if (_cursor.isNull(_cursorIndexOfShared)) {
              _tmpShared = null;
            } else {
              _tmpShared = _cursor.getInt(_cursorIndexOfShared);
            }
            _result = new Game(_tmpDeveloper,_tmpGame_url,_tmpFreetogame_profile_url,_tmpGenre,_tmpPlatform,_tmpPublisher,_tmpRelease_date,_tmpId,_tmpTitle,_tmpShort_description,_tmpThumbnail,_tmpRating,_tmpIsFavorite,_tmpIsPlayed,_tmpIsShared,_tmpPlayed,_tmpFavorited,_tmpShared);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Game>> getAllFavorites() {
    final String _sql = "SELECT * FROM items WHERE isFavorite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"items"}, new Callable<List<Game>>() {
      @Override
      public List<Game> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeveloper = CursorUtil.getColumnIndexOrThrow(_cursor, "developer");
          final int _cursorIndexOfGameUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "game_url");
          final int _cursorIndexOfFreetogameProfileUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "freetogame_profile_url");
          final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfShortDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "short_description");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlayed");
          final int _cursorIndexOfIsShared = CursorUtil.getColumnIndexOrThrow(_cursor, "isShared");
          final int _cursorIndexOfPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "played");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfShared = CursorUtil.getColumnIndexOrThrow(_cursor, "shared");
          final List<Game> _result = new ArrayList<Game>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Game _item;
            final String _tmpDeveloper;
            if (_cursor.isNull(_cursorIndexOfDeveloper)) {
              _tmpDeveloper = null;
            } else {
              _tmpDeveloper = _cursor.getString(_cursorIndexOfDeveloper);
            }
            final String _tmpGame_url;
            if (_cursor.isNull(_cursorIndexOfGameUrl)) {
              _tmpGame_url = null;
            } else {
              _tmpGame_url = _cursor.getString(_cursorIndexOfGameUrl);
            }
            final String _tmpFreetogame_profile_url;
            if (_cursor.isNull(_cursorIndexOfFreetogameProfileUrl)) {
              _tmpFreetogame_profile_url = null;
            } else {
              _tmpFreetogame_profile_url = _cursor.getString(_cursorIndexOfFreetogameProfileUrl);
            }
            final String _tmpGenre;
            if (_cursor.isNull(_cursorIndexOfGenre)) {
              _tmpGenre = null;
            } else {
              _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
            }
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpRelease_date;
            if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
              _tmpRelease_date = null;
            } else {
              _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpShort_description;
            if (_cursor.isNull(_cursorIndexOfShortDescription)) {
              _tmpShort_description = null;
            } else {
              _tmpShort_description = _cursor.getString(_cursorIndexOfShortDescription);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsPlayed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlayed);
            _tmpIsPlayed = _tmp_1 != 0;
            final boolean _tmpIsShared;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsShared);
            _tmpIsShared = _tmp_2 != 0;
            final Integer _tmpPlayed;
            if (_cursor.isNull(_cursorIndexOfPlayed)) {
              _tmpPlayed = null;
            } else {
              _tmpPlayed = _cursor.getInt(_cursorIndexOfPlayed);
            }
            final Integer _tmpFavorited;
            if (_cursor.isNull(_cursorIndexOfFavorited)) {
              _tmpFavorited = null;
            } else {
              _tmpFavorited = _cursor.getInt(_cursorIndexOfFavorited);
            }
            final Integer _tmpShared;
            if (_cursor.isNull(_cursorIndexOfShared)) {
              _tmpShared = null;
            } else {
              _tmpShared = _cursor.getInt(_cursorIndexOfShared);
            }
            _item = new Game(_tmpDeveloper,_tmpGame_url,_tmpFreetogame_profile_url,_tmpGenre,_tmpPlatform,_tmpPublisher,_tmpRelease_date,_tmpId,_tmpTitle,_tmpShort_description,_tmpThumbnail,_tmpRating,_tmpIsFavorite,_tmpIsPlayed,_tmpIsShared,_tmpPlayed,_tmpFavorited,_tmpShared);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Game>> getAllPlayed() {
    final String _sql = "SELECT * FROM items WHERE isPlayed = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"items"}, new Callable<List<Game>>() {
      @Override
      public List<Game> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeveloper = CursorUtil.getColumnIndexOrThrow(_cursor, "developer");
          final int _cursorIndexOfGameUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "game_url");
          final int _cursorIndexOfFreetogameProfileUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "freetogame_profile_url");
          final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfShortDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "short_description");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlayed");
          final int _cursorIndexOfIsShared = CursorUtil.getColumnIndexOrThrow(_cursor, "isShared");
          final int _cursorIndexOfPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "played");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfShared = CursorUtil.getColumnIndexOrThrow(_cursor, "shared");
          final List<Game> _result = new ArrayList<Game>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Game _item;
            final String _tmpDeveloper;
            if (_cursor.isNull(_cursorIndexOfDeveloper)) {
              _tmpDeveloper = null;
            } else {
              _tmpDeveloper = _cursor.getString(_cursorIndexOfDeveloper);
            }
            final String _tmpGame_url;
            if (_cursor.isNull(_cursorIndexOfGameUrl)) {
              _tmpGame_url = null;
            } else {
              _tmpGame_url = _cursor.getString(_cursorIndexOfGameUrl);
            }
            final String _tmpFreetogame_profile_url;
            if (_cursor.isNull(_cursorIndexOfFreetogameProfileUrl)) {
              _tmpFreetogame_profile_url = null;
            } else {
              _tmpFreetogame_profile_url = _cursor.getString(_cursorIndexOfFreetogameProfileUrl);
            }
            final String _tmpGenre;
            if (_cursor.isNull(_cursorIndexOfGenre)) {
              _tmpGenre = null;
            } else {
              _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
            }
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpRelease_date;
            if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
              _tmpRelease_date = null;
            } else {
              _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpShort_description;
            if (_cursor.isNull(_cursorIndexOfShortDescription)) {
              _tmpShort_description = null;
            } else {
              _tmpShort_description = _cursor.getString(_cursorIndexOfShortDescription);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsPlayed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlayed);
            _tmpIsPlayed = _tmp_1 != 0;
            final boolean _tmpIsShared;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsShared);
            _tmpIsShared = _tmp_2 != 0;
            final Integer _tmpPlayed;
            if (_cursor.isNull(_cursorIndexOfPlayed)) {
              _tmpPlayed = null;
            } else {
              _tmpPlayed = _cursor.getInt(_cursorIndexOfPlayed);
            }
            final Integer _tmpFavorited;
            if (_cursor.isNull(_cursorIndexOfFavorited)) {
              _tmpFavorited = null;
            } else {
              _tmpFavorited = _cursor.getInt(_cursorIndexOfFavorited);
            }
            final Integer _tmpShared;
            if (_cursor.isNull(_cursorIndexOfShared)) {
              _tmpShared = null;
            } else {
              _tmpShared = _cursor.getInt(_cursorIndexOfShared);
            }
            _item = new Game(_tmpDeveloper,_tmpGame_url,_tmpFreetogame_profile_url,_tmpGenre,_tmpPlatform,_tmpPublisher,_tmpRelease_date,_tmpId,_tmpTitle,_tmpShort_description,_tmpThumbnail,_tmpRating,_tmpIsFavorite,_tmpIsPlayed,_tmpIsShared,_tmpPlayed,_tmpFavorited,_tmpShared);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Game>> getAllNotPlayed() {
    final String _sql = "SELECT * FROM items WHERE isPlayed = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"items"}, new Callable<List<Game>>() {
      @Override
      public List<Game> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeveloper = CursorUtil.getColumnIndexOrThrow(_cursor, "developer");
          final int _cursorIndexOfGameUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "game_url");
          final int _cursorIndexOfFreetogameProfileUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "freetogame_profile_url");
          final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfShortDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "short_description");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlayed");
          final int _cursorIndexOfIsShared = CursorUtil.getColumnIndexOrThrow(_cursor, "isShared");
          final int _cursorIndexOfPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "played");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfShared = CursorUtil.getColumnIndexOrThrow(_cursor, "shared");
          final List<Game> _result = new ArrayList<Game>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Game _item;
            final String _tmpDeveloper;
            if (_cursor.isNull(_cursorIndexOfDeveloper)) {
              _tmpDeveloper = null;
            } else {
              _tmpDeveloper = _cursor.getString(_cursorIndexOfDeveloper);
            }
            final String _tmpGame_url;
            if (_cursor.isNull(_cursorIndexOfGameUrl)) {
              _tmpGame_url = null;
            } else {
              _tmpGame_url = _cursor.getString(_cursorIndexOfGameUrl);
            }
            final String _tmpFreetogame_profile_url;
            if (_cursor.isNull(_cursorIndexOfFreetogameProfileUrl)) {
              _tmpFreetogame_profile_url = null;
            } else {
              _tmpFreetogame_profile_url = _cursor.getString(_cursorIndexOfFreetogameProfileUrl);
            }
            final String _tmpGenre;
            if (_cursor.isNull(_cursorIndexOfGenre)) {
              _tmpGenre = null;
            } else {
              _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
            }
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpRelease_date;
            if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
              _tmpRelease_date = null;
            } else {
              _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpShort_description;
            if (_cursor.isNull(_cursorIndexOfShortDescription)) {
              _tmpShort_description = null;
            } else {
              _tmpShort_description = _cursor.getString(_cursorIndexOfShortDescription);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsPlayed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlayed);
            _tmpIsPlayed = _tmp_1 != 0;
            final boolean _tmpIsShared;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsShared);
            _tmpIsShared = _tmp_2 != 0;
            final Integer _tmpPlayed;
            if (_cursor.isNull(_cursorIndexOfPlayed)) {
              _tmpPlayed = null;
            } else {
              _tmpPlayed = _cursor.getInt(_cursorIndexOfPlayed);
            }
            final Integer _tmpFavorited;
            if (_cursor.isNull(_cursorIndexOfFavorited)) {
              _tmpFavorited = null;
            } else {
              _tmpFavorited = _cursor.getInt(_cursorIndexOfFavorited);
            }
            final Integer _tmpShared;
            if (_cursor.isNull(_cursorIndexOfShared)) {
              _tmpShared = null;
            } else {
              _tmpShared = _cursor.getInt(_cursorIndexOfShared);
            }
            _item = new Game(_tmpDeveloper,_tmpGame_url,_tmpFreetogame_profile_url,_tmpGenre,_tmpPlatform,_tmpPublisher,_tmpRelease_date,_tmpId,_tmpTitle,_tmpShort_description,_tmpThumbnail,_tmpRating,_tmpIsFavorite,_tmpIsPlayed,_tmpIsShared,_tmpPlayed,_tmpFavorited,_tmpShared);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Game>> getAllShared() {
    final String _sql = "SELECT * FROM items WHERE isShared = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[]{"items"}, new Callable<List<Game>>() {
      @Override
      public List<Game> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDeveloper = CursorUtil.getColumnIndexOrThrow(_cursor, "developer");
          final int _cursorIndexOfGameUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "game_url");
          final int _cursorIndexOfFreetogameProfileUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "freetogame_profile_url");
          final int _cursorIndexOfGenre = CursorUtil.getColumnIndexOrThrow(_cursor, "genre");
          final int _cursorIndexOfPlatform = CursorUtil.getColumnIndexOrThrow(_cursor, "platform");
          final int _cursorIndexOfPublisher = CursorUtil.getColumnIndexOrThrow(_cursor, "publisher");
          final int _cursorIndexOfReleaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "release_date");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfShortDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "short_description");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "isPlayed");
          final int _cursorIndexOfIsShared = CursorUtil.getColumnIndexOrThrow(_cursor, "isShared");
          final int _cursorIndexOfPlayed = CursorUtil.getColumnIndexOrThrow(_cursor, "played");
          final int _cursorIndexOfFavorited = CursorUtil.getColumnIndexOrThrow(_cursor, "favorited");
          final int _cursorIndexOfShared = CursorUtil.getColumnIndexOrThrow(_cursor, "shared");
          final List<Game> _result = new ArrayList<Game>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Game _item;
            final String _tmpDeveloper;
            if (_cursor.isNull(_cursorIndexOfDeveloper)) {
              _tmpDeveloper = null;
            } else {
              _tmpDeveloper = _cursor.getString(_cursorIndexOfDeveloper);
            }
            final String _tmpGame_url;
            if (_cursor.isNull(_cursorIndexOfGameUrl)) {
              _tmpGame_url = null;
            } else {
              _tmpGame_url = _cursor.getString(_cursorIndexOfGameUrl);
            }
            final String _tmpFreetogame_profile_url;
            if (_cursor.isNull(_cursorIndexOfFreetogameProfileUrl)) {
              _tmpFreetogame_profile_url = null;
            } else {
              _tmpFreetogame_profile_url = _cursor.getString(_cursorIndexOfFreetogameProfileUrl);
            }
            final String _tmpGenre;
            if (_cursor.isNull(_cursorIndexOfGenre)) {
              _tmpGenre = null;
            } else {
              _tmpGenre = _cursor.getString(_cursorIndexOfGenre);
            }
            final String _tmpPlatform;
            if (_cursor.isNull(_cursorIndexOfPlatform)) {
              _tmpPlatform = null;
            } else {
              _tmpPlatform = _cursor.getString(_cursorIndexOfPlatform);
            }
            final String _tmpPublisher;
            if (_cursor.isNull(_cursorIndexOfPublisher)) {
              _tmpPublisher = null;
            } else {
              _tmpPublisher = _cursor.getString(_cursorIndexOfPublisher);
            }
            final String _tmpRelease_date;
            if (_cursor.isNull(_cursorIndexOfReleaseDate)) {
              _tmpRelease_date = null;
            } else {
              _tmpRelease_date = _cursor.getString(_cursorIndexOfReleaseDate);
            }
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpShort_description;
            if (_cursor.isNull(_cursorIndexOfShortDescription)) {
              _tmpShort_description = null;
            } else {
              _tmpShort_description = _cursor.getString(_cursorIndexOfShortDescription);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final float _tmpRating;
            _tmpRating = _cursor.getFloat(_cursorIndexOfRating);
            final boolean _tmpIsFavorite;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp != 0;
            final boolean _tmpIsPlayed;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsPlayed);
            _tmpIsPlayed = _tmp_1 != 0;
            final boolean _tmpIsShared;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsShared);
            _tmpIsShared = _tmp_2 != 0;
            final Integer _tmpPlayed;
            if (_cursor.isNull(_cursorIndexOfPlayed)) {
              _tmpPlayed = null;
            } else {
              _tmpPlayed = _cursor.getInt(_cursorIndexOfPlayed);
            }
            final Integer _tmpFavorited;
            if (_cursor.isNull(_cursorIndexOfFavorited)) {
              _tmpFavorited = null;
            } else {
              _tmpFavorited = _cursor.getInt(_cursorIndexOfFavorited);
            }
            final Integer _tmpShared;
            if (_cursor.isNull(_cursorIndexOfShared)) {
              _tmpShared = null;
            } else {
              _tmpShared = _cursor.getInt(_cursorIndexOfShared);
            }
            _item = new Game(_tmpDeveloper,_tmpGame_url,_tmpFreetogame_profile_url,_tmpGenre,_tmpPlatform,_tmpPublisher,_tmpRelease_date,_tmpId,_tmpTitle,_tmpShort_description,_tmpThumbnail,_tmpRating,_tmpIsFavorite,_tmpIsPlayed,_tmpIsShared,_tmpPlayed,_tmpFavorited,_tmpShared);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
