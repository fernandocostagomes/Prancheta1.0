package br.com.fgomes.prancheta10.activities;

import android.app.*;
import android.content.*;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.*;

import br.com.fgomes.prancheta10.*;
import br.com.fgomes.prancheta10.objects.*;
import br.com.fgomes.prancheta10.utils.*;

import static br.com.fgomes.prancheta10.R.id.fab;


public class NewPlayer extends AppCompatActivity {

   private EditText m_namePlayer;
   private EditText m_phonePlayer;
   private EditText m_emailPlayer;
   private Button m_btSaveNewPlayer;
   FloatingActionButton m_fab;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_new_player);

      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);



      m_fab = (FloatingActionButton) findViewById(fab);
      m_fab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                  .setAction("Action", null).show();
         }
      });

      m_namePlayer = (EditText) findViewById(R.id.etNamePlayer);
      m_phonePlayer = (EditText) findViewById(R.id.etPhonePlayer);
      m_emailPlayer = (EditText) findViewById(R.id.etEmailPlayer);
      m_btSaveNewPlayer = (Button) findViewById(R.id.btSave);
   }


   public void evtSaveNewPlayer(View p_view) {
      m_btSaveNewPlayer.setEnabled(false);
      String name = m_namePlayer.getText().toString();
      String phone = m_phonePlayer.getText().toString();
      String email = m_emailPlayer.getText().toString();


      if (name.equals("") || phone.equals(""))
      {
         Snackbar.make(p_view, "Por favor preencher os campos!", Snackbar.LENGTH_LONG)
                 .setAction("Action", null).show();
         m_btSaveNewPlayer.setEnabled(true);
      }
      else
      {
         Player player = new Player();
         player.setM_PLAYER_TXT_Name(name);
         player.setM_PLAYER_TXT_Phone(phone);
         player.setM_PLAYER_TXT_Email(email);

         DbHelper db = new DbHelper(this);
         try {
            db.insertPlay(player);
            m_btSaveNewPlayer.setEnabled(true);

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(NewPlayer.this);
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setTitle(R.string.title_activity_new_player);
            alertDialog.setMessage("Deseja cadastrar outro jogador?");
            alertDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                  m_namePlayer.requestFocus();
                  m_namePlayer.setText("");
                  m_phonePlayer.setText("");
                  m_emailPlayer.setText("");
               }
            });
            alertDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface p_dialog, int p_which) {
                  p_dialog.dismiss();

                  Intent i = new Intent(getApplicationContext(), MainActivity.class);
                  startActivity(i);
               }
            });
            alertDialog.show();
         } catch (Exception p_e) {
            p_e.printStackTrace();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
         }
      }
   }

}
