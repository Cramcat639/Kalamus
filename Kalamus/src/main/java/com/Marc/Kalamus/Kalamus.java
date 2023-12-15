
package com.Marc.Kalamus;
import com.Marc.Kalamus.GUI.GUI;
import com.Marc.Kalamus.Modelo.ExepcioAtribut;
import com.Marc.Kalamus.Modelo.ExepcioHabitants;
import com.Marc.Kalamus.Persistencia.KalamusDB;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class Kalamus {

    public static void main(String[] args) throws ExepcioComanda, IOException, ExepcioHabitants, ExepcioAtribut, SQLException {
        KalamusDB kalamusDB=new KalamusDB();
        kalamusDB.inicialitzar();
        kalamusDB.lectura("planet");
        kalamusDB.lectura("being");
        if(args.length ==1){
            GUI finestra= new GUI(kalamusDB);
            finestra.setVisible(true);
  
        }else{
            String comanda = args[1]+" "+args[2];
            switch(comanda.toLowerCase()) {                                    //defineixo la primera paraula per entrar en els diferents case i faig que ho llegeixi tot en minuscules per evitar erros
                  case "planet add":
                      try{
                      kalamusDB.escripturaPlaneta(args);
                      }catch(Exception ex){
                          System.out.println(ex.getMessage());
                      }
                      break;

                  case "being add":
                      try{
                      kalamusDB.escripturaEsser(args);
                      }catch(Exception ex){
                          System.out.println(ex.getMessage());
                      }
                      break;

                  case "planet list":
                      ArrayList <String> llistaPlanetas = kalamusDB.infoPlanetas();
                      for(int i =0; i<llistaPlanetas.size(); i ++){
                          System.out.println(llistaPlanetas.get(i));
                      }
                      break;

                  case "being list":
                       ArrayList <String> llistaEssers = kalamusDB.infoEssers();
                       for(int i =0; i<llistaEssers.size(); i ++){
                           System.out.println(llistaEssers.get(i));
                       }
                      break;

                  default:
                        throw new ExepcioComanda();
          }
        }
    }
}