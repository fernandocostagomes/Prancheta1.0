package br.com.fgomes.prancheta10.objects;

/**
 * Created by fernando.gomes on 10/03/2017.
 */
public class Player {
   /** Name of player. */
   public String m_PLAYER_INT_Id_Player;
   /** Name of player. */
   public String m_PLAYER_TXT_Name;
   /** Phone of player. */
   public String m_PLAYER_TXT_Phone;
   /** Email of player */
   public String m_PLAYER_TXT_Email;

   public String getM_PLAYER_INT_Id_Player() {
      return m_PLAYER_INT_Id_Player;
   }
   public void setM_PLAYER_INT_Id_Player(String m_PLAYER_INT_Id_Player) {
      this.m_PLAYER_INT_Id_Player = m_PLAYER_INT_Id_Player;
   }

   public String getM_PLAYER_TXT_Name() {
      return m_PLAYER_TXT_Name;
   }
   public void setM_PLAYER_TXT_Name(String m_PLAYER_TXT_Name) {
      this.m_PLAYER_TXT_Name = m_PLAYER_TXT_Name;
   }

   public String getM_PLAYER_TXT_Phone() {
      return m_PLAYER_TXT_Phone;
   }
   public void setM_PLAYER_TXT_Phone(String m_PLAYER_TXT_Phone) {
      this.m_PLAYER_TXT_Phone = m_PLAYER_TXT_Phone;
   }

   public String getM_PLAY_TXT_Email() {
      return m_PLAYER_TXT_Email;
   }
   public void setM_PLAYER_TXT_Email(String m_PLAYER_TXT_Email) {
      this.m_PLAYER_TXT_Email = m_PLAYER_TXT_Email;
   }
}
