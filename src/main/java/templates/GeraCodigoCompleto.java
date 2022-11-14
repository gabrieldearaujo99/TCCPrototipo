package templates;

import org.json.JSONObject;

public class GeraCodigoCompleto extends GeraMetodo {

    public GeraCodigoCompleto(JSONObject model) {
        super(model);
    }

    @Override
    protected String geraConstrutor(String nomeDaClasse) {
        return "\n  public ".concat(nomeDaClasse).concat("() {\n").concat("\n  }\n");
    }
}
