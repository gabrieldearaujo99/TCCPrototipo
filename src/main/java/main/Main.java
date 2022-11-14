package main;

import org.json.JSONObject;
import templates.GeraCodigoCompleto;

public class Main {
    public static void main(String[] args) {
        Parser.run("in/Diagrama.mdj");
        JSONObject model =  Parser.getJsonModel();

        GeraCodigoCompleto d = new GeraCodigoCompleto(model);
        d.geraCodigo();

    }
}