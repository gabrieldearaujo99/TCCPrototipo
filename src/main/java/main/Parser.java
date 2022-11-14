package main;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Parser {
    private static JSONObject jsonModel;

    public static void run(String PathAndName) {
        JSONObject jsonObject = readAndReturnJsonObject(PathAndName);
        jsonModel = returnJsonModel(jsonObject);
    }

    private static JSONObject readAndReturnJsonObject(String pathAndName) {
        JSONObject json = null;
        try {
            FileReader fileReader = new FileReader(pathAndName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> collect = bufferedReader.lines().collect(Collectors.toList());
            StringBuilder jsonTemp = new StringBuilder();
            for (String s : collect)
                jsonTemp.append(s);
            json = new JSONObject(jsonTemp.toString());
            fileReader.close();
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Não foi possível ler o arquivo json");
        }
        return json;
    }

    private static JSONObject returnJsonModel(JSONObject jsonObject) {
        JSONObject jsonModel = new JSONObject();
        JSONArray arrayModel = new JSONArray();
        JSONArray arrayStarUml = jsonObject.getJSONArray("ownedElements").getJSONObject(0).getJSONArray("ownedElements");

        for(int i = 1; i < arrayStarUml.length(); ++i)
            arrayModel.put(arrayStarUml.getJSONObject(i));
        jsonModel.put("diagrama", arrayModel);

        return filterJsonModel(jsonModel);
    }

    private static JSONObject filterJsonModel(JSONObject jsonModel) {
        JSONArray jsonArray = jsonModel.getJSONArray("diagrama");
        for(int i = 0; i < jsonArray.length(); ++i) {
            jsonArray.getJSONObject(i).remove("_parent");
            if(jsonArray.getJSONObject(i).has("attributes")) {
                removeKey(jsonArray.getJSONObject(i), "attributes", "_parent");
                removeKey(jsonArray.getJSONObject(i), "attributes", "_id");
            }
            if(jsonArray.getJSONObject(i).has("operations")){
                removeKey(jsonArray.getJSONObject(i), "operations", "_parent");
                removeKey(jsonArray.getJSONObject(i), "operations", "_id");
            }
            if(jsonArray.getJSONObject(i).has("ownedElements"))
                removeKey(jsonArray.getJSONObject(i), "ownedElements", "_parent");
        }
        return jsonModel;
    }

    private static void removeKey(JSONObject jsonObject, String key, String keyToRemove) {
        if(jsonObject.has(key))
            for(int j = 0; j < jsonObject.getJSONArray(key).length(); ++j)
                jsonObject.getJSONArray(key).getJSONObject(j).remove(keyToRemove);
    }

    public static JSONObject getJsonModel() {
        return jsonModel;
    }
}
