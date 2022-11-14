package templates;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeraClasse {
    protected JSONArray model;

    public GeraClasse(JSONObject model) {
        this.model = model.getJSONArray("diagrama");
    }

    public void geraCodigo() {
        for(int i = 0; i < model.length(); ++i) {
            String nomeDaClasse = model.getJSONObject(i).getString("name");
            try {
                FileWriter fw = new FileWriter("out/" + model.getJSONObject(i).getString("name") + ".java");
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(geraClasse(nomeDaClasse));
                if (model.getJSONObject(i).has("attributes"))
                    for (int a = 0; a < model.getJSONObject(i).getJSONArray("attributes").length(); ++a) {
                        String visibilidade = model.getJSONObject(i).getJSONArray("attributes").getJSONObject(a).getString("visibility");
                        String tipo = model.getJSONObject(i).getJSONArray("attributes").getJSONObject(a).getString("type");
                        String nome = model.getJSONObject(i).getJSONArray("attributes").getJSONObject(a).getString("name");
                        bw.write(geraAtributo(visibilidade, tipo, nome));
                    }
                if (model.getJSONObject(i).has("stereotype") && model.getJSONObject(i).getString("stereotype").equalsIgnoreCase("Singleton")) {
                    bw.write(geraAtributoSingleton(nomeDaClasse));
                    bw.write(geraConstrutor(nomeDaClasse));
                    bw.write(geraMetodoSingleton(nomeDaClasse));
                } else
                    bw.write(geraConstrutor(nomeDaClasse));
                if (model.getJSONObject(i).has("operations"))
                    for (int o = 0; o < model.getJSONObject(i).getJSONArray("operations").length(); ++o) {
                        String nome = model.getJSONObject(i).getJSONArray("operations").getJSONObject(o).getString("name");
                        String visibilidade = model.getJSONObject(i).getJSONArray("operations").getJSONObject(o).has("visibility") ?
                                model.getJSONObject(i).getJSONArray("operations").getJSONObject(o).getString("visibility") : "public";
                        String tipoRetorno = "";
                        for (int p = 0; p < model.getJSONObject(i).getJSONArray("operations").getJSONObject(o).getJSONArray("parameters").length(); ++p) {
                            tipoRetorno = model.getJSONObject(i).getJSONArray("operations").getJSONObject(o).getJSONArray("parameters").getJSONObject(p).has("direction") ?
                                    model.getJSONObject(i).getJSONArray("operations").getJSONObject(o).getJSONArray("parameters").getJSONObject(p).getString("type") : "void";
                        }
                        bw.write(geraMetodo(visibilidade, tipoRetorno, nome));
                    }
                bw.write("\n}");
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String geraClasse(String nomeClasse) {
        return "public class " + nomeClasse + " {\n\n";
    }

    protected String geraAtributo(String visibilidade, String nome, String tipo) {
        return "";
    }

    protected String geraMetodo(String visibilidade, String tipoRetorno, String nome) {
        return "";
    }

    protected String geraConstrutor(String nomeDaClasse) {
        return "";
    }

    /*Singleton*/
    protected String geraAtributoSingleton(String nomeDaClasse) {
        return "";
    }

    protected String geraMetodoSingleton(String nomeDaClasse) {
        return "";
    }
}
