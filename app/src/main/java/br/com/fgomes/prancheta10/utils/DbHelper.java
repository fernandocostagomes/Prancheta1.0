package br.com.fgomes.prancheta10.utils;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import java.util.*;
import br.com.fgomes.prancheta10.objects.*;

/**
 * Created by fernando.gomes on 10/03/2017.
 */
public class DbHelper extends SQLiteOpenHelper
{
   /**
    * Constante com o nome do banco de Dados.
    */
   private static final String NOME_BANCO = "dbprancheta";

   /**
    * Constante com a versão do Banco de Dados.
    */
   private static final int VERSAO_BASE = 1;

   /**
    * Array de string com as querys para criar as tabelas do banco de dados.
    */
   private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
           "CREATE TABLE Player_PLA(PLAYER_TXT_Name VARCHAR(30) NOT NULL, PLAYER_TXT_Phone VARCHAR(14) NOT NULL, PLAYER_TXT_Email VARCHAR(40));"
   };

   /**
    * Construtor padrão que recebe um contexto.
    *
    * @param p_context
    */
   public DbHelper( Context p_context )
   {
      super( p_context, NOME_BANCO, null, VERSAO_BASE );
   }

   /**
    * Metódo onCreate para criar as tabelas no banco de dados.
    */
   @Override
   public void onCreate( SQLiteDatabase p_db )
   {
      String SqlCreateTablePlayer = "CREATE TABLE Player_PLA(PLAYER_INT_Id_Player INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, PLAYER_TXT_Name VARCHAR(30) NOT NULL, PLAYER_TXT_Phone VARCHAR(14) NOT NULL, PLAYER_TXT_Email VARCHAR(40));";
      p_db.execSQL( SqlCreateTablePlayer );
      String SqlCreateTableGame = "CREATE TABLE Game_GAM (GAME_INT_Id_Game INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, GAME_TXT_Data CHAR NOT NULL, GAME_INT_Num_Players INT NOT NULL, GAME_DOU_Amount_Collected DOUBLE NOT NULL, GAME_INT_Number_Sets INT NOT NULL, GAME_TXT_Local CHAR NOT NULL, GAME_INT_Status_Game INT NOT NULL)";
      p_db.execSQL( SqlCreateTableGame );
      String SqlCreateTableGamePlayer = "CREATE TABLE GamePlayer_GAMPLA(GAMPLA_INT_Id_Game INTEGER NOT NULL, GAMPLA_INT_Id_Player INTEGER NOT NULL)";
      p_db.execSQL( SqlCreateTableGamePlayer );
   }

   /**
    * Metódo que atualiza o banco de dados.
    */
   @Override
   public void onUpgrade( SQLiteDatabase p_db, int p_oldVersion, int p_newVersion )
   {
      String SqlDropTablePlayer_PLA = "DROP TABLE AppInfo_APP";
      p_db.execSQL( SqlDropTablePlayer_PLA );
      onCreate( p_db );
   }

   /**
    * Insert a play on the db.
    *
    * @param p_player .
    */
   public void insertPlay( Player p_player )
   {
      SQLiteDatabase db = getWritableDatabase();

      ContentValues cv = new ContentValues();

      cv.put( "PLAYER_TXT_Name", p_player.getM_PLAYER_TXT_Name().toString() );
      cv.put( "PLAYER_TXT_Phone", p_player.getM_PLAYER_TXT_Phone().toString() );
      cv.put( "PLAYER_TXT_Email", p_player.getM_PLAY_TXT_Email().toString() );

      db.insert( "Player_PLA", null, cv );

      db.close();
   }

   /**
    * Select a player.
    *
    * @param p_player
    *
    * @return result with name.
    */
   public String selectPlayer( String p_player )
   {
      String player = "";

      SQLiteDatabase db = getReadableDatabase();

      String SqlSelectOnePlayer = "SELECT * FROM Player_PLA WHERE PLAYER_TXT_Name = '"
              + p_player + "';";

      Cursor c = db.rawQuery( SqlSelectOnePlayer, null );

      if ( c.moveToFirst() )
      {
         player = c.getString( 0 );
      }
      else
      {
         player = "no";
      }
      c.close();

      return player;
   }

   /*
    * Metódo da lista de objetos App.
    *
    * @return listAppInfo retorna a lista de objetos AppInfo.
    */
   public List<Player> selectAllPlayer()
   {
      List<Player> listPlayer = new ArrayList<Player>();

      SQLiteDatabase db = getReadableDatabase();

      String SqlSelectPlayer = "SELECT * FROM Player_PLA";

      Cursor c = db.rawQuery( SqlSelectPlayer, null );

      if ( c.moveToFirst() )
      {
         do
         {
            Player player = new Player();

            player.setM_PLAYER_INT_Id_Player( c.getString( 0 ) );
            player.setM_PLAYER_TXT_Name( c.getString( 1 ) );
            player.setM_PLAYER_TXT_Phone( c.getString( 2 ) );
            player.setM_PLAYER_TXT_Email( c.getString( 3 ) );


            listPlayer.add( player );
         } while ( c.moveToNext() );
      }
      db.close();

      return listPlayer;
   }

   /**
    * Alter values of Player
    *
    * @param p_namePlayer
    *
    * @param p_phonePlayer
    *
    * @param p_emailPlayer
    */
   public void updatePassword( String p_namePlayer, String p_phonePlayer, String p_emailPlayer )
   {
      SQLiteDatabase db = getWritableDatabase();
      String SqlUpdateValuesPlayer = "UPDATE Player_PLA set PLAYER_TXT_Name = '" + p_namePlayer + "' , PLAYER_TXT_Phone = '" + p_phonePlayer + "' , PLAYER_TXT_Name = '" + p_namePlayer + "' ;";
      db.execSQL( SqlUpdateValuesPlayer );
      db.close();
   }

   /**
    * Método que altera a permissão de todos os Apps de uma só vez.
    *
    * @param p_valuePermission valor para qual quer alterar 0;1;
    *
    * @return resultUpdateAllPermissionApp booleando para caso true: sucesso ou false: erro.
    */
   public boolean updateAllAppPermission( String p_valuePermission )
   {
      boolean resultUpdateAllPermissionApp = false;
      SQLiteDatabase db = getWritableDatabase();

      try
      {
         String SqlUpdateAllPermissionApp = "UPDATE AppInfo_APP set APP_TXT_Permission = '" + p_valuePermission + "';";

         db.execSQL( SqlUpdateAllPermissionApp );

         db.close();
         resultUpdateAllPermissionApp = true;
      }
      catch ( Exception e )
      {
         e.printStackTrace();
      }
      return resultUpdateAllPermissionApp;
   }

   /**
    * Method who found status lastGame.
    */
   public boolean selectStatusLastGame()
   {
      boolean result = false;

      int result_int = 0;

      SQLiteDatabase db = getReadableDatabase();

      String SqlSelectOnePlayer = "SELECT * FROM Game_GAM WHERE GAME_INT_Id_Game = " + "(SELECT MAX (GAME_INT_Id_Game) FROM Game_GAM)";


      Cursor c = db.rawQuery( SqlSelectOnePlayer, null );

      if ( c.moveToFirst() )
      {
         result_int = c.getInt( 6 );
      }
      else
      {
         result_int = 2;
      }
      c.close();

      switch (result_int) {
         case DbConst.DbStatusValues.PARAM_STATUS_GAME_OPEN:

            result = true;
            break;
         default:
            result = false;
            break;
      }
      return result;
   }

   public int idLastGame()
   {
      int result = 0;

      SQLiteDatabase db = getReadableDatabase();

      String SqlSelect = "select max(game_int_id_game) from Game_gam";

      Cursor c = db.rawQuery( SqlSelect, null );

      if ( c.moveToFirst() )
      {
         result = c.getInt( 0 );
      }
      else
      {
         result = 0;
      }
      c.close();
      return result;
   }


   /**
    * Metódo que faz um limpa nas tabelas do banco de dados.
    *
    * @return true / false.
    */
   public boolean dropDB()
   {
      boolean confirmDeleteTable = false;
      try
      {
         SQLiteDatabase db = getWritableDatabase();

         String SqlDropDbAppInfo = "DELETE FROM AppInfo_APP";
         String SqlUpdatePassword = "UPDATE Password_PSW set PSW_TXT_password  = 'blacklauncher2016';";
         String SqlUpdateOldPassword = "UPDATE Password_PSW set PSW_TXT_OldPassword  = 'blacklauncher2016';";

         db.execSQL( SqlDropDbAppInfo );
         db.execSQL( SqlUpdatePassword );
         db.execSQL( SqlUpdateOldPassword );

         db.close();
         confirmDeleteTable = true;
      }
      catch ( Exception p_e )
      {
         p_e.printStackTrace();
      }
      return confirmDeleteTable;
   }

   /**
    * Method -  insert a player in the game
    */
   public boolean insertPlayerGame(int p_id_player, int p_id_game)
   {
      boolean result = false;
      try {
         SQLiteDatabase db = getWritableDatabase();
         ContentValues cv = new ContentValues();
         cv.put("GAMPLA_INT_Id_Player", p_id_player);
         cv.put("GAMPLA_INT_Id_Game", p_id_game);
         db.insert("GamePlayer_GAMPLA", null, cv);
         db.close();
         result = true;
      }
      catch(Exception p_e)
      {
         p_e.printStackTrace();
      }
      return result;
   }
}