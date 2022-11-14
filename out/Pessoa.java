public class Pessoa {

  private String nome;
  private String endereco;
  private String telefone;
  private static Pessoa mSingletonObj = null;

  public Pessoa() {

  }

  private static Pessoa getInstance() {
    if (mSingletonObj == null) {
      mSingletonObj = new Pessoa();
    }
    return mSingletonObj;
  }

  public String getNome() {
	return '';
  }

  public void setNome() {

  }

  public String toString() {
	return '';
  }

}