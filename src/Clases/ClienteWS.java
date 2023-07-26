/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ReR-7
 */
public class ClienteWS {

    public List<Alumno> MetodoGetm(String matricula) throws JSONException {

        List<Alumno> estudiantes = new ArrayList<>();

        try {

            URL url = new URL("http://localhost/ProyectoWS/index.php?matricula=" + matricula);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");
            conexion.connect();

            int respuesta = conexion.getResponseCode();
            if (respuesta != 200) {
                throw new RuntimeException("Hubo un error en la conexion " + respuesta);
            } else {
                StringBuilder info = new StringBuilder();
                try (Scanner sc = new Scanner(url.openStream())) {
                    while (sc.hasNext()) {
                        info.append(sc.nextLine());
                    }
                }
               JSONArray array = new JSONArray(info.toString());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject objeto = array.getJSONObject(i);
                    Alumno estudiante = new Alumno();
                    estudiante.setMatricula(objeto.getString("matricula"));
                    estudiante.setaPaterno(objeto.getString("a_paterno"));
                    estudiante.setaMaterno(objeto.getString("a_materno"));
                    estudiante.setEdad(objeto.getString("edad"));
                    estudiante.setGrupo(objeto.getString("grupo"));
                    estudiante.setTelefono(objeto.getString("telefono"));
                    estudiante.setCorreo(objeto.getString("correo"));
                    estudiante.setCuatrimestre(objeto.getString("cuatrimestre"));
                    estudiante.setPromedio(objeto.getString("promedio"));

                    estudiantes.add(estudiante);
                }
            }
        } catch (IOException | RuntimeException e) {
        }

        return estudiantes;
    }

}
