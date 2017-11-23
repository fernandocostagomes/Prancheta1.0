package br.com.fgomes.prancheta10.activities;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.fgomes.prancheta10.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NewPlayerFragment extends Fragment {

   public NewPlayerFragment() {
   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_new_player, container, false);
   }
}
