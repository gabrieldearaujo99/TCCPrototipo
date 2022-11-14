package templates;

import org.json.JSONObject;

public class GeraAtributo extends GeraClasse {

    public GeraAtributo(JSONObject model) {
        super(model);
    }

    @Override
    protected String geraAtributo(String visibilidade, String tipo, String nome) {
        return "  ".concat(visibilidade).concat(" ").concat(tipo).concat(" ").concat(nome).concat(";\n");
    }

    @Override
    protected String geraAtributoSingleton(String nomeDaClasse) {
        return "  ".concat("private static ").concat(nomeDaClasse).concat(" mSingletonObj").concat(" = null;\n");
    }
}
