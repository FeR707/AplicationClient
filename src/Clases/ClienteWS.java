/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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
                    estudiante.setNombre(objeto.getString("nombre"));
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

    public List<Alumno> MetodoConsulta() throws JSONException {

        List<Alumno> estudiantes = new ArrayList<>();

        try {

            URL url = new URL("http://localhost/ProyectoWS/index.php");
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
                    estudiante.setNombre(objeto.getString("nombre"));
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

    public Boolean MetodoPost(Alumno alumno) {
        String parametros = "matricula=" + alumno.getMatricula() + "&nombre=" + alumno.getNombre() + "&a_paterno=" + alumno.getaPaterno() + "&a_materno=" + alumno.getaMaterno() + "&edad=" + alumno.getEdad() + "&grupo=" + alumno.getGrupo() + "&telefono=" + alumno.getTelefono() + "&correo=" + alumno.getCorreo() + "&cuatrimestre=" + alumno.getCuatrimestre() + "&promedio=" + alumno.getPromedio();
        try {
            URL url = new URL("http://localhost/ProyectoWS/index.php");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoOutput(true);

            try (OutputStream os = con.getOutputStream()) {
                byte[] input = parametros.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Leer la respuesta del servicio web
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String entrada;
                StringBuilder respuesta = new StringBuilder();

                while ((entrada = in.readLine()) != null) {
                    respuesta.append(entrada);
                }
            }
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public Boolean MetodoDelete(String matricula) {
        try {
            URL url = new URL("http://localhost/ProyectoWS/index.php?matricula=" + matricula);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");

            // Leer la respuesta del servicio web
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String entrada;
                StringBuilder respuesta = new StringBuilder();

                while ((entrada = in.readLine()) != null) {
                    respuesta.append(entrada);
                }
            }
            return true;

        } catch (IOException e) {
            return false;
        }
    }
    public Boolean MetodoPut(Alumno alumno) {
        
        String url = "http://localhost/ProyectoWS/index.php?matricula=" + alumno.getMatricula() + "&nombre=" + alumno.getNombre() + "&a_paterno=" + alumno.getaPaterno() + "&a_materno=" + alumno.getaMaterno() + "&edad=" + alumno.getEdad() + "&grupo=" + alumno.getGrupo() + "&telefono=" + alumno.getTelefono() + "&correo=" + alumno.getCorreo() + "&cuatrimestre=" + alumno.getCuatrimestre() + "&promedio=" + alumno.getPromedio();

        try {
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("PUT");

            // Leer la respuesta del servicio web
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String entrada;
                StringBuilder respuesta = new StringBuilder();

                while ((entrada = in.readLine()) != null) {
                    respuesta.append(entrada);
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
