package br.com.fgomes.prancheta10.activities;

import android.content.*;
import android.os.Bundle;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;
import java.util.*;

import br.com.fgomes.prancheta10.*;
import br.com.fgomes.prancheta10.objects.*;
import br.com.fgomes.prancheta10.utils.*;

public class NewGame extends AppCompatActivity {

   private Spinner m_spAddPlayers;
   private DbHelper m_dbHelper;
   ArrayList<String> m_array_string_name_player;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_new_game);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
      fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(), NewPlayer.class);
            startActivity(i);
         }
      });

      //Create the bd.
      m_dbHelper = new DbHelper(this);

      if(checkLastGameStatusOpen() == true)
      {
         Toast.makeText(getApplicationContext(), "Existe uma pelada aberta.", Toast.LENGTH_SHORT).show();

         //Select the player added.

         //Select name.

         //Select Amount

         //Select the local.

         //
      }
      else
      {
         //New Game



      }




      //Spinner for add players for game.
      m_spAddPlayers = (Spinner) findViewById(R.id.spAddPlayers);
      ArrayList<Player> array_players = new ArrayList<Player>(m_dbHelper.selectAllPlayer());

      m_array_string_name_player = new ArrayList<String>();
      for (Player ap : array_players )
      {
         m_array_string_name_player.add(ap.getM_PLAYER_TXT_Name());
      }

      //Load spinner with players assigned in the bd.
      ArrayAdapter<String> adapterSerialize =
              new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, m_array_string_name_player );
      adapterSerialize.setDropDownViewResource( android.R.layout.simple_spinner_item );
      m_spAddPlayers.setAdapter( adapterSerialize );
      m_spAddPlayers.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener()
      {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
         {
            //Snackbar.make(view, "Adicionado", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            if(m_dbHelper.insertPlayerGame(position, 1))
            {
               Snackbar.make(view, "Adicionado com sucesso!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            else
            {
               Snackbar.make(view, "Erro ao adicionar jogador na pelada!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }

         }
         @Override
         public void onNothingSelected( AdapterView<?> parent )
         {

         }
      });
   }

   /**
    * Method check was have last game is status in open.
    * @return false case status lastgame is false; true case status lastgame is true.
    */
   private boolean checkLastGameStatusOpen()
   {
      return m_dbHelper.selectStatusLastGame();
   }


}
