# Datu Atzipena
Atal honetan Visual Studioko aplikazioan kontsumitu dugun RestApi-a garatu dugu, horretarako Spring web framework-a erabili dugu. Honen helburua gure jokuan jolasten ditugun partidak MongoDB datu base batean gordetzea zen, eta Spring-en mapeoen bitartez RestApi-a sortu eta Visual aplikazioan partiden buruzko informazioa bistaratzea..

Horretaz gain Swagger funtzionaltasuna ezarri diogu gure proiektuari, bertan guren api-aren dokumentazio osoa daukagu,non bertan api-ak egiten dituen funtzio guztiak eta hauetako bakoitzak duen egitura eta bueltatuko duen informazioa kontsultatu dezakegu.


### Klase Diagrama:

![Alt text](https://cdn.discordapp.com/attachments/805837040566534207/940872049524633640/BJAjava.png "Klase Diagrama")

Android Studiotik partiden datuak jasotzeko socket baten bitartez egiten dugu, socket honetatik jasotzen dugun informazioa JsonParser libreriarekin atzitzen dugu, eta behin formatu egokian dugunean MongoDB-ra bidaltzen ditugu partida hauek.

*Datuak android-etik funtzio honekin bidaltzen ditugu.*

```java
public String datosJson() throws JSONException {
        String s="[";
        String separador=",";
        int i=0;
        for (Partida p : getPartidas()) {
            JSONObject datos = new JSONObject();
            JSONObject jokalariObj = new JSONObject();
            jokalariObj.put("dni", p.getJokalaria().getDni());
            jokalariObj.put("name", p.getJokalaria().getName());
            jokalariObj.put("surname", p.getJokalaria().getSurname());
            jokalariObj.put("saldo", p.getJokalaria().getSaldo());
            
            JSONObject partidaObj = new JSONObject();
            partidaObj.put("puntuacion", p.getPuntuacion());
            partidaObj.put("fecha", p.printFecha());
            partidaObj.put("jokalaria", jokalariObj);

            datos.put("partida",partidaObj);

            if(i!=getPartidas().size()-1) {
                s = s + datos.toString() + separador;
            }else{
                s = s + datos.toString()+"]";
            }
            i++;
        }
        return s;
    }

```
*Java aplikazioan horrela irakurtzen ditugu jasotako datuak*

```java
JSONArray jsonArr = new JSONArray(str_mezua);
for (int i = 0; i < jsonArr.length(); i++) {
JSONObject jsonObj = jsonArr.getJSONObject(i);
JSONObject partidaJSON = (JSONObject) jsonObj.get("partida");
JSONObject jokalariaJSON = (JSONObject) partidaJSON.get("jokalaria");
System.out.println(jsonObj);
String data = (String) partidaJSON.getString("fecha");
SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
Date fecha = formato.parse(data);
fecha = new Date();
int puntuazioa = partidaJSON.getInt("puntuacion");
String dni = (String) jokalariaJSON.getString("dni");
String name = (String) jokalariaJSON.getString("name");
int saldo = jokalariaJSON.getInt("saldo");
Jokalaria j = new Jokalaria(dni, name, saldo);
Partida p = new Partida(puntuazioa, fecha, j);
partidaDao.insertPartida(p);
```
