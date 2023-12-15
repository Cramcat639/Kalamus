
package com.Marc.Kalamus.Persistencia;
import com.Marc.Kalamus.Modelo.ExepcioAtribut;
import com.Marc.Kalamus.Modelo.ExepcioHabitants;
import com.Marc.Kalamus.Modelo.ExepcioPlaneta;
import com.Marc.Kalamus.Modelo.Andorian;
import com.Marc.Kalamus.Modelo.Esser;
import com.Marc.Kalamus.Modelo.ExepcioNom;
import com.Marc.Kalamus.Modelo.Human;
import com.Marc.Kalamus.Modelo.Ferengi;
import com.Marc.Kalamus.Modelo.Klingon;
import com.Marc.Kalamus.Modelo.Nibirian;
import com.Marc.Kalamus.Modelo.Planeta;
import com.Marc.Kalamus.Modelo.Vulcanian;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;


public class KalamusDB {
    private ArrayList <Esser> essers;
    private ArrayList <Planeta> planetas;
    private File carpeta;
    private File config;
    private File planetsCSV;
    private File beingsCSV;
    private Properties properties;
    private Connection conexio;

    public KalamusDB() {
        this.essers = new ArrayList <>();
        this.planetas = new ArrayList <>();
        this.properties = new Properties();
    }
    public void inicialitzar() throws IOException, SQLException{
        carpeta= new File(System.getProperty("user.home")+File.separator+".kalamus");
        config = new File(System.getProperty("user.home")+File.separator+".kalamus"+File.separator+ "config.properties");
        planetsCSV= new File(System.getProperty("user.home")+File.separator+".kalamus"+File.separator+"planets.csv");
        beingsCSV= new File(System.getProperty("user.home")+File.separator+".kalamus"+File.separator+"beings.csv");
        if (!carpeta.exists()){
            try{
                carpeta.mkdir();
                config.createNewFile();
                planetsCSV.createNewFile();
                beingsCSV.createNewFile();
                FileWriter filewriter;
                properties.setProperty("opcio", "fitxers");
                properties.setProperty("pathFitxers", "");
                properties.setProperty("db.url", "");
                properties.setProperty("db.user", "");
                properties.setProperty("db.password", "");
                properties.setProperty("db.schema", "");
                filewriter = new FileWriter(config);
                properties.store(filewriter, "opcio = fitxers: mode fitxers / opcio = sql: mode base de dades.");
                filewriter.close();
            }catch(Exception ex){
                System.out.println("No s'ha creat la carpeta");
                ex.printStackTrace();
                exit(1);
            }
        }else{
            FileReader filereader;
            try {
                filereader = new FileReader(config);
                properties.load(filereader);
                filereader.close();
            } catch (FileNotFoundException ex) {
                System.out.println("No s'ha trobat el fitxer");
            }
        }
         if (properties.getProperty("opcio").equalsIgnoreCase("sql")){
             conectar();
         }
    }
    private Planeta donamPlaneta(String nom) throws ExepcioPlaneta{
        for(int i = 0; i <planetas.size(); i++){
            if(nom.equalsIgnoreCase(planetas.get(i).getNom())){
                return planetas.get(i);
            }
        }
        throw new ExepcioPlaneta();
    }
    private boolean hiHaEspecie(String especie, String planeta){
        for(int i = 0; i <essers.size(); i ++){
            if(especie.equalsIgnoreCase(essers.get(i).getEspecie()) && planeta.equalsIgnoreCase(essers.get(i).getPlaneta())){
                return true;
            }
        }
        return false;
    }
    
    private Integer nombreHabitants(String planeta){
        Integer count=0;
        for(int i = 0; i <essers.size(); i ++){
            if(planeta.equalsIgnoreCase(essers.get(i).getPlaneta())){
                count++;
            }
        }
        return count;
    }
    
    private boolean buscarNom(String esser){
        for(int i= 0; i < essers.size(); i ++){
            if(essers.get(i).getNom().equalsIgnoreCase(esser)){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<String>infoPlanetas(){
        ArrayList<String>info = new ArrayList<>();
        for(int i = 0; i <planetas.size(); i++){
            info.add("Nom: "+planetas.get(i).getNom());
            info.add("Galaxia: "+planetas.get(i).getGalaxia());
            info.add("Capacitat Maxima: "+planetas.get(i).getCapacitatMax());
            info.add("Clima: "+planetas.get(i).getClima());
            info.add("Flora Vermella: "+(planetas.get(i).isFloraVermella() ? "Si":"No"));
            info.add("Fauna Acuatica: "+(planetas.get(i).isEssersAcuatics() ? "Si":"No")+"\n");
        }
        return info;
    }
    
    public ArrayList<String>infoEssers(){
        ArrayList<String>info = new ArrayList<>();
        for(int i =0; i <essers.size(); i++){
            info.add("Nom: "+essers.get(i).getNom());
            info.add("Espècie: "+essers.get(i).getEspecie());
            info.add("Planeta: "+essers.get(i).getPlaneta());
            info.add("Civilitzacio: "+essers.get(i).getCategoria());
            if(essers.get(i) instanceof Human){
                Human huma = (Human)essers.get(i);
                info.add("Edat: "+huma.getEdat());
                info.add("Genere: "+huma.getGenere()+"\n");
            }
            else if(essers.get(i) instanceof Ferengi){
                 Ferengi ferengi = (Ferengi)essers.get(i);
                 info.add("Lantanium: "+ ferengi.getLantanium()+"\n");
            }
            else if(essers.get(i) instanceof Andorian){
                 Andorian andorian = (Andorian) essers.get(i);
                 info.add("Rang: "+ andorian.getRang());
                 info.add("Es Aenar? "+ (andorian.isAenar() ? "Si":"No")+"\n");
            }
            else if(essers.get(i) instanceof Klingon){
                 Klingon klingon = (Klingon) essers.get(i);
                 info.add("Força: "+ klingon.getForça()+"\n");
            }
            else if(essers.get(i) instanceof Nibirian){
                 Nibirian nibirian = (Nibirian) essers.get(i);
                 info.add("Vegetaria: "+ (nibirian.isVegetaria() ?"Si":"No")+"\n");
            }
            else if(essers.get(i) instanceof Vulcanian){
                 Vulcanian vulcanian = (Vulcanian) essers.get(i);
                 info.add("Nivell de Meditació: "+ vulcanian.getMeditacio()+"\n");
            }
            info.add("\n");
    }
        return info;
    }
    
    public void lectura(String text) throws IOException, ExepcioAtribut, SQLException{
        if (properties.getProperty("opcio").equalsIgnoreCase("fitxers")){
            BufferedReader lectura;
            String linia;
            if (text.equalsIgnoreCase("planet")){
                lectura = new BufferedReader(new FileReader(planetsCSV));
                while ((linia = lectura.readLine()) != null) {
                    String[] llista = linia.split(",");
                    String nom= llista[0];
                    String galaxia=llista[1];
                    Integer capacitatMax= Integer.parseInt(llista[2]);
                    String clima=llista[3];
                    boolean floraVermella= "yes".equalsIgnoreCase(llista[4]);
                    boolean essersAcuatics="yes".equalsIgnoreCase(llista[5]);
                    Planeta nouPlaneta= new Planeta (nom,galaxia,capacitatMax,clima,floraVermella,essersAcuatics);
                    planetas.add(nouPlaneta);
                }
             lectura.close();
            }
            else if (text.equalsIgnoreCase("being")){
                lectura = new BufferedReader(new FileReader(beingsCSV));
                Esser nouEsser;
                while ((linia = lectura.readLine()) != null) {
                    String[] llista = linia.split(",");
                    String nom=llista[0];
                    String especie=llista[1];
                    String planeta=llista[2];
                    switch(especie.toLowerCase()){
                        case "huma":
                            Integer edat =Integer.parseInt(llista[3]);
                            String genere = llista[4];
                            nouEsser= new Human(nom,especie,planeta,edat,genere);
                            essers.add(nouEsser);
                            break;

                         case "ferengi":
                             Float lantanium =Float.parseFloat(llista[3]);
                             nouEsser= new Ferengi(nom,especie,planeta,lantanium);
                             essers.add(nouEsser);
                             break;

                         case "andorian":
                             String rang= llista[3];
                             boolean aenar=Boolean.parseBoolean(llista[4]);
                             nouEsser= new Andorian(nom,especie,planeta,rang,aenar);
                             essers.add(nouEsser);
                             break;

                         case "klingon":
                             Integer força =Integer.parseInt(llista[3]);
                             nouEsser = new Klingon(nom,especie,planeta,força);
                             essers.add(nouEsser);
                             break;

                         case "vulcanian":
                             Integer meditacio = Integer.parseInt(llista[3]);
                             nouEsser = new Vulcanian(nom,especie,planeta,meditacio);
                             essers.add(nouEsser);
                             break;

                         case "nibirian":
                             boolean vegetaria ="yes".equalsIgnoreCase(llista[3]);
                             nouEsser = new Nibirian(nom,especie,planeta,vegetaria);
                             essers.add(nouEsser);
                             break;
                    }
                }
             lectura.close();
            }
        }
        else if (properties.getProperty("opcio").equalsIgnoreCase("sql")){
            if (text.equalsIgnoreCase("planet")){
                consultarPlanetas();
            }
            else if (text.equalsIgnoreCase("being")){
                consultarEssers();
            }
        }
    }
    public void escripturaEsser(String[] args) throws IOException, ExepcioHabitants, ExepcioNom, ExepcioAtribut, SQLException{
        BufferedWriter escriptura;
        String nom=args[3];
        String especie=args[4];
        String planeta=args[5];
        Planeta planetaTrobat = null;
        try {
          planetaTrobat = donamPlaneta(planeta);
        } catch (ExepcioPlaneta ex) {
            System.out.println(ex.getMessage());
            exit(1);
        }
        if(nombreHabitants(planeta)>=planetaTrobat.getCapacitatMax()){
            throw new ExepcioHabitants();
        }
        if(buscarNom(nom)){
            throw new ExepcioNom();
        }
        
         switch(especie.toLowerCase()){
                case "huma":
                    Integer edat =Integer.parseInt(args[6]);
                    String genere = args[7];
                    if(edat <0 || edat >130){
                    throw new ExepcioAtribut("Error: Edat incorrecte");
                    }
                    if (properties.getProperty("opcio").equalsIgnoreCase("fitxers")){
                        escriptura = new BufferedWriter(new FileWriter(beingsCSV,true));
                        escriptura.write(nom+","+especie+","+planeta+","+edat+","+genere+","+System.lineSeparator());
                        escriptura.close();
                    }
                    else if (properties.getProperty("opcio").equalsIgnoreCase("sql")){
                        String query = "insert into human (nom,planeta,edat,genere) values(?,?,?,?)";
                        PreparedStatement ps=conexio.prepareStatement(query);
                        ps.setString(1, nom);
                        ps.setString(2, planeta);
                        ps.setInt(3, edat);
                        ps.setString(4, genere);
                        ps.executeUpdate();
                        ps.close();
                    }
                    Human human = new Human(nom,especie,planeta,edat,genere);
                    essers.add(human);
                    break;

                 case "ferengi":
                     Float lantanium =Float.parseFloat(args[6]);
                     if(lantanium <0){
                        throw new ExepcioAtribut("Error: l'or no pot ser negatiu.");
                    }
                     if(!planetaTrobat.getClima().equalsIgnoreCase("fred")){
                        if (properties.getProperty("opcio").equalsIgnoreCase("fitxers")){
                            escriptura = new BufferedWriter(new FileWriter(beingsCSV,true));
                            escriptura.write(nom+","+especie+","+planeta+","+lantanium+","+System.lineSeparator());
                            escriptura.close();
                      }
                    else if (properties.getProperty("opcio").equalsIgnoreCase("sql")){
                        String query = "insert into ferengi (nom,planeta,lantanium) values(?,?,?)";
                        PreparedStatement ps=conexio.prepareStatement(query);
                        ps.setString(1, nom);
                        ps.setString(2, planeta);
                        ps.setFloat(3, lantanium);
                        ps.executeUpdate();
                        ps.close();
                    }
                        Ferengi ferengi = new Ferengi(nom,especie,planeta,lantanium);
                        essers.add(ferengi);
                        
                     }else{
                        System.out.println("Error: Aquest esser no pot viure en planetes freds");
                        }
                     break;

                 case "andorian":
                     String rang= args[6];
                     boolean aenar=Boolean.parseBoolean(args[7]);
                      if(!Andorian.POSSIBLES_RANGS.contains(rang)){
                      throw new ExepcioAtribut("Error: Aquest rang no existeix.");
                     }
                       if(hiHaEspecie("vulcanian", planeta)){
                            System.out.println("Error: No es pot registrar en aquest planeta perque ja hi viu un Vulcanian.");

                       }else{
                           if (properties.getProperty("opcio").equalsIgnoreCase("fitxers")){
                            escriptura = new BufferedWriter(new FileWriter(beingsCSV,true));
                            escriptura.write(nom+","+especie+","+planeta+","+rang+","+aenar+","+System.lineSeparator());
                            escriptura.close();
                           }
                           else if (properties.getProperty("opcio").equalsIgnoreCase("sql")){
                                String query = "insert into andorian (nom,planeta,rang,aenar) values(?,?,?,?)";
                                PreparedStatement ps=conexio.prepareStatement(query);
                                ps.setString(1, nom);
                                ps.setString(2, planeta);
                                ps.setString(3, rang);
                                ps.setBoolean(4, aenar);
                                ps.executeUpdate();
                                ps.close();
                           }
                           Andorian andorian = new Andorian(nom,especie,planeta,rang,aenar);
                           essers.add(andorian);
                       }
                     break;

                 case "klingon":
                     Integer força =Integer.parseInt(args[6]);
                     if(força < 50 || força >350){
                     throw new ExepcioAtribut("Error: El nivell de força no es correcte.");
                     }
                     if(!planetaTrobat.getClima().equalsIgnoreCase("calid")){
                        if (properties.getProperty("opcio").equalsIgnoreCase("fitxers")){
                            escriptura = new BufferedWriter(new FileWriter(beingsCSV,true));
                            escriptura.write(nom+","+especie+","+planeta+","+força+","+System.lineSeparator());
                            escriptura.close();
                    }
                     else if (properties.getProperty("opcio").equalsIgnoreCase("sql")){
                        String query = "insert into klingon (nom,planeta,força) values(?,?,?)";
                        PreparedStatement ps=conexio.prepareStatement(query);
                        ps.setString(1, nom);
                        ps.setString(2, planeta);
                        ps.setInt(3, força);
                        ps.executeUpdate();
                        ps.close();
                    }
                        Klingon klingon = new Klingon(nom,especie,planeta,força);
                        essers.add(klingon);
                     }else{
                        System.out.println("Error: Aquest esser no pot viure en planetes de clima càlid.");
                    }
                     break;

                 case "vulcanian":
                     Integer meditacio = Integer.parseInt(args[6]);
                     if(meditacio <0 || meditacio >10){
                     throw new ExepcioAtribut("Error: nivell de meditació incorrecte.");
                     }
                     if(hiHaEspecie("andorian", planeta)){
                        System.out.println("Error: No es pot registrar en aquest planeta perque ja hi viu un Andorian.");
                        
                      }else{
                          if (properties.getProperty("opcio").equalsIgnoreCase("fitxers")){
                            escriptura = new BufferedWriter(new FileWriter(beingsCSV,true));
                            escriptura.write(nom+","+especie+","+planeta+","+meditacio+","+System.lineSeparator());
                            escriptura.close();
                        }
                           else if (properties.getProperty("opcio").equalsIgnoreCase("sql")){
                                String query = "insert into vulcanian (nom,planeta,meditacio) values(?,?,?)";
                                PreparedStatement ps=conexio.prepareStatement(query);
                                ps.setString(1, nom);
                                ps.setString(2, planeta);
                                ps.setInt(3, meditacio);
                                ps.executeUpdate();
                                ps.close();
                           }
                          Vulcanian vulcanian = new Vulcanian(nom,especie,planeta,meditacio);
                          essers.add(vulcanian);
                     }
                     break;

                 case "nibirian":
                     boolean vegetaria = "yes".equalsIgnoreCase(args[6]);
                     if((planetaTrobat.isEssersAcuatics() && !vegetaria) || (planetaTrobat.isFloraVermella() && vegetaria)){
                        if (properties.getProperty("opcio").equalsIgnoreCase("fitxers")){
                            escriptura = new BufferedWriter(new FileWriter(beingsCSV,true));
                            escriptura.write(nom+","+especie+","+planeta+","+vegetaria+","+System.lineSeparator());
                            escriptura.close();
                     }
                      else if (properties.getProperty("opcio").equalsIgnoreCase("sql")){
                        String query = "insert into nibirian (nom,planeta,vegetaria) values(?,?,?)";
                        PreparedStatement ps=conexio.prepareStatement(query);
                        ps.setString(1, nom);
                        ps.setString(2, planeta);
                        ps.setBoolean(3, vegetaria);
                        ps.executeUpdate();
                        ps.close();
                      } 
                        Nibirian nibirian = new Nibirian(nom,especie,planeta,vegetaria);
                        essers.add(nibirian);
                     }else{
                            System.out.println("Error: Aquest esser no pot viure en aquest planeta perque no disposa d'aliment");
                     }
                     break;
         }
    }
    
    public void escripturaPlaneta(String[] args) throws IOException, ExepcioHabitants, ExepcioNom, ExepcioAtribut, SQLException{
        if (properties.getProperty("opcio").equalsIgnoreCase("fitxers")){
            BufferedWriter escriptura;
            escriptura = new BufferedWriter(new FileWriter(planetsCSV,true));
            String nom=args[3];
            String galaxia=args[4];
            Integer capacitatMax= Integer.parseInt(args[5]);
            String clima=args[6];
            boolean floraVermella= "yes".equalsIgnoreCase(args[7]);
            boolean essersAcuatics="yes".equalsIgnoreCase(args[8]);
            escriptura.write(nom+","+galaxia+","+capacitatMax+","+clima+","+floraVermella+","+essersAcuatics+","+System.lineSeparator());
            escriptura.close();
            Planeta planeta = new Planeta(nom,galaxia,capacitatMax,clima,floraVermella,essersAcuatics);
            planetas.add(planeta);
        }
        else if (properties.getProperty("opcio").equalsIgnoreCase("sql")){
                String nom=args[3];
                String galaxia=args[4];
                Integer capacitatMax= Integer.parseInt(args[5]);
                String clima=args[6];
                boolean floraVermella= "yes".equalsIgnoreCase(args[7]);
                boolean essersAcuatics="yes".equalsIgnoreCase(args[8]);
                Planeta planeta = new Planeta(nom,galaxia,capacitatMax,clima,floraVermella,essersAcuatics);
                insertarPlaneta(planeta);
                planetas.add(planeta);
        }
    }
   
    public void conectar() throws SQLException  {
        String url = properties.getProperty("db.url");
        String user=properties.getProperty("db.user");
        String pass =properties.getProperty("db.password");
        conexio = DriverManager.getConnection(url, user, pass);
    }
     public void desconectar() throws SQLException  {
        if (conexio != null) {
            conexio.close();
        }
     }
     public void consultarPlanetas() throws SQLException, ExepcioAtribut{
        String query = "select * from planeta";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);      //Executem la consulta i recollim resultat.
        while (rs.next()) {
            String nom = rs.getString("nom");
            String galaxia = rs.getString("galaxia");
            int capacitatMax = rs.getInt("capacitat");
            String clima = rs.getString("clima");
            boolean floraVermella = rs.getBoolean("flora");
            boolean essersAcuatics = rs.getBoolean("fauna");
            planetas.add(new Planeta(nom,galaxia,capacitatMax,clima,floraVermella,essersAcuatics));
        }
        rs.close();
        st.close();
     }
     public void consultarEssers() throws SQLException{
        String query = "select * from being_list;";
        Statement st = conexio.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String nom = rs.getString("nom");
            String planeta = rs.getString("planeta");
            String especie = rs.getString("especie");
            int categoria = rs.getInt("civilitzacio");
            essers.add(new Esser(categoria,especie,nom,planeta));
        }
        rs.close();
        st.close();
     }
     public void insertarPlaneta(Planeta p) throws SQLException{
         String query = "Insert into planeta( nom,galaxia,capacitat,clima,flora,fauna) values(?,?,?,?,?,?)";
         PreparedStatement ps=conexio.prepareStatement(query);
         ps.setString(1, p.getNom());
         ps.setString(2, p.getGalaxia());
         ps.setInt(3, p.getCapacitatMax());
         ps.setString(4, p.getClima());
         ps.setBoolean(5, p.isFloraVermella());
         ps.setBoolean(6, p.isEssersAcuatics());
         ps.executeUpdate();
         ps.close();
     }

    public ArrayList<Planeta> getPlanetas() {
        return planetas;
    }
     
}


 
        
        
        