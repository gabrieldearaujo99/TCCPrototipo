package templates;

import org.json.JSONObject;

public class GeraMetodo extends GeraAtributo {

    public GeraMetodo(JSONObject model) {
        super(model);
    }

    @Override
    protected String geraMetodo(String visibilidade, String tipoRetorno ,String nome) {
        return "\n  ".concat(visibilidade).concat(" ").concat(tipoRetorno).concat(" ").concat(nome).concat("() {\n")
            .concat(tipoRetorno.equals("void") ? "" : "\treturn".concat(" '';")).concat("\n  }\n");
    }

    @Override
    protected String geraMetodoSingleton(String nomeDaClasse) {
        return "\n"
                .concat("  private static ").concat(nomeDaClasse).concat(" getInstance() {\n")
                .concat("    if (mSingletonObj == null) {\n")
                .concat("      mSingletonObj = new ").concat(nomeDaClasse).concat("();\n")
                .concat("    }\n")
                .concat("    return mSingletonObj;\n")
                .concat("  }\n");
    }
}
