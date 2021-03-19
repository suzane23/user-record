package com.secure.userdata.record;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserDataNetDBImpl extends UserDataBaseImpl {
    private static final String URL = "https://userrecords-ea32.restdb.io/rest/records";
    private static final String DELETE_URL = "https://userrecords-ea32.restdb.io/rest/records/*?q=%s";

    private HttpURLConnection getConnection(String connURL, String method, Map<String, String> headers){
        HttpURLConnection connection = null;
        try {
            URL url = new URL(connURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            if(method.equals("POST")) {
                connection.setDoOutput(true);
            }

            if(null != headers) {
                Set<Map.Entry<String, String>> headerSet = headers.entrySet();

                for (Map.Entry<String, String> entry : headerSet) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;


    }

    @Override
    void addRecordTo(UserRecord record) {
        try {

            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("x-apikey", "d56cd9b8b341a2883dfb6511098cbe994b7a6");
            headersMap.put("Content-Type", "application/json");

            HttpURLConnection connection = getConnection(URL,"POST", headersMap);

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(record.toString() + "\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Add record : " + record.toString());
            } else {
                System.out.println("POST request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    int deleteRecordByName(String name) {
        int count = 0;
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("x-apikey", "d56cd9b8b341a2883dfb6511098cbe994b7a6");
            headersMap.put("Content-Type", "application/json");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            String query = jsonObject.toString();

            String delete_url = String.format(DELETE_URL,query);

            HttpURLConnection connection = getConnection( delete_url,"DELETE", headersMap);

            int code = connection.getResponseCode();

            if (code == HttpURLConnection.HTTP_OK) { // success
                String response = readResponse(connection);

                JSONObject object = new JSONObject(response);
                count = object.getInt("result");


                // print result
                System.out.println(response);
            } else {
                System.out.println("DELETE request not worked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    List<UserRecord> getRecordsByName(String name) {
        return null;
    }

    @Override
    List<UserRecord> getAllRecords() {
        List<UserRecord> list = new ArrayList<>();
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("x-apikey", "d56cd9b8b341a2883dfb6511098cbe994b7a6");
            headersMap.put("Content-Type", "application/json");

            HttpURLConnection connection = getConnection(URL,"GET", headersMap);

            int code = connection.getResponseCode();
            System.out.println(code);

            if (code == HttpURLConnection.HTTP_OK) { // success
                String response = readResponse(connection);

                JSONArray jsonArray = new JSONArray(response);
                int l = jsonArray.length();
                for (int index = 0 ;  index < l ; ++index) {
                    UserRecord record = fetchRecord((JSONObject) jsonArray.get(index));
                    list.add(record);
                }


                // print result
                System.out.println(response);
            } else {
                System.out.println("GET request not worked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }

    @Override
    int getRecordsCount() {
        int count = 0;
        try {
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("x-apikey", "d56cd9b8b341a2883dfb6511098cbe994b7a6");
            headersMap.put("Content-Type", "application/json");

            HttpURLConnection connection = getConnection(URL,"GET", headersMap);

            int code = connection.getResponseCode();
            System.out.println(code);

            if (code == HttpURLConnection.HTTP_OK) { // success

                String response = readResponse(connection);

                JSONArray jsonArray = new JSONArray(response);
                count = jsonArray.length();

            } else {
                System.out.println("GET request not worked");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    private UserRecord fetchRecord(JSONObject jsonObject) {
        try {
            UserRecord record = new UserRecord();
            record.name = jsonObject.getString("name");
            record.id  = jsonObject.getString("id");
            record.time = jsonObject.getLong("time");
            return record;
        }
        catch (Exception e) {
            return  null;
        }
    }

    private String readResponse(HttpURLConnection connection){
        StringBuffer response = new StringBuffer();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }


}
